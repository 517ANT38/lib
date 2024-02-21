package com.ml.models;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import com.ml.util.distances.Distable;
import com.ml.util.distances.EuclideanDistance;
import com.ml.util.graphGui.KohonenMapVisualizer;
import com.ml.util.graphGui.KohonenVisualizer;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.randomGenMatrix.RandomGenerator;
import com.ml.util.randomGenMatrix.RandomGeneratorR;



public class KohonenModel {

    private Matrix<Double> weightMatrix;
    private Matrix<Double> potentials;
    private int inputSize;
    private int outputSize;
    private double learningRate;
    private double sigma;
    private double pMin;
    private double offset;
    private double tay;
    private Distable distable = new EuclideanDistance();
    private RandomGenerator<Double> rGenerator = new RandomGeneratorR(); 

    public KohonenModel(int inputSize, int outputSize, double learningRate,double pMin,double offset) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.learningRate = learningRate;
        this.sigma = inputSize / 2;
        this.tay = 1000/Math.log(this.sigma);
        this.weightMatrix = rGenerator.genRand(outputSize, inputSize);
        this.potentials = new MatArray(outputSize, inputSize);
        for (int i = 0; i < outputSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                this.potentials.set(i, j, 1.0/outputSize*inputSize);
            }
        }
        this.pMin = pMin;
        this.offset = offset;
    }

    public List<Double> train(Matrix<Double> inputMatrix, int epochs, double minError) {
        double totalError = 0.0;
        List<Double> errs = new ArrayList<>();
        KohonenMapVisualizer v = new KohonenMapVisualizer();
        v.visualize(weightMatrix.toArr(), "first");
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < inputMatrix.getDimensions()[0]; i++) {
                Matrix<Double> inputVector = inputMatrix.getVector(i, 0);
                int winnerIndex = findNearestNeuron(inputVector);
                updatePotentials(winnerIndex);
                resetInactiveNeuron();
                if (learningRate > 0.02) {                    
                    learningRate = learningRate * Math.exp(-epoch/1000);
                }
                sigma = sigma*Math.exp(-epoch/tay);
                updateWeights(winnerIndex, inputVector);
                totalError = distable.distance(inputVector, weightMatrix.getVector(winnerIndex, 0));
                totalError *= totalError;
                errs.add(totalError);
                if (1/inputSize*totalError <minError) {
                    break;
                }
            }
           
        }
        v.visualize(weightMatrix.toArr(), "res");
        return errs;
    }

    public int predict(Matrix<Double> inputVector) {
        int winnerIndex = findNearestNeuron(inputVector);
        return winnerIndex;
    }

    private int findNearestNeuron(Matrix<Double> inputVector) {
        int winnerIndex = 0;
        var minDistance = distable.distance(inputVector, weightMatrix.getVector(0, 0));
        for (int i = 1; i < outputSize; i++) {
            var distance = distable.distance(inputVector, weightMatrix.getVector(i, 0));
            if (distance < minDistance) {
                minDistance = distance;
                winnerIndex = i;
            }
        }
        return winnerIndex;
    }

    private void updatePotentials(int winnerIndex){
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                potentials.set(j, i, potentials.get(j,i) +  1.0/outputSize*inputSize);
            }
            
        }
        for (int i = 0; i < inputSize; i++) {
            potentials.set(winnerIndex, i, potentials.get(winnerIndex,i) -  pMin);
        }
    }

    private void resetInactiveNeuron(){
        for (int i = 0; i < outputSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                if (potentials.get(i, j) <= pMin) {
                    weightMatrix.set(i, j, 0.0);
                }
            }
        }
    }

    private void updateWeights(int winnerIndex, Matrix<Double> inputVector) {
        for (int i = 0; i < inputSize; i++) {
            double distance = distable.distance(new MatArray(new double[][]{new double[]{winnerIndex,0.0}}), 
                new MatArray(new double[][]{new double[]{0.0,i}})) +offset;
            var neighborhood = Math.exp(-(distance*distance)/2*sigma*sigma)*learningRate;
            var currentWeight = weightMatrix.get(winnerIndex, i);
            var input = inputVector.get(0, i);
            var updatedWeight = (currentWeight + neighborhood * (input - currentWeight));
            weightMatrix.set(winnerIndex, i, updatedWeight);
        }
    }

    public int getInputSize() {
        return inputSize;
    }

    public int getOutputSize() {
        return outputSize;
    }

    public Matrix<Double> getWeightMatrix() {
        return weightMatrix;
    }

    

}


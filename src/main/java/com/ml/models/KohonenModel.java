package com.ml.models;


import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import com.ml.util.distances.Distable;
import com.ml.util.distances.EuclideanDistance;
import com.ml.util.graphGui.KohonenVisualizer;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.randomGenMatrix.RandomGenerator;
import com.ml.util.randomGenMatrix.RandomGeneratorR;



public class KohonenModel {

    private Matrix<Double> weightMatrix;
    private int inputSize;
    private int outputSize;
    private double learningRate;
    private Distable distable = new EuclideanDistance();
    private RandomGenerator<Double> rGenerator = new RandomGeneratorR(); 

    public KohonenModel(int inputSize, int outputSize, double learningRate) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.learningRate = learningRate;
        weightMatrix = rGenerator.genRand(outputSize, inputSize);
        
    }

    public void train(Matrix<Double> inputMatrix, int epochs) {
        
        KohonenVisualizer vs = new KohonenVisualizer(this);
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < inputMatrix.getDimensions()[0]; i++) {
                Matrix<Double> inputVector = inputMatrix.getVector(i, 0);
                int winnerIndex = findNearestNeuron(inputVector);
                updateWeights(winnerIndex, inputVector);
            }
           
        }
        vs.visualize();
        
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

    private void updateWeights(int winnerIndex, Matrix<Double> inputVector) {
        for (int i = 0; i < inputSize; i++) {
            var currentWeight = weightMatrix.get(winnerIndex, i);
            var input = inputVector.get(0, i);
            var updatedWeight = (currentWeight.doubleValue() + learningRate * (input.doubleValue() - currentWeight.doubleValue()));
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


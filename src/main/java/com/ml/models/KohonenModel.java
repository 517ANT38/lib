package com.ml.models;


import com.ml.util.graphGui.KohonenVisualization;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.randomGenMatrix.RandomGenerator;
import com.ml.util.randomGenMatrix.RandomGeneratorR;

import lombok.Getter;


@Getter
public class KohonenModel {

    private Matrix<Double> weightMatrix;
    private int inputSize;
    private int outputSize;
    private double learningRate;
    private RandomGenerator<Double> rGenerator = new RandomGeneratorR(); 

    public KohonenModel(int inputSize, int outputSize, double learningRate) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.learningRate = learningRate;
        weightMatrix = rGenerator.genRand(outputSize, inputSize);
        
    }

    public void train(Matrix<Double> inputMatrix, int epochs) {
        KohonenVisualization visualization = new KohonenVisualization(this);

        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < inputMatrix.getDimensions()[0]; i++) {
                Matrix<Double> inputVector = inputMatrix.getVector(i, 0);
                int winnerIndex = findNearestNeuron(inputVector);
                updateWeights(winnerIndex, inputVector);
            }

            
        }
        visualization.visualize(inputMatrix);
    }

    public int predict(Matrix<Double> inputVector) {
        int winnerIndex = findNearestNeuron(inputVector);
        return winnerIndex;
    }

    private int findNearestNeuron(Matrix<Double> inputVector) {
        int winnerIndex = 0;
        var minDistance = distance(inputVector, weightMatrix.getVector(0, 0));
        for (int i = 1; i < outputSize; i++) {
            var distance = distance(inputVector, weightMatrix.getVector(i, 0));
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

    private double distance(Matrix<Double> v1, Matrix<Double> v2) {
        var sum = v1.sub(v2).map(x -> x.doubleValue() * x.doubleValue()).sum(0, 1);
        return  Math.sqrt(sum.doubleValue());
    }

}


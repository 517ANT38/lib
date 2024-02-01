package com.ml.net.layer;

import java.io.Serializable;

import com.ml.optimizer.util.Optimizer;
import com.ml.optimizer.util.SGD;
import com.ml.util.activationFunction.ActivationFunction;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.randomGenMatrix.RandomGenerator;
import com.ml.util.randomGenMatrix.RandomGeneratorR;

public class LayerHidden implements Layerable, Serializable {

    private static final long serialVersionUID = 1L;
    private Matrix<Double> matrix;
    private Matrix<Double> biases;
    private Matrix<Double> x;
    private Matrix<Double> y;
    private Optimizer optimizer;
    private ActivationFunction func;
    private RandomGenerator<Double> rg;

    public LayerHidden(int input, int countNeuron, ActivationFunction func, Optimizer optimizer, RandomGenerator<Double> rg){
        this.matrix = rg.genRand(input, countNeuron);
        this.biases = new MatArray(1, countNeuron);
        this.func = func;
        this.rg = rg;
        this.optimizer = optimizer;
    }
   

    @Override
    public Matrix<Double> ford(Matrix<Double> m) {
        this.x = m.dot(this.matrix)
            .add(biases);
        this.y = this.x
            .map(x -> func.apply(x));
        return this.y;
    }

    @Override
    public Matrix<Double> back(Matrix<Double> m) {
        
        var d = m
            .mult(x.map(x -> func.difApply(x)));
            
        
        this.matrix = optimizer.optWs(matrix, d, y);
        this.biases = optimizer.optBs(biases, d);
        
        return d.dot(matrix.transpose());
    }
    
}

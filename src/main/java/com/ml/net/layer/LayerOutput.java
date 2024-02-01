package com.ml.net.layer;

import java.io.Serializable;

import com.ml.util.activationFunction.ActivationFunction;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.randomGenMatrix.RandomGenerator;
import com.ml.util.randomGenMatrix.RandomGeneratorR;

public class LayerOutput implements Layerable, Serializable{
    private static final long serialVersionUID = 1L;
    private Matrix<Double> matrix;
    private Matrix<Double> biases;
    private Matrix<Double> x;
    private Matrix<Double> y;
    private ActivationFunction func;
    private RandomGenerator<Double> rg;

    public LayerOutput(int input, int countNeuron, ActivationFunction func, RandomGenerator<Double> rg){
        this.matrix = rg.genRand(input, countNeuron);
        this.biases = new MatArray(1, countNeuron);
        this.func = func;
        this.rg = rg;
    }
    public LayerOutput(int input, int countNeuron, ActivationFunction func){
        this(input, countNeuron, func, new RandomGeneratorR());
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
    public Matrix<Double> back(Matrix<Double> m, double coff) {
        var d = x.map(u -> func.difApply(u))            
            .mult(this.y.sub(m));
        
        this.matrix = matrix.add(y
            .mult(d)
            .map(x -> x * coff));
        this.biases = biases.map(x -> x + d.sum(0, 0)*coff);// научиться находить сумму элементов строки (столбца)
        
           
        return d.dot(matrix.transpose());
    }

    
    
}

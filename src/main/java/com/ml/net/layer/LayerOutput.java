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
    private Matrix<Double> y;
    private ActivationFunction func;

    public LayerOutput(int input, int countNeuron, ActivationFunction func, RandomGenerator<Double> rg){
        this.matrix = rg.genRand(input, countNeuron);
        this.biases = new MatArray(1, countNeuron);
    }
    public LayerOutput(int input, int countNeuron, ActivationFunction func){
        this(input, countNeuron, func, new RandomGeneratorR());
    }
    @Override
    public Matrix<Double> ford(Matrix<Double> m) {
        this.y = m.dot(this.matrix)
            .add(biases)
            .map(x -> func.apply(x));
        return this.y;
    }
    @Override
    public Matrix<Double> back(Matrix<Double> m, double coff) {
        var d = this.y.sub(m).map(x -> func.difApply(x));
        this.matrix = matrix.add(y
            .transpose()
            .dot(d)
            .map(x -> x * coff));
        this.biases = biases.map(x -> x + d.sum(0, 0)*coff);// научиться находить сумму элементов строки (столбца)
        return d;
    }

    
    
}

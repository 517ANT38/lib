package com.ml.util.lossFunction;

import com.ml.util.linearAlgebra.Matrix;

public class MeanSquarErr implements LossFunction<Double> {

    @Override
    public Double apply(Matrix<Double> arg0, Matrix<Double> arg1) {
        return arg0.sub(arg1)
            .map(x -> x*x)
            .sum(0, 0)*0.5;
    }
    
}

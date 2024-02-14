package com.ml.util.lossFunction;

import com.ml.util.linearAlgebra.Matrix;

public class CosineDistance implements LossFunction<Double>  {

    @Override
    public Double apply(Matrix<Double> arg0, Matrix<Double> arg1) {
        var v0 = Math.sqrt(arg0.map(x -> x*x).sum(0, 0));
        var v1 = Math.sqrt(arg1.map(x -> x*x).sum(0, 0));
        var v2 = arg0.transpose().dot(arg1).get(0, 0);
        return 1 - v2/(v0-v1+0e-10);
    }
    
}

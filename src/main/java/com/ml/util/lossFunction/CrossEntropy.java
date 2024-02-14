package com.ml.util.lossFunction;

import com.ml.util.linearAlgebra.Matrix;

public class CrossEntropy implements LossFunction<Double> {

    @Override
    public Double apply(Matrix<Double> arg0, Matrix<Double> arg1) {
        
        return arg0.mult(arg1.map(x -> Math.log(Math.abs(x+0e-10)))).sum(0,0)*-1;
    }
    
}

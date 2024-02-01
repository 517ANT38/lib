package com.ml.optimizer.util;

import com.ml.util.linearAlgebra.Matrix;

public interface Optimizer {

    Matrix<Double> optWs(Matrix<Double> ws,Matrix<Double> d,Matrix<Double> y);
    Matrix<Double> optBs(Matrix<Double> bs,Matrix<Double> d);
} 
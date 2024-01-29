package com.ml.util.activationFunction;

import com.ml.util.linearAlgebra.Matrix;

public interface ActivationFunction<T extends Number> {

    Matrix<T> apply(Matrix<T> x);
    Matrix<T> difApply(Matrix<T> x);
} 
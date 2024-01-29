package com.ml.util.lossFunction;

import java.util.function.BiFunction;

import com.ml.util.linearAlgebra.Matrix;

public interface LossFunction<T extends Number> extends BiFunction<Matrix<T>,Matrix<T>,Matrix<Double>>  {
    
}

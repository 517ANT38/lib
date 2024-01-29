package com.ml.util.randomGenMatrix;

import com.ml.util.linearAlgebra.Matrix;

public interface RandomGenerator<T extends Number> {
    Matrix<T> genRand(int n, int m);
}

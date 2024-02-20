package com.ml.util.distances;

import com.ml.util.linearAlgebra.Matrix;

public interface Distable {
    double distance(Matrix<Double> v1, Matrix<Double> v2);
}

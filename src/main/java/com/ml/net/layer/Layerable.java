package com.ml.net.layer;

import com.ml.util.linearAlgebra.Matrix;

public interface Layerable {

    Matrix<Double> ford(Matrix<Double> m);
    Matrix<Double> back(Matrix<Double> m,double coff);
}

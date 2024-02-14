package com.ml.util.dataNormolize;

import com.ml.util.linearAlgebra.Matrix;

public interface Normolizable {
    double[] normolize(double[] data);
    Matrix<Double> normolize(Matrix<Double> data);    
}

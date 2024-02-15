package com.ml.models;

import java.util.List;

import com.ml.net.Netable;
import com.ml.util.linearAlgebra.Matrix;

public interface Model extends Netable{
    List<Double> fit(double[][] xs, double[][] ys);
    Matrix<Double> predict(double[] x);
    Netable getNet();
}

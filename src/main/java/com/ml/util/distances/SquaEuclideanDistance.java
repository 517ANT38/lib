package com.ml.util.distances;

import com.ml.util.linearAlgebra.Matrix;

public class SquaEuclideanDistance implements Distable {

    @Override
    public double distance(Matrix<Double> v1, Matrix<Double> v2) {
        return v1.sub(v2).map(x -> x * x).sum(0, 1);
    }
    
}

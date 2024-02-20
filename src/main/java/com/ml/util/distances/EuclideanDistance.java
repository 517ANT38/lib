package com.ml.util.distances;

import com.ml.util.linearAlgebra.Matrix;

public class EuclideanDistance implements Distable {

    @Override
    public double distance(Matrix<Double> v1, Matrix<Double> v2) {
        var sum = v1.sub(v2).map(x -> x * x).sum(0, 1);
        return  Math.sqrt(sum);
    }
    
}

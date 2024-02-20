package com.ml.util.distances;

import com.ml.util.linearAlgebra.Matrix;

public class ManhattanDistance implements Distable{

    @Override
    public double distance(Matrix<Double> v1, Matrix<Double> v2) {
        return v1.sub(v2).map(x -> Math.abs(x)).sum(0, 1);
    }
    
}

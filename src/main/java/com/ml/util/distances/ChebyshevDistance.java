package com.ml.util.distances;

import java.util.Arrays;

import com.ml.util.linearAlgebra.Matrix;

public class ChebyshevDistance implements Distable {

    @Override
    public double distance(Matrix<Double> v1, Matrix<Double> v2) {
        return Arrays.stream(vectorToArr(v1
            .sub(v2)
            .map(x->Math.abs(x))))
                .max()
                .getAsDouble();
    }
    private static double[] vectorToArr(Matrix<Double> m){
        var l = m.getDimensions()[1];
        var res = new double[l];
        for (int i = 0; i < res.length; i++) {
            res[i] = m.get(0, i);
        }
        return res;
    }
}

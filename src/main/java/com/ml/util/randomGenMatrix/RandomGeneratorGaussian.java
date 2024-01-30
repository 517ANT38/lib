package com.ml.util.randomGenMatrix;

import java.io.Serializable;
import java.util.Random;

import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.linearAlgebra.Matrix;

public class RandomGeneratorGaussian implements RandomGenerator<Double>, Serializable {
    private static final long serialVersionUID = 1L;
    private final Random RND;

    public RandomGeneratorGaussian() {
        this.RND = new Random();
    }

    @Override
    public Matrix<Double> genRand(int n, int m) {
        double[][] res = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = Math.max(-1,Math.min(RND.nextGaussian(),1)); 
            }         
        }
        return new MatArray(res);
    }
    
}

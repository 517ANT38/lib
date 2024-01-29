package com.ml.util.randomGenMatrix;

import java.util.Random;

import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.linearAlgebra.NDArray;

public class RandomGeneratorGaussian implements RandomGenerator<Double> {

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
        return new NDArray(res);
    }
    
}

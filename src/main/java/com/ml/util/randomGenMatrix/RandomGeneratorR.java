package com.ml.util.randomGenMatrix;

import java.util.Random;

import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.linearAlgebra.NDArray;

public class RandomGeneratorR implements RandomGenerator<Double> {

    private final  Random RND; 

    public RandomGeneratorR() {
        RND = new Random();
    }

    @Override
    public Matrix<Double> genRand(int n, int m) {
        double[][] res = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = RND.nextDouble(-1, 1); 
            }         
        }
        return new NDArray(res);
    }
    
}

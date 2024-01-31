package com.ml.util.linearAlgebra;

public final class Util {
    
    private Util(){}

    public static Matrix<Double> dot(Matrix<Double> a, Matrix<Double> b){
        int rn1 = a.getDimensions()[0];
        int rm1 = a.getDimensions()[1];
        int rn2 = b.getDimensions()[0];
        int rm2 = b.getDimensions()[1];
        
        double[][] res = new double[rn1][rm2];
        for (int i = 0; i < rn1; i++) {
            for (int j = 0; j < rm2; j++) {
                for (int k = 0; k < rm1; k++) {
                    res[i][j] += a.get(i, k) * b.get(k, j);
                }
            }
        }
        return new MatArray(res);
    }

    
}

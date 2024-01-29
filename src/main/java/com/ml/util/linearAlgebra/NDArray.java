package com.ml.util.linearAlgebra;

import java.io.Serializable;
import java.util.function.Function;

public class NDArray implements Matrix<Double>, Serializable {
    
    private static final long serialVersionUID = 1L; 
    private double[][] matrix;

    public NDArray(double[][] matrix) {
        this.matrix = matrix;
    }
    public NDArray(int n, int m){
        this.matrix = new double[n][m];
    }

    public static Matrix<Double> dot(Matrix<Double> a, Matrix<Double> b){
        int rn1 = a.getDimensions()[0];
        int rm1 = a.getDimensions()[1];
        int rn2 = b.getDimensions()[0];
        int rm2 = b.getDimensions()[1];
        if (rm1 != rn2) 
            throw new IllegalArgumentException("""
                    Number of columns in  first matrix 
                    is ​​not equal to  number of rows in second
                """);
        double[][] res = new double[rn1][rm2];
        for (int i = 0; i < rn1; i++) {
            for (int j = 0; j < rm2; j++) {
                for (int k = 0; k < rm1; k++) {
                    res[i][j] += a.get(i, k) * b.get(k, j);
                }
            }
        }
        return new NDArray(res);
    }

    @Override
    public Matrix<Double> transpose() {
        int m = matrix[0].length;
        int n = matrix.length;
        double[][] res = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[j][i] = matrix[i][j];
            }          
        }
        return new NDArray(res);
    }

    @Override
    public Double get(int i, int j) {
        if(i >= matrix.length)
            throw new IllegalArgumentException("Not found row with index" + i);
        if(j >= matrix[0].length)
            throw new IllegalArgumentException("Not found column with index" + j);
        return matrix[i][j];
    }

    @Override
    public void set(int i, int j, Double v) {
        if(i >= matrix.length)
            throw new IllegalArgumentException("Not found row with index" + i);
        if(j >= matrix[0].length)
            throw new IllegalArgumentException("Not found column with index" + j);
        matrix[i][j] = v;
    }

    @Override
    public Matrix<Double> dot(Matrix<Double> m) {
        int rm1 = matrix[0].length;
        int rn1 = matrix.length;
        int rn2 = m.getDimensions()[0];
        int rm2 = m.getDimensions()[1];
        if (rm1 != rn2) 
            throw new IllegalArgumentException("""
                Number of columns in  first matrix 
                is ​​not equal to  number of rows in second
            """);
        double[][] res = new double[rn1][rm2];
        for (int i = 0; i < rn1; i++) {
            for (int j = 0; j < rm2; j++) {
                for (int k = 0; k < rm1; k++) {
                    res[i][j] += matrix[i][k] * m.get(k, j);
                }
            }
        } 
        return new NDArray(res);
    }

    @Override
    public Matrix<Double> add(Matrix<Double> m) {
        var dms = m.getDimensions();
        if(dms[0] != matrix.length)
            throw new IllegalArgumentException("""
                Number of columns in  first matrix 
                is ​​not equal to  number of columns in second
            """);
        if(dms[1] != matrix[0].length)
            throw new IllegalArgumentException("""
                Number of columns in  first matrix 
                is ​​not equal to  number of columns in second
            """);
        double[][] res = new double[dms[0]][dms[1]];
        for (int i = 0; i < dms[0]; i++) {
            for (int j = 0; j < dms[1]; j++) {
                res[i][j] = matrix[i][j] + m.get(i, j);
            }
        }
        return new NDArray(res);
    }

    @Override
    public Matrix<Double> sub(Matrix<Double> m) {
        var dms = m.getDimensions();
        if(dms[0] != matrix.length)
            throw new IllegalArgumentException("""
                Number of columns in  first matrix 
                is ​​not equal to  number of columns in second
            """);
        if(dms[1] != matrix[0].length)
            throw new IllegalArgumentException("""
                Number of columns in  first matrix 
                is ​​not equal to  number of columns in second
            """);
        double[][] res = new double[dms[0]][dms[1]];
        for (int i = 0; i < dms[0]; i++) {
            for (int j = 0; j < dms[1]; j++) {
                res[i][j] = matrix[i][j] - m.get(i, j);
            }
        }
        return new NDArray(res);
    }
    @Override
    public Matrix<Double> map(Function<Double, Double> func) {
        int n = matrix.length;
        int m = matrix[0].length;
        double[][] res = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = func.apply(matrix[i][j]);
            }
        }
        return new NDArray(res);
    }
    @Override
    public int[] getDimensions() {
        return new int[]{matrix.length,matrix[0].length};
    }

    
    
}
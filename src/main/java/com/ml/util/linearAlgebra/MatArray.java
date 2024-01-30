package com.ml.util.linearAlgebra;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Function;

import lombok.ToString;
@ToString
public class MatArray implements Matrix<Double>, Serializable {
    
    private static final long serialVersionUID = 1L; 
    private double[][] matrix;

    public MatArray(double[][] matrix) {
        this.matrix = matrix;
    }
    public MatArray(int n, int m){
        this.matrix = new double[n][m];
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
        return new MatArray(res);
    }

    @Override
    public Double get(int i, int j) {
        if(i >= matrix.length)
            throw new IllegalArgumentException("Not found row with index " + i);
        if(j >= matrix[0].length)
            throw new IllegalArgumentException("Not found column with index " + j);
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
        // if (rm1 != rn2) 
        //     throw new IllegalArgumentException("""
        //         Number of columns in  first matrix 
        //         is ​​not equal to  number of rows in second
        //     """);
        double[][] res = new double[rn1][rm2];
        for (int i = 0; i < rn1; i++) {
            for (int j = 0; j < rm2; j++) {
                for (int k = 0; k < rm1; k++) {
                    res[i][j] += matrix[i][k] * m.get(k, j);
                }
            }
        } 
        return new MatArray(res);
    }

    @Override
    public Matrix<Double> add(Matrix<Double> m) {
        var dms = m.getDimensions();
        var n = matrix.length;
        var mm = matrix[0].length;
        double[][] res = new double[n][mm];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mm; j++) {
                if(dms[0] == 1)
                    res[i][j] = matrix[i][j] + m.get(0, j);
                else if(dms[1] == 1)
                    res[i][j] = matrix[i][j] + m.get(i, 0);
                else if(dms[0] == 1 && dms[1] == 1)
                    res[i][j] = matrix[i][j] + m.get(0, 0);
                else 
                    res[i][j] = matrix[i][j] + m.get(i, j);
            }
        }
        return new MatArray(res);
    }

    @Override
    public Matrix<Double> sub(Matrix<Double> m) {
        var dms = m.getDimensions();
        var n = matrix.length;
        var mm = matrix[0].length;
        double[][] res = new double[n][mm];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mm; j++) {
                if(dms[0] == 1)
                    res[i][j] = matrix[i][j] - m.get(0, j);
                else if(dms[1] == 1)
                    res[i][j] = matrix[i][j] - m.get(i, 0);
                else if(dms[0] == 1 && dms[1] == 1)
                    res[i][j] = matrix[i][j] - m.get(0, 0);
                else 
                    throw new IllegalArgumentException("Dimensions row or col not equal");
            }
        }
        return new MatArray(res);
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
        return new MatArray(res);
    }
    @Override
    public int[] getDimensions() {
        return new int[]{matrix.length,matrix[0].length};
    }
    @Override
    public Matrix<Double> getVector(int index, int axis) {
        if (axis >= 2) {
            throw new IllegalArgumentException("Axis not found");
        }
        else if(axis == 0){
            if (index > matrix.length) {
                throw new IllegalArgumentException("Not found row");
            }
            return new MatArray(new double[][]{matrix[index]});
        }
        else {
            if (index > matrix[0].length) {
                throw new IllegalArgumentException("Not found column");
            }
            var res = new double[matrix.length][1];
            for (int i = 0; i < matrix.length; i++) {
                res[i][0] = matrix[i][0];
            }
            return new MatArray(res);
        }
    }
    @Override
    public Double sum(int index, int axis) {
        if (axis >= 2) {
            throw new IllegalArgumentException("Axis not found");
        }
        else if(axis == 0){
            if (index > matrix.length) {
                throw new IllegalArgumentException("Not found row");
            }
            return Arrays.stream(matrix[index]).sum();
        }
        else {
            if (index > matrix[0].length) {
                throw new IllegalArgumentException("Not found column");
            }
            return Arrays.stream(matrix)
                .mapToDouble(x -> x[index])
                .sum();
        }
    }
    @Override
    public Matrix<Double> mult(Matrix<Double> m) {
        var dms = m.getDimensions();
        var n = matrix.length;
        var mm = matrix[0].length;
        double[][] res = new double[n][mm];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mm; j++) {
                if(dms[0] == 1)
                    res[i][j] = matrix[i][j] * m.get(0, j);
                else if(dms[1] == 1)
                    res[i][j] = matrix[i][j] * m.get(i, 0);
                else if(dms[0] == 1 && dms[1] == 1)
                    res[i][j] = matrix[i][j] * m.get(0, 0);
                else 
                    res[i][j] = matrix[i][j] * m.get(i, j);
            }
        }
        return new MatArray(res);
    }
    

    
    
}
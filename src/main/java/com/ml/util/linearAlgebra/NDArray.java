package com.ml.util.linearAlgebra;

import java.io.Serializable;

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
        return null;
    }

    @Override
    public Matrix<Double> transpose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transpose'");
    }

    @Override
    public Double get(int i, int j) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void set(int i, int j, Double v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public Matrix<Double> dot(Matrix<Double> m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dot'");
    }

    @Override
    public Matrix<Double> add(Matrix<Double> m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public Matrix<Double> sub(Matrix<Double> m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sub'");
    }

    
    
}
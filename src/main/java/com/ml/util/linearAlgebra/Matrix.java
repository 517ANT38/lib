package com.ml.util.linearAlgebra;

import java.util.function.Function;

public interface Matrix<T extends Number> {
    Matrix<T> transpose();
    T get(int i, int j);
    void set(int i, int j,T v);
    Matrix<T> dot(Matrix<T> m);
    Matrix<T> add(Matrix<T> m);
    Matrix<T> sub(Matrix<T> m);
    Matrix<T> mult(Matrix<T> m);
    Matrix<T> divide(Matrix<T> m);
    Matrix<T> map(Function<T,T> func);
    int[] getDimensions();
    Matrix<T> getVector(int index, int axis);
    T sum(int index, int axis);
    double[][] toArr();
}

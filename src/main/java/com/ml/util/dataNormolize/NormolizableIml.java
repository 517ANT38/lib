package com.ml.util.dataNormolize;

import java.util.Arrays;

import com.ml.util.linearAlgebra.Matrix;

public class NormolizableIml implements Normolizable {

    @Override
    public double[] normolize(double[] data) {
        var st = Arrays.stream(data).summaryStatistics();
        return Arrays.stream(data)
            .map(x -> x - st.getMin())
            .map(x -> x / (st.getMax() - st.getMin()))
            .toArray();
    }

    @Override
    public Matrix<Double> normolize(Matrix<Double> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'normolize'");
    }
    
}

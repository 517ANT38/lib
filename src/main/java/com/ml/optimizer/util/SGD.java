package com.ml.optimizer.util;

import java.io.Serializable;

import com.ml.util.linearAlgebra.Matrix;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SGD implements Optimizer, Serializable{
    private static final long serialVersionUID = 1L;
    private double rate;

    @Override
    public Matrix<Double> optWs(Matrix<Double> ws, Matrix<Double> d, Matrix<Double> y) {
        return ws.add(y.mult(d).map(x -> x * (-rate)));
    }

    @Override
    public Matrix<Double> optBs(Matrix<Double> bs, Matrix<Double> d) {
        return bs.map(x -> x - d.sum(0, 0)*(-rate));
    }

    @Override
    public void cleanState() {
        
    }
    
}

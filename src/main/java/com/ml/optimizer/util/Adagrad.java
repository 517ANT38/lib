package com.ml.optimizer.util;

import java.io.Serializable;

import com.ml.util.linearAlgebra.Matrix;

public class Adagrad implements Optimizer, Serializable {
    private static final long serialVersionUID = 1L;
    private Matrix<Double> grad;
    private double rate;
    private double iner;
    private double ep;

    public Adagrad(double rate, double iner,double ep) {
        this.rate = rate;
        this.iner = iner;
        this.ep = ep;
    }

    public Adagrad(double rate, double iner){
        this(rate,iner,10e-6);
    }

    @Override
    public Matrix<Double> optWs(Matrix<Double> ws, Matrix<Double> d, Matrix<Double> y) {
        var dw = y.mult(d);
        var g = dw;
        if(grad != null){            
            dw = grad.map(x -> x * iner)
                .add(dw.map(x -> x * x * (1 - iner)));
        }
        this.grad = dw;
        return ws.add(g.map(x -> x * (-rate))
            .divide(grad.map(x -> Math.sqrt(Math.abs(x + ep)))));
    }

    @Override
    public Matrix<Double> optBs(Matrix<Double> bs, Matrix<Double> d) {
        return bs.map(x -> x + d.sum(0, 0)*(-rate));
    }

    @Override
    public void cleanState() {
        this.grad = null;
    }
    
}

package com.ml.optimizer.util;



import com.ml.util.linearAlgebra.Matrix;



public class RMSProp implements Optimizer {

    private double rate;
    private double iner;
    private Matrix<Double> grad;
    
    public RMSProp(double rate, double iner) {
        this.rate = rate;
        this.iner = iner;
    }

    @Override
    public Matrix<Double> optWs(Matrix<Double> ws, Matrix<Double> d, Matrix<Double> y) {
        
        
        var dw = y.mult(d);
        if(grad != null){            
            dw = grad.map(x -> x * iner)
                .add(dw.map(x -> x*(1 - iner)))
                .map(x -> x * (-rate));
        }
        this.grad = dw;
        return ws.add(dw);
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

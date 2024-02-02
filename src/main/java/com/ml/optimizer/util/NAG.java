package com.ml.optimizer.util;

import com.ml.util.linearAlgebra.Matrix;

public class NAG implements Optimizer {

    private double iner;
    private double rate;
    private Matrix<Double> velocity;

    public NAG(double rate, double iner) {
        this.rate = rate;
        this.iner = iner;
    }

    @Override
    public Matrix<Double> optWs(Matrix<Double> ws, Matrix<Double> d, Matrix<Double> y) {
        if(velocity == null){
            this.velocity = ws;
        }
        var prevVelocity = velocity;
        velocity = velocity
            .map(x -> x * iner)
            .sub(d.map(x -> x * (-rate)))
            .mult(y).map(x -> x * (-rate) * (1 - iner));
        ws = ws.sub(prevVelocity.map(x -> x * iner)).add(velocity);
        return ws;
    }

    @Override
    public Matrix<Double> optBs(Matrix<Double> bs, Matrix<Double> d) {
        return bs.map(x -> x + d.sum(0, 0)*(-rate));
    }

    @Override
    public void cleanState() {
        this.velocity = null;
    }
    
}

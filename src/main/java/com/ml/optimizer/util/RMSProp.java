package com.ml.optimizer.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ml.util.linearAlgebra.Matrix;



public class RMSProp implements Optimizer {

    private double rate;
    private double iner;
    private List<Matrix<Double>> grads;
    private int posLayerW;
    private int posLayerB;
    private int countLayer;
    
    public RMSProp(double rate, double iner, int countLayer) {
        this.rate = rate;
        this.iner = iner;
        this.grads = new ArrayList<>(Collections.nCopies(countLayer, null));
        this.posLayerW = countLayer - 1;
        this.posLayerB = countLayer - 1;
        this.countLayer = countLayer;
    }

    @Override
    public Matrix<Double> optWs(Matrix<Double> ws, Matrix<Double> d, Matrix<Double> y) {
        if(posLayerW == 0)
            posLayerW = countLayer;
        var s = grads.get(countLayer);
        var dw = y.mult(d);
        if(s != null){
            dw = s.map(x -> x * iner).add(dw.map(x -> x*(1 - iner)));
        }
        grads.set(posLayerW, dw.map(x -> x * (-rate)));
        posLayerW--;
        return ws.add(dw);
    }

    @Override
    public Matrix<Double> optBs(Matrix<Double> bs, Matrix<Double> d) {
        return bs.map(x -> x + d.sum(0, 0)*(-rate));
    }
    
}

package com.ml.optimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ml.net.Netable;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.lossFunction.LossFunction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RMSProp implements Optimizer {

    private final Random RND = new Random(); 
    private int epoch;
    private double eps;
    private double iner;
    private double rate;
    private LossFunction<Double> loss;

    @Override
    public void opt(Netable net, double[][] xs, double[][] ys) {
        var layers = net.getLayers();
        List<Matrix<Double>> grads = new ArrayList<>();
        for (int i = 0; i < epoch; i++) {
            int ep = RND.nextInt(0, xs.length - 1);
            Matrix<Double> x = new MatArray(new double[][]{xs[ep]}); 
            Matrix<Double> y = new MatArray(new double[][]{ys[ep]});
            var res = net.getResult(x);
            if(loss.apply(res, y) < eps)
                break;
            var tmp = y;
            for (int j = layers.size() - 1; j >= 0; j--) {
                var l = layers.get(j);
                tmp = l.back(tmp, rate);
            }
        } 
    }

    
}

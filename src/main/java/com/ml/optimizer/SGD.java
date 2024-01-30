package com.ml.optimizer;

import java.util.List;
import java.util.Random;

import com.ml.net.Netable;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.lossFunction.LossFunction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SGD implements Optimizer  {
    private final Random RND = new Random(); 
    private int epoch;
    private double eps;
    private double rate;
    private LossFunction<Double> loss;   


    @Override
    public void opt(Netable net, List<Matrix<Double>> xs, List<Matrix<Double>> ys) {
        var layers = net.getLayers();
        for (int i = 0; i < epoch; i++) {
            int ep = RND.nextInt(0, xs.size() - 1);
            var x = xs.get(ep); var y = ys.get(ep);
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

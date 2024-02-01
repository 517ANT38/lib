package com.ml.optimizer;

import java.util.ArrayList;
import java.util.Collections;
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
        int n = layers.size();
        List<Matrix<Double>> grads = new ArrayList<>(Collections.nCopies(n + 1, null));
        for (int i = 0; i < epoch; i++) {
            int ep = RND.nextInt(0, xs.length - 1);
            Matrix<Double> x = new MatArray(new double[][]{xs[ep]}); 
            Matrix<Double> y = new MatArray(new double[][]{ys[ep]});
            var res = net.getResult(x);
            if(loss.apply(res, y) < eps)
                break;
            grads.set(n, y);
            for (int j = n - 1; j >= 0; j--) {
                var l = layers.get(j);
                var tmp = l.back(grads.get(j + 1), rate);
                var s = grads.get(j);
                if(s != null){
                    grads.set(j, tmp);                
                    final int t = i; 
                    tmp = s.map(u -> u * (1 - iner))
                        .add(tmp.map(u -> u * iner * (t - 1)))
                        .map(u -> u * (-rate));
                }
                grads.set(j, tmp);
            }
        } 
    }

    
}

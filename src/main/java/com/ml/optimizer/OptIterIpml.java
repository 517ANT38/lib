package com.ml.optimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ml.net.Netable;
import com.ml.net.layer.Layerable;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.lossFunction.LossFunction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OptIterIpml implements OptIter  {
    private final Random RND = new Random(); 
    private int epoch;
    private double eps;
    private LossFunction<Double> loss;   


    @Override
    public List<Double> opt(Netable net, double[][] xs, double[][] ys) {
        var layers = net.getLayers();
        List<Double> errors =new ArrayList<>();
        for (int i = 0; i < epoch; i++) {
            int ep = RND.nextInt(0, xs.length - 1);
            Matrix<Double> x = new MatArray(new double[][]{xs[ep]}); 
            Matrix<Double> y = new MatArray(new double[][]{ys[ep]});
            var res = net.getResult(x);
            var err = loss.apply(res, y);
            System.out.println(res);
            errors.add(err);
            if(err < eps)
                break;
            var tmp = y;
            for (int j = layers.size() - 1; j >= 0; j--) {
                var l = layers.get(j);
                tmp = l.back(tmp);
            }
        }
        for (Layerable layerable : layers) {
            layerable.cleanState();
        }
        return errors;
    }
    
}

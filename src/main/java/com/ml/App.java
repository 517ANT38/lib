package com.ml;



import java.util.List;

import com.ml.net.Net;
import com.ml.net.layer.LayerHidden;
import com.ml.net.layer.LayerOutput;
import com.ml.net.layer.Layerable;
import com.ml.optimizer.OptIter;
import com.ml.optimizer.OptIterIpml;
import com.ml.optimizer.util.Adagrad;
import com.ml.optimizer.util.MomentumSGD;
import com.ml.optimizer.util.NAG;
import com.ml.optimizer.util.SGD;
import com.ml.util.activationFunction.LogSigmoid;
import com.ml.util.activationFunction.Sin;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.lossFunction.MeanSquarErr;
import com.ml.util.randomGenMatrix.RandomGeneratorGaussian;

public class App 
{
    public static void main( String[] args )
    {
        var rg = new RandomGeneratorGaussian();
        var func = new LogSigmoid();
        Layerable layerInpt = new LayerHidden(3, 5, func , new Adagrad(0.11, 0.1), rg);
        Layerable layerHid = new LayerHidden(5, 5, func , new Adagrad(0.11, 0.1), rg);
        Layerable layerOut = new LayerOutput(5, 3, func , new Adagrad(0.11, 0.1), rg);
        var lst = List.of(layerInpt,layerHid,layerOut);
        Net net = new Net(lst);
        
        OptIter opt = new OptIterIpml(1000,0.01, new MeanSquarErr());

        System.out.println(net.getResult(new MatArray(new double[][]{{0,1,1}})));
        opt.opt(net, new double[][]{
            {0,0,0},{1,0,1},{0,1,1},{1,1,1}
        },new double[][]{{0,0,0},{1,1,1},{1,1,1},{0,0,0}});
        System.out.println(net.getResult(new MatArray(new double[][]{{0,1,1}})));
       
        // net.serialization("net");
        // var net1 = Net.readCreateNet("net");
        // System.out.println(net.getResult(new MatArray(new double[][]{{0,1}})));
    }
}

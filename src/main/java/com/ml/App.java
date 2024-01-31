package com.ml;



import java.util.List;

import com.ml.net.Net;
import com.ml.optimizer.Optimizer;
import com.ml.optimizer.SGD;
import com.ml.util.activationFunction.LogSigmoid;
import com.ml.util.activationFunction.SingleLeap;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.lossFunction.MeanSquarErr;

public class App 
{
    public static void main( String[] args )
    {
        Net net = new Net(new SingleLeap(),2,5,5,1);
        
        Optimizer opt = new SGD(500, 0.0001, 0.1, new MeanSquarErr());
        opt.opt(net, new double[][]{
            {0,0},{1,0},{0,1},{1,1}
        },new double[][]{{0},{1},{1},{0}});
        System.out.println(net.getResult(new MatArray(new double[][]{{0,0}})));
        net.serialization("net");
        var net1 = Net.readCreateNet("net");
        System.out.println(net.getResult(new MatArray(new double[][]{{0,0}})));
    }
}

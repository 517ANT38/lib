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
        Net net = new Net(new LogSigmoid(),3,34,1,3);
        
        Optimizer opt = new SGD(8, 0.000000001, 0.1, new MeanSquarErr());

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

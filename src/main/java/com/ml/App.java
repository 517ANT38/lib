package com.ml;



import com.ml.net.Net;
import com.ml.optimizer.Optimizer;
import com.ml.optimizer.SGD;
import com.ml.util.activationFunction.SingleLeap;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.lossFunction.MeanSquarErr;

public class App 
{
    public static void main( String[] args )
    {
        Net net = new Net(new SingleLeap(),2,3,1,1);
        
        Optimizer opt = new SGD(100, 0.12, 0.11, new MeanSquarErr());
        opt.opt(net, new MatArray(new double[][]{{0,1},{1,1},{0,0},{1,0}}),
            new MatArray(new double[][]{{1},{0},{0},{1}}));
        System.out.println(net.getResult(new MatArray(new double[][]{{1,1}})));
    }
}

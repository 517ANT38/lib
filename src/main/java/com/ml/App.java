package com.ml;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.ml.net.Net;
import com.ml.net.Netable;
import com.ml.net.layer.LayerHidden;
import com.ml.net.layer.LayerOutput;
import com.ml.net.layer.Layerable;
import com.ml.optimizer.OptIter;
import com.ml.optimizer.OptIterIpml;
import com.ml.optimizer.util.Adagrad;
import com.ml.optimizer.util.MomentumSGD;
import com.ml.optimizer.util.SGD;
import com.ml.util.activationFunction.HyperbolicTg;
import com.ml.util.activationFunction.LogSigmoid;
import com.ml.util.activationFunction.SingleLeap;
import com.ml.util.dataNormolize.Normolizable;
import com.ml.util.dataNormolize.NormolizableIml;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.lossFunction.CosineDistance;
import com.ml.util.lossFunction.CrossEntropy;
import com.ml.util.lossFunction.MeanSquarErr;
import com.ml.util.randomGenMatrix.RandomGeneratorGaussian;
import com.ml.util.randomGenMatrix.RandomGeneratorR;
import com.ml.util.readWriteDataSet.ImageRead;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        var imageRead = new ImageRead();
        Normolizable normalizer = new NormolizableIml(); 
        double[][] set = new double[10][];
        double[][] res = new double[10][10];
        
        for (int i = 0; i < set.length; i++) {            

            set[i] = imageRead.read("example/"+i+".jpg");
            set[i] = normalizer.normolize(set[i]);
        }
        for (int i = 0; i < res.length; i++) {
            
            res[i][i]=1;
        }
       
        var rg = new RandomGeneratorGaussian();
        var func = new LogSigmoid();
        var sgd = new SGD(0.11);
        Layerable input = new LayerHidden(set[0].length, 2, func,sgd,rg);
        Layerable hidden1 = new LayerHidden(2, 2, func,sgd,rg);
        Layerable hidden2 = new LayerHidden(2, 2, func,sgd,rg);
        Layerable out = new LayerOutput(2, 10, func, sgd, rg);

        Netable net = new Net(List.of(input,hidden1,hidden2,out));

        OptIter optIter = new OptIterIpml(100, 1.0764, new MeanSquarErr());

        var errs = optIter.opt(net, set, res);
        for (var double1 : errs) {
            System.out.println(double1);
        }
        // System.out.println(Math.log(0.-1));
        net.serialization("net");
        
        var net1 = Net.readCreateNet("net");       
        System.out.println(net1.getResult(new MatArray(new double[][]{set[8]})));
    }
    
}

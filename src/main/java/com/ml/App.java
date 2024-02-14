package com.ml;



import java.io.IOException;
import java.util.List;

import com.ml.net.Net;
import com.ml.net.Netable;
import com.ml.net.layer.LayerHidden;
import com.ml.net.layer.LayerOutput;
import com.ml.net.layer.Layerable;
import com.ml.optimizer.OptIter;
import com.ml.optimizer.OptIterIpml;
import com.ml.optimizer.util.SGD;
import com.ml.util.activationFunction.LogSigmoid;
import com.ml.util.dataNormolize.Normolizable;
import com.ml.util.dataNormolize.NormolizableIml;
import com.ml.util.lossFunction.MeanSquarErr;
import com.ml.util.randomGenMatrix.RandomGeneratorGaussian;
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
        var sgd = new SGD(0.11);
        var rg = new RandomGeneratorGaussian();
        var func = new LogSigmoid();

        Layerable input = new LayerHidden(set[0].length, 5, func,sgd,rg);
        Layerable hidden = new LayerHidden(5, 5, func,sgd,rg);
        Layerable out = new LayerOutput(5, 10, func, sgd, rg);

        Netable net = new Net(List.of(input,hidden,out));

        OptIter optIter = new OptIterIpml(100, 0.4, new MeanSquarErr());

        optIter.opt(net, set, res);
        net.serialization("net");
    }
    
}

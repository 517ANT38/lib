package com.ml.models;

import java.util.ArrayList;
import java.util.List;

import com.ml.net.Net;
import com.ml.net.Netable;
import com.ml.net.layer.LayerHidden;
import com.ml.net.layer.LayerOutput;
import com.ml.net.layer.Layerable;
import com.ml.optimizer.OptIter;
import com.ml.optimizer.OptIterIpml;
import com.ml.optimizer.util.NAG;
import com.ml.util.activationFunction.LogSigmoid;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.lossFunction.MeanSquarErr;
import com.ml.util.randomGenMatrix.RandomGeneratorR;

public class SimpleModel implements Model {


    
    private OptIter optIter;
    private final Netable net;


    public SimpleModel(String pathfile){
        this.net = Net.readCreateNet(pathfile);
    }    


    public SimpleModel(int countInput,int countHiddenLayer,int countHiddenNeuron,int countOutput,int epoch, double rate,double iner,double eps) {
        
        var rg = new RandomGeneratorR();
        var func = new LogSigmoid();
        List<Layerable> layers = new ArrayList<>();
        Layerable input = new LayerHidden(countInput, countHiddenNeuron, func,new NAG(rate, iner),rg);
        layers.add(input);
        for (int j = 0; j < countHiddenLayer - 1; j++) {
            layers.add(new LayerHidden(countHiddenNeuron, countHiddenNeuron, func,new NAG(rate, iner), rg));
        }         
        Layerable out = new LayerOutput(countHiddenNeuron, countOutput, func, new NAG(rate, iner), rg);
        layers.add(out);
        this.net = new Net(layers);
        this.optIter = new OptIterIpml(epoch, eps, new MeanSquarErr());
    }

    @Override
    public List<Double> fit(double[][] xs, double[][] ys) {
        return optIter.opt(net, xs, ys);
    }

    @Override
    public Matrix<Double> predict(double[] x) {
        return net.getResult(new MatArray(new double[][]{x}));
    }

    @Override
    public void serialization(String pathFile) {
        net.serialization(pathFile);
    }

    @Override
    public List<Layerable> getLayers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLayers'");
    }

    @Override
    public Matrix<Double> getResult(Matrix<Double> input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }

    @Override
    public int[] getParams() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParams'");
    }

    @Override
    public Netable getNet() {
        return net;
    }
    
}

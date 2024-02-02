package com.ml.net;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ml.net.layer.LayerHidden;
import com.ml.net.layer.LayerOutput;
import com.ml.net.layer.Layerable;
import com.ml.optimizer.util.Optimizer;
import com.ml.optimizer.util.SGD;
import com.ml.util.activationFunction.ActivationFunction;
import com.ml.util.activationFunction.LogSigmoid;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.randomGenMatrix.RandomGenerator;
import com.ml.util.randomGenMatrix.RandomGeneratorGaussian;

public class Net implements Netable, Serializable {

    private static final long serialVersionUID = 1L;
    private List<Layerable> layers;
    private transient static final int[] DEFAULT_PARAMS = {3,1,3,1}; // countInput countHiddenLayer countHidNeuron countOutPut
    private int[] params;

    public static Netable readCreateNet(String pathFile){
        try (var iStream = new FileInputStream(pathFile)) {
            try(var oStream = new ObjectInputStream(iStream)){
                return (Net) oStream.readObject();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public Net(List<Layerable> layers) {
        this.layers = layers;
    }

    public Net(ActivationFunction f, RandomGenerator<Double> r, Optimizer opt,  int ... params){
        this.params = Arrays.copyOfRange(params, 0, 4);
        this.layers = new ArrayList<>();
        for (int i = 0; i < this.params.length; i++) {
            this.params[i] = this.params[i] == 0 ? DEFAULT_PARAMS[i]: this.params[i]; 
        }
        layers.add(new LayerHidden(this.params[0], this.params[2], f,opt,r));
        for (int i = 0; i < this.params[1] - 1; i++) {
            layers.add(new LayerHidden(this.params[2], this.params[2], f,opt,r));
        }
        layers.add(new LayerOutput(this.params[2], this.params[3], f,opt,r));
        
    }

    public Net(double rate,int ... params){
        this(new LogSigmoid(), new RandomGeneratorGaussian(), new SGD(rate), params);
    }

    public Net(ActivationFunction f,double rate,int ... params){
        this(f, new RandomGeneratorGaussian(),new SGD(rate), params);
    }

    public Net(ActivationFunction f,Optimizer opt,int ... params){
        this(f, new RandomGeneratorGaussian(),opt, params);
    }

    @Override
    public List<Layerable> getLayers() {
        return layers;
    }

    @Override
    public void serialization(String pathFile) {
        try (var fileStream = new FileOutputStream(pathFile)) {
            try (var oStream = new ObjectOutputStream(fileStream)) {
              oStream.writeObject(this);  
            } 
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Matrix<Double> getResult(Matrix<Double> input) {
        var tmp = input;
        for (var l : layers) {
            tmp = l.ford(tmp);
        }
        return tmp;
    }

    @Override
    public int[] getParams() {
        return this.params;
    }
    
}

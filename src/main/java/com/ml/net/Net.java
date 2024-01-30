package com.ml.net;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ml.net.layer.LayerHidden;
import com.ml.net.layer.Layerable;
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

    public Net(List<Layerable> layers) {
        this.layers = layers;
    }

    public Net(ActivationFunction f, RandomGenerator<Double> r,  int ... params){
        this.params = Arrays.copyOfRange(params, 0, 4);
        this.layers = new ArrayList<>();
        for (int i = 0; i < this.params.length; i++) {
            this.params[i] = this.params[i] == 0 ? DEFAULT_PARAMS[i]: this.params[i]; 
        }
        layers.add(new LayerHidden(this.params[0], this.params[2], f,r));
        for (int i = 0; i < this.params[1] - 1; i++) {
            layers.add(new LayerHidden(this.params[2], this.params[2], f,r));
        }
        layers.add(new LayerHidden(this.params[2], this.params[3], f,r));
    }

    public Net(int ... params){
        this(new LogSigmoid(), new RandomGeneratorGaussian(), params);
    }

    @Override
    public List<Layerable> getLayers() {
        return layers;
    }

    @Override
    public void serialization(String pathFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serialization'");
    }

    @Override
    public Matrix<Double> getResult(Matrix<Double> input) {
        var tmp = input;
        for (var l : layers) {
            tmp = l.ford(tmp);
        }
        return tmp;
    }
    
}

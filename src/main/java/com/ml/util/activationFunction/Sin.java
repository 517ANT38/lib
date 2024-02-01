package com.ml.util.activationFunction;

import java.io.Serializable;

public class Sin implements ActivationFunction, Serializable{

    private static final long serialVersionUID = 1L;

    @Override
    public double apply(double x) {
        return Math.sin(x);
    }

    @Override
    public double difApply(double x) {
        return Math.cos(x);
    }
    
}

package com.ml.util.activationFunction;

import java.io.Serializable;

public class ReLu implements ActivationFunction,Serializable {
    private static final long serialVersionUID = 1L;
    @Override
    public double apply(double x) {
        return Math.max(0, x);
    }

    @Override
    public double difApply(double x) {
        return 0;
    }
    
}

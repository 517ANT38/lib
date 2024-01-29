package com.ml.util.activationFunction;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class SingleLeap implements ActivationFunction,Serializable  {
    private static final long serialVersionUID = 1L;
    @Override
    public double apply(double v) {
        return v >= 0 ? 1 : 0;
    }

    @Override
    public double difApply(double v) {
        return 0;
    }
    
}

package com.ml.util.activationFunction;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class LogSigmoid implements ActivationFunction,Serializable  {
    private static final long serialVersionUID = 1L;
    private final double a;
    private final double b;

    

    public LogSigmoid(){
        this(1,0);
    }

    @Override
    public double apply(double v) {
        return 1 / (1 + Math.exp(a * v)) + b;
    }

    @Override
    public double difApply(double v) {
        var f = apply(v);
        return a * f * (1 - f);
    }

    
    
}
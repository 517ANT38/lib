package com.ml.util.activationFunction;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class HyperbolicTg implements ActivationFunction,Serializable {

    private static final long serialVersionUID = 1L;
    private double a;
    private double b;

    public HyperbolicTg(){
        this(1,0);
    }

    @Override
    public double apply(double x) {
        
        return  Math.tanh(a*x) + b;
    }

    @Override
    public double difApply(double x) {
        double v = Math.cosh(a*x);
        return a /(v * v); 
    }
    
}

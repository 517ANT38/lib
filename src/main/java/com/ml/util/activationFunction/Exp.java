package com.ml.util.activationFunction;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Exp implements ActivationFunction, Serializable {

    private static final long serialVersionUID = 1L;
    private double a;
    private double b;


    @Override
    public double apply(double x) {
        return Math.exp(a * x) + b;
    }

    @Override
    public double difApply(double x) {
        return a * Math.exp(a * x);
    }
    
}

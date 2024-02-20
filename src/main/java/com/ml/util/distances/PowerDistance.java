package com.ml.util.distances;

import com.ml.util.linearAlgebra.Matrix;

public class PowerDistance implements Distable {

    private double p;
    private double r;

    public PowerDistance(double p,double r){
        this.r=r;
        this.p=p;
    }

    public PowerDistance(){
        this(2, 2);
    }

    @Override
    public double distance(Matrix<Double> v1, Matrix<Double> v2) {
        var sum = v1.sub(v2).map(x -> Math.pow(x, p)).sum(0, 1);
        return  Math.pow(sum, 1/r);
    }
    
}

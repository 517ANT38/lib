package com.ml.optimizer;



import com.ml.net.Netable;
import com.ml.util.linearAlgebra.Matrix;

public interface Optimizer {

    void opt(Netable net, Matrix<Double> xs,Matrix<Double> ys);
    
} 
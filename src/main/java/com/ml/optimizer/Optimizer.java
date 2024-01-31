package com.ml.optimizer;



import com.ml.net.Netable;

public interface Optimizer {

    void opt(Netable net, double[][] xs,double[][] ys);
    
} 
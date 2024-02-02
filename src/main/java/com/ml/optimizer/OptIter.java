package com.ml.optimizer;



import com.ml.net.Netable;

public interface OptIter {

    void opt(Netable net, double[][] xs,double[][] ys);
    
} 
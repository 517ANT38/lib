package com.ml.optimizer;



import java.util.List;

import com.ml.net.Netable;

public interface OptIter {

    List<Double> opt(Netable net, double[][] xs,double[][] ys);
    
} 
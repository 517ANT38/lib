package com.ml.optimizer;



import java.util.List;

import com.ml.net.Netable;
import com.ml.util.linearAlgebra.Matrix;

public interface Optimizer {

    void opt(Netable net, List<Matrix<Double>> xs,List<Matrix<Double>> ys);
    
} 
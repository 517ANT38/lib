package com.ml.net;

import java.util.List;

import com.ml.net.layer.Layerable;
import com.ml.util.linearAlgebra.Matrix;

public interface Netable {
    List<Layerable> getLayers();
    void serialization(String pathFile);
    Matrix<Double> getResult(Matrix<Double> input);
}

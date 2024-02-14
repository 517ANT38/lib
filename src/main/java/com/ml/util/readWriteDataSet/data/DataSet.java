package com.ml.util.readWriteDataSet.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSet {
    private double[] x;
    private String y;
}

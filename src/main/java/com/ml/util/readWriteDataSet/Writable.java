package com.ml.util.readWriteDataSet;

import java.util.List;

import com.ml.util.readWriteDataSet.data.DataSet;

public interface Writable {
    
    void write(List<DataSet> set,String path);
}

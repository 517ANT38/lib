package com.ml.util.readWriteDataSet;

import java.util.List;

import com.ml.util.readWriteDataSet.data.DataSet;

public interface Readable {
    List<DataSet> read(String path);
}

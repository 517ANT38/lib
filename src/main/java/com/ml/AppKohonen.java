package com.ml;

import java.io.File;

import com.ml.models.KohonenModel;
import com.ml.util.dataNormolize.Normolizable;
import com.ml.util.dataNormolize.NormolizableIml;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.readWriteDataSet.ImageRead;
import com.ml.util.readWriteDataSet.ReadCSVFileForAirplanes;

/**
 * AppKohonen
 */
public class AppKohonen {

    public static void main(String[] args) {
        
        com.ml.util.readWriteDataSet.Readable readable = new ReadCSVFileForAirplanes();

        var ds = readable.read("data.csv");

        var set = new double[ds.size()][];
        var res = new double[ds.size()][];
        for (int i = 0; i < set.length; i++) {
            set[i] = ds.get(i).getX();
            res[i] = ds.get(i).getYs();
        }
        var mat = new MatArray(set);
        KohonenModel model = new KohonenModel(set[0].length, res[0].length, 0.1,0.8,0.5);
        model.train(mat, 500,0.001);
    }
}
package com.ml;

import com.ml.models.KohonenModel;
import com.ml.util.graphGui.GraphPanel;
import com.ml.util.linearAlgebra.MatArray;
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
        KohonenModel model = new KohonenModel(set[0].length, res[0].length, 0.08,0.08,0.5);
        var errs = model.train(mat, 500,10e-40);
        System.out.println(errs);
        GraphPanel.createGraphics(errs);
    }
}
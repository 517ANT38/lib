package com.ml;

import java.io.File;

import com.ml.models.KohonenModel;
import com.ml.util.dataNormolize.Normolizable;
import com.ml.util.dataNormolize.NormolizableIml;
import com.ml.util.linearAlgebra.MatArray;
import com.ml.util.readWriteDataSet.ImageRead;

/**
 * AppKohonen
 */
public class AppKohonen {

    public static void main(String[] args) {
        
        var imageRead = new ImageRead();
        Normolizable normalizer = new NormolizableIml(); 
                
        File folder = new File("example");
        File[] files = folder.listFiles();
        int n = files.length;
        double[][] set = new double[n][];   
        int i = 0;
        
        for (File file : files) {
            if (file.isFile()) {
                var name = file.getName();
                set[i] = imageRead.read("example/"+name);
                set[i] = normalizer.normolize(set[i]);
                i++;
            }
        }
        var mat = new MatArray(set);
        KohonenModel model = new KohonenModel(set[0].length, 10, 0.1);
        model.train(mat, 100);
    }
}
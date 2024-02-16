package com.ml;



import java.io.File;
import java.io.IOException;
import com.ml.models.SimpleModel;
import com.ml.util.dataNormolize.Normolizable;
import com.ml.util.dataNormolize.NormolizableIml;
import com.ml.util.graphGui.GraphPanel;
import com.ml.util.readWriteDataSet.ImageRead;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        var imageRead = new ImageRead();
        Normolizable normalizer = new NormolizableIml(); 
        
        
        File folder = new File("example");
        File[] files = folder.listFiles();
        int n = files.length;
        double[][] set = new double[n][];
        double[][] res = new double[n][];
   
        int i = 0;
        
        for (File file : files) {
            if (file.isFile()) {
                var name = file.getName();
                set[i] = imageRead.read("example/"+name);
                set[i] = normalizer.normolize(set[i]);
                res[i++] = getFlagValue(name.split(".jpg")[0]);
            }
        }


        var model = new SimpleModel(set[0].length, 9,20,10,1000,0.2,0.15,0.5 );
        
        var errs = model.fit(set, res);
        GraphPanel.createGraphics(errs);
        model.serialization("net");
        
        var test = imageRead.read("example/234.jpg");
        var test1 = imageRead.read("example/0.jpg");
        var net1 = new SimpleModel("net");       
        System.out.println(net1.predict(test));
        System.out.println(net1.predict(test1));
    }
    

    private static double[] getFlagValue(String val){
        int x = Integer.parseInt(val);
        double[] arr = new double[10];

        while (x!=0) {
            arr[x%10]=1;
            x/=10;
        }
        return arr;
    }
}

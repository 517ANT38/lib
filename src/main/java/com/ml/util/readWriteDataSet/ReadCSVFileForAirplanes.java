package com.ml.util.readWriteDataSet;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ml.util.dataNormolize.Normolizable;
import com.ml.util.dataNormolize.NormolizableIml;
import com.ml.util.readWriteDataSet.data.DataSet;

import lombok.SneakyThrows;

public class ReadCSVFileForAirplanes implements Readable {

    
    private Normolizable normolizable;

    private Map<String,Integer> map = Map.of("пушки", 0,"камеры",1,"ракеты",2,"бомбы",3,"авиабомбы",4);
    private Map<String,double[]> map2 = Map.of("Штурмовик", new double[]{1.0,0.0,0.0,0.0},
        "Бомбардировщик",new double[]{0.0,1.0,0.0,0.0},
        "Истребитель",new double[]{0.0,0.0,1.0,0.0},
        "Разведчик",new double[]{0.0,0.0,0.0,1.0});

    public ReadCSVFileForAirplanes(){
        normolizable = new NormolizableIml();
    }

    

    @Override
    @SneakyThrows
    public List<DataSet> read(String path) {
        List<DataSet> rdata = new ArrayList<>();
        String[] data = Files
        .readString(Paths.get(path))
        .split(System.lineSeparator());

        for (String s : data) {
            var row = s.split(" ");
            var ds = new DataSet();
            ds.setY(row[1]);
            ds.setYs(map2.get(row[1]));
            double[] res = new double[10];
            double[] arr = new double[5];
            int j=0;
            for (int i = 2; i < row.length - 1; i++) {
                arr[j++] = Double.parseDouble(row[i]);
            }
            arr = normolizable.normolize(arr);
            var arrS = row[row.length - 1].split(",");
            double[] labels = new double[5];
            for (String string : arrS) {
                labels[map.get(string)]=1.0;
            }
            int k = 0;
            while (k < arr.length - 1) {
                res[k++] = arr[k];
            }
            k=0;
            while (k < arr.length - 1) {
                res[k+5] = labels[k];
                k++;
            }
            ds.setX(res);
            rdata.add(ds);
        }
        
        return rdata;
    }
}

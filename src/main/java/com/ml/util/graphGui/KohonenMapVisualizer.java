package com.ml.util.graphGui;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class KohonenMapVisualizer {

    private final static String FOLDER_MAP = "kohonen_maps";

    public  void visualize(double[][] inputArray, String filename) {
        new File(FOLDER_MAP).mkdir();
        int imageSize = 400;
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
        var max = Arrays.stream(inputArray).mapToDouble(x -> Arrays.stream(x).max().getAsDouble()).max().getAsDouble();
        var min = Arrays.stream(inputArray).mapToDouble(x -> Arrays.stream(x).max().getAsDouble()).max().getAsDouble();
        for (int i = 0; i < imageSize; i++) {
            for (int j = 0; j < imageSize; j++) {
                int x = (int) ((double) i / imageSize * inputArray.length);
                int y = (int) ((double) j / imageSize * inputArray[0].length);
                
                var e =  (inputArray[x][y] - min)/(max - min +0.011);
                Color color = new Color(Math.abs((float)e)/255, Math.abs((float)e)/255, 0.0f);
                
                image.setRGB(i, j, color.getRGB());
            }
        }
        
        try {
            File output = new File(FOLDER_MAP+"/"+filename+".png");
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.ml.util.readWriteDataSet;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import lombok.SneakyThrows;

public class ImageRead {
    
    @SneakyThrows
    public double[] read(String path){
        var b = ImageIO.read(new File(path));
        return convertTo2DWithoutUsingGetRGB(b);
    }
    private static double[] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();

        double[] pixels = new double[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int gray = (pixel & 0xff) < 128 ? 0 : 255; 
                pixels[y * width + x] = gray;
            }
        }
        return pixels;
     }
}

package com.ml.util.readWriteDataSet;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import boofcv.io.image.ConvertBufferedImage;
// import boofcv.io.*;;
import boofcv.struct.image.GrayU8;
import lombok.SneakyThrows;

public class ImageRead {
    
    @SneakyThrows
    public double[] read(String path){
        var b = ImageIO.read(new File(path));
        GrayU8 grayImage = ConvertBufferedImage.convertFromSingle(b, null, GrayU8.class);
        return convertTo2DWithoutUsingGetRGB(grayImage);
    }
    public static double[] convertTo2DWithoutUsingGetRGB(GrayU8 grayImage) {

        int width = grayImage.getWidth();
        int height = grayImage.getHeight();

        // Преобразование в одномерный double массив
        double[] pixels = new double[width * height];
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[index] = grayImage.get(x, y);
                index++;
            }
        }
        return pixels;
     }
}

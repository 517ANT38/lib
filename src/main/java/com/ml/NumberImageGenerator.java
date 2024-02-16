package com.ml;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.ml.models.Model;
import com.ml.models.SimpleModel;
import com.ml.util.dataNormolize.Normolizable;
import com.ml.util.dataNormolize.NormolizableIml;
import com.ml.util.linearAlgebra.Matrix;
import com.ml.util.readWriteDataSet.ImageRead;

import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class NumberImageGenerator extends JFrame {

    private JPanel panel;
    private JLabel numberLabel;
    private JTextField numberField;
    private JButton generateButton;
    private JButton analylizeFileButton;
    private JButton analylizeFrameButton;
    private BufferedImage image;
    private Model model;
    private int number;
    private static final String path = "example";
    private static final int WIDTH = 400; // Ширина окна
    private static final int HEIGHT = 200; // Высота окна
    private static final Font FONT = new Font("Arial", Font.BOLD, 100);
    private static final ImageRead imageRead = new ImageRead();
    private static final Normolizable normalizer = new NormolizableIml(); 

    public NumberImageGenerator() {
        super("Number Image Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        model = new SimpleModel("net");
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        numberLabel = new JLabel("Enter a number: ");
        panel.add(numberLabel);

        numberField = new JTextField(10);
        panel.add(numberField);

        generateButton = new JButton("Generate");
        analylizeFileButton =new JButton("Analyze from file");
        analylizeFrameButton =new JButton("Analyze from frame");

        generateButton.addActionListener(e->generateImage());
        panel.add(generateButton);

        analylizeFileButton.addActionListener(e->anylizeFromFile());
        panel.add(analylizeFileButton);

        analylizeFrameButton.addActionListener(e->anylizeFromFrame());
        panel.add(analylizeFrameButton);

        add(panel);

        setVisible(true);
    }


    private static void drawNum(BufferedImage image,int number){
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, WIDTH, HEIGHT);

        graphics.setFont(FONT);
        graphics.setColor(Color.BLACK);
        String numberString = String.valueOf(number);
        int stringWidth = graphics.getFontMetrics().stringWidth(numberString);
        int x = (WIDTH - stringWidth) / 2;
        int y = HEIGHT / 2;
        graphics.drawString(numberString, x, y);

        graphics.dispose();
    }


    private static double[] readAndNormalize(int num){
        var res = imageRead.read(path+"/"+num+".jpg");
        return normalizer.normolize(res);
    }

    private static double[] readFrameAndNormalize(BufferedImage image){
        GrayU8 grayImage = ConvertBufferedImage.convertFromSingle(image, null, GrayU8.class);
        var res = ImageRead.convertTo2DWithoutUsingGetRGB(grayImage);
        return normalizer.normolize(res);
    }

    private void anylizeFromFrame(){
        var v = readFrameAndNormalize(image);
        var res = vectorToArr(model.predict(v));
        var r = callProcent(res,number);
        generateFrameAnalyze(r);
    }

    private void anylizeFromFile(){
        int num = Integer.parseInt(numberField.getText());
        var v = readAndNormalize(num);
        var res = vectorToArr(model.predict(v));
        var r = callProcent(res,num);
        generateFrameAnalyze(r);
    }


    private static void generateFrameAnalyze(double res){
        JFrame frame = new JFrame("Result analyze");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(250, 50);
        var panel = new JPanel(); 
        panel.setLayout(new FlowLayout());
        var label = new JLabel("Number recognition result: "+ res+"%");
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static double[] vectorToArr(Matrix<Double> m){
        var l = m.getDimensions()[1];
        var res = new double[l];
        for (int i = 0; i < res.length; i++) {
            res[i] = m.get(0, i);
        }
        return res;
    }

    private static int[] positionFlag(int n){
        int[] arr = new int[10];
        while (n!=0) {
            arr[n%10]=1;
            n/=10;
        }
        return arr;
    }

    private static double callProcent(double[] probabilities,int n){
        int count = 0;
        int lenOne = 0;
        var pos = positionFlag(n);
        double threshold = Arrays.stream(probabilities).summaryStatistics().getAverage();        
        for (int i = 0; i < pos.length; i++) {
            if(pos[i] == 1){
                lenOne++;
                if (threshold>=probabilities[i]) {
                    count++;
                }
            }
        }
        
        return  Math.round((double)count / (double)lenOne) * 100;
    }

    @SneakyThrows
    private static void generateImg(int num, BufferedImage image) {
        new File(path).mkdir();
        ImageIO.write(image, "jpg", new File(path+"/"+num+".jpg"));
    }

    private void generateImage() {
        try {
            number = Integer.parseInt(numberField.getText());

            // Create a white image of size 100x100 pixels
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            drawNum(image, number);
            generateImg(number,image);
            // Show the image in a new window
            JFrame imageFrame = new JFrame("Number Image");
            imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            imageFrame.setSize(450, 250);

            JPanel imagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 25, 25, null);
                }
            };

            imageFrame.add(imagePanel);
            imageFrame.setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberImageGenerator();
            }
        });
    }
}


package com.ml.util.graphGui;
import javax.swing.*;

import com.ml.models.KohonenModel;
import com.ml.util.linearAlgebra.Matrix;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

public class KohonenVisualizer extends JPanel {
    private KohonenModel model;

    public KohonenVisualizer(KohonenModel model) {
        this.model = model;

    }
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    new Thread(()->{
        Graphics2D g2d = (Graphics2D) g;

        int cellSize = 50;
        int padding = 10;

        for (int i = 0; i < model.getOutputSize(); i++) {
            for (int j = 0; j < 100; j++) {
                double weight = model.getWeightMatrix().get(i, j);
                Color color = getColorForValue(weight);

                g2d.setColor(color);
                Rectangle2D rectangle = new Rectangle2D.Double(padding + i * cellSize, padding + j * cellSize, cellSize, cellSize);
                g2d.fill(rectangle);
            }
        }
        g2d.dispose();
    }).start();

}

private Color getColorForValue(double value) {
    var v = maxMin(model.getWeightMatrix());
    int intValue = (int) (255.0 * (value-v[1])/(v[0]-v[1]));
    return new Color(intValue, intValue, intValue);
}
private double[] maxMin(Matrix<Double> matrix){
    var rz = matrix.getDimensions();
    double[][] mat = new double[rz[0]][rz[1]];
    for (int i = 0; i < rz[0]; i++) {
        for (int j = 0; j < rz[1]; j++) {
            mat[i][j] = matrix.get(i, j);
        }
    }
    var max = Arrays.stream(mat).map(x->Arrays.stream(x).max())
        .mapToDouble(x -> x.getAsDouble()).max().getAsDouble();
    var min = Arrays.stream(mat).map(x->Arrays.stream(x).min())
        .mapToDouble(x -> x.getAsDouble()).min().getAsDouble();
    return new double[]{max,min};    
}
public void visualize() {
    JFrame frame = new JFrame("Kohonen Map Visualizer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(this);
    frame.setSize(600, 600);
    frame.setVisible(true);
}
}
package com.ml.util.graphGui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ml.models.KohonenModel;
import com.ml.util.linearAlgebra.Matrix;

public class KohonenVisualization extends JPanel {

    private KohonenModel model;
    private List<Color> neuronColors;

    public KohonenVisualization(KohonenModel model) {
        this.model = model;
        neuronColors = new ArrayList<>();

        for (int i = 0; i < model.getOutputSize(); i++) {
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            neuronColors.add(new Color(r, g, b));
        }
    }

    public void visualize(Matrix<Double> inputMatrix) {
        JFrame frame = new JFrame("Kohonen Map Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);

        int gridSize = 50;
        int padding = 20;

        int rows = model.getOutputSize();
        int columns = model.getInputSize();

        int mapWidth = gridSize * rows + padding * 2;
        int mapHeight = gridSize * columns + padding * 2;
        
        int startX = (getWidth() - mapWidth) / 2;
        int startY = (getHeight() - mapHeight) / 2;

        Graphics g = getGraphics();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int x = startX + i * gridSize;
                int y = startY + j * gridSize;
                int winnerIndex = model.predict(inputMatrix);
                g.setColor(neuronColors.get(winnerIndex));
                g.fillRect(x, y, gridSize, gridSize);
            }
        }
        paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}


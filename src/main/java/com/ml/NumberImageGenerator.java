package com.ml;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class NumberImageGenerator extends JFrame {

    private JPanel panel;
    private JLabel numberLabel;
    private JTextField numberField;
    private JButton generateButton;
    private BufferedImage image;

    private static final int WIDTH = 400; // Ширина окна
    private static final int HEIGHT = 200; // Высота окна
    private static final Font FONT = new Font("Arial", Font.BOLD, 100);

    public NumberImageGenerator() {
        super("Number Image Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        numberLabel = new JLabel("Enter a number: ");
        panel.add(numberLabel);

        numberField = new JTextField(10);
        panel.add(numberField);

        generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateImage();
            }
        });
        panel.add(generateButton);

        add(panel);

        setVisible(true);
    }

    private void generateImage() {
        try {
            int number = Integer.parseInt(numberField.getText());

            // Create a white image of size 100x100 pixels
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
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

            // Show the image in a new window
            JFrame imageFrame = new JFrame("Number Image");
            imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            imageFrame.setSize(150, 150);

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


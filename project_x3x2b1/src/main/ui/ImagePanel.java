package ui;

import javax.swing.*;

//This method is adapted from Lab1-Photoviewer CPSC 210.
//represents an image panel that contains a happy face
public class ImagePanel extends JPanel {
    private static final String IMAGE_FILE = "smiling-face-with-sunglasses_1f60e (1).png";

    public ImagePanel() {
        ImageIcon icon = new ImageIcon(IMAGE_FILE);
        JLabel label = new JLabel();
        label.setIcon(icon);
        this.add(label);
    }
}

package ui;

import javax.swing.*;

//This method is adapted from Lab1-Photoviewer CPSC 210.
//represents an image panel that has a sad face
public class ImageSadFacePanel extends JPanel {
    private static final String IMAGE_FILE = "sadface.png";

    public ImageSadFacePanel() {
        ImageIcon icon = new ImageIcon(IMAGE_FILE);
        JLabel label = new JLabel();
        label.setIcon(icon);
        this.add(label);
    }
}

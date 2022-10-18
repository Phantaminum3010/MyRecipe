package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private JButton addRecipeButton;
    private JButton  viewRecipeButton;
    private JButton deleteRecipeButton;
    private JButton saveRecipeButton;
    private JButton loadRecipeButton;
    private static final String IMAGE_FILE = "smiling-face-with-sunglasses_1f60e (1).png";

    //A portion of this code is taken from Lab1-Photoviewer
    public MainMenuPanel() {
        ImageIcon icon = new ImageIcon(IMAGE_FILE);
        JLabel label = new JLabel();
        label.setIcon(icon);
        this.add(label);
        viewRecipeButton = this.addAButton(
                "View all the recipes", "Show the panel to view all the recipes", this);
        addRecipeButton = this.addAButton(
                "Add a new recipe", "Show the panel to add the recipes", this);
        deleteRecipeButton = this.addAButton(
                "Delete one recipe", "Show the panel to delete a recipe", this);
        saveRecipeButton = this.addAButton(
                "Save this menu to the list", "Show the panel to save this menu", this);
        loadRecipeButton = this.addAButton(
                "Load a recipe from the list", "Show the panel to load a recipe list", this);
    }

    //Taken from BoxLayOutDemoProject
    //EFFECTS: adds a button of text text and actionCommand actionCommand to container, returns the button
    public static JButton addAButton(String text, String actionCommand, Container container) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        container.add(button);
        return button;
    }

    //EFFECTS: returns a list of buttons that MainMenuPanel contains
    public List<JButton> getButtons() {
        List<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(viewRecipeButton);
        buttonList.add(addRecipeButton);
        buttonList.add(deleteRecipeButton);
        buttonList.add(saveRecipeButton);
        buttonList.add(loadRecipeButton);
        return buttonList;
    }

}

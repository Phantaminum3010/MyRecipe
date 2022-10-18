package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//represents a panels with a list of buttons for which we can perform several actions regarding loading a recipe
public class LoadRecipePanel extends JPanel {
    private JTextField fileName;
    private JButton loadRecipe;
    private JButton backToMenu;

    private JLabel message;

    public LoadRecipePanel() {
        message = new JLabel();
        fileName = new JTextField();
        AddRecipePanel.addRow("Enter file's name: ", fileName, this);
        addButtons();
        add(message);
    }

    //MODIFIES: this
    //EFFECTS: adds the buttons backToMenu and loadRecipe to the container
    public void addButtons() {
        backToMenu = MainMenuPanel.addAButton("Back", "Back to Menu", this);
        loadRecipe = MainMenuPanel.addAButton("Load", "Load a recipe list", this);
    }

    //EFFECTS: returns a list of buttons that LoadRecipePanel contains
    public List<JButton> getButtons() {
        List<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(backToMenu);
        buttonList.add(loadRecipe);
        return buttonList;
    }

    public JTextField getFileName() {
        return this.fileName;
    }

    public JLabel getMessage() {
        return this.message;
    }

}

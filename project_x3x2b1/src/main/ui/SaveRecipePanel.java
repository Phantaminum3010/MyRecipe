package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//represents a panels with a list of buttons for which we can perform several actions regarding saving a recipe
public class SaveRecipePanel extends JPanel {
    private JTextField fileName;
    private JButton saveRecipe;
    private JButton backToMenu;

    private JLabel message;

    public SaveRecipePanel() {
        message = new JLabel();
        fileName = new JTextField();
        AddRecipePanel.addRow("Enter file's name: ", fileName, this);
        addButtons();
        add(message);
    }

    //MODIFIES: this
    //EFFECTS: adds the buttons backToMenu and saveRecipe to the container
    public void addButtons() {
        backToMenu = MainMenuPanel.addAButton("Back", "Back to Menu", this);
        saveRecipe = MainMenuPanel.addAButton("Save", "Save this recipe list", this);
    }

    //EFFECTS: returns a list of buttons in SaveRecipePanel
    public List<JButton> getButtons() {
        List<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(backToMenu);
        buttonList.add(saveRecipe);
        return buttonList;
    }

    public JTextField getFileName() {
        return this.fileName;
    }

    public JLabel getMessage() {
        return this.message;
    }

}


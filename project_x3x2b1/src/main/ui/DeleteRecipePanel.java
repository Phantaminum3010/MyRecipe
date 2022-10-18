package ui;

import javax.swing.*;
import ui.AddRecipePanel;

import java.util.ArrayList;
import java.util.List;

//represents a panels with a list of buttons for which we can perform several actions regarding deleting a recipe
public class DeleteRecipePanel extends JPanel {
    private JTextField recipeName;
    private JButton backToMenu;
    private JButton deleteRecipe;
    private JLabel message;

    public DeleteRecipePanel() {
        message = new JLabel();
        recipeName = new JTextField();
        AddRecipePanel.addRow("Enter recipe's name: ", recipeName, this);
        addButtons();
        add(message);
    }

    //MODIFIES: this
    //EFFECTS: adds the buttons backToMenu and deleteRecipe to the container
    public void addButtons() {
        backToMenu = MainMenuPanel.addAButton("Back", "Back to Menu", this);
        deleteRecipe = MainMenuPanel.addAButton("Delete", "Delete a recipe from the menu", this);
    }

    //EFFECTS: returns a list of buttons that AddRecipePanel contains
    public List<JButton> getButtons() {
        List<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(backToMenu);
        buttonList.add(deleteRecipe);
        return buttonList;
    }

    public JTextField getRecipeName() {
        return this.recipeName;
    }

    public JLabel getMessage() {
        return this.message;
    }
}

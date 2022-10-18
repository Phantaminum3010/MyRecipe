package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//represents a panels with a list of buttons for which we can perform several actions regarding finding a recipe
public class FindRecipePanel extends JPanel {
    private JTextField recipeID;
    private JButton findRecipe;

    private JLabel message;

    public FindRecipePanel() {
        message = new JLabel();
        recipeID = new JTextField();
        AddRecipePanel.addRow("Enter recipe's ID to see its details: ", recipeID, this);
        addButtons();
        add(message);
    }

    //MODIFIES: this
    //EFFECTS: adds the buttons backToMenu and findRecipe to the container
    public void addButtons() {
        findRecipe = MainMenuPanel.addAButton("Find", "Find a recipe from the menu", this);
    }

    //EFFECTS: returns a list of buttons that FindRecipePanel contains
    public List<JButton> getButtons() {
        List<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(findRecipe);
        return buttonList;
    }

    public JTextField getRecipeID() {
        return this.recipeID;
    }

    public JLabel getMessage() {
        return this.message;
    }

}

package ui;

import exception.NegativeAmountException;
import model.Ingredient;
import model.Recipe;
import ui.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//represents a panels with a list of buttons for which we can perform several actions regarding adding a recipe
public class AddRecipePanel extends JPanel {
    private JTextField recipeName;

    private List<JTextField> ingredientNames;
    private List<JTextField> ingredientAmounts;

    private static final int numberOfIngredients = 7;

    private Recipe recipe;

    private JButton addRecipe;
    private JButton backToMenu;


    private JLabel message;

    public AddRecipePanel() {
        this.setLayout(new GridLayout(11, 11));
        message = new JLabel();
        recipe = new Recipe("Pizza pepperoni");
        recipeName = new JTextField();
        ingredientAmounts = new ArrayList<>();
        ingredientNames = new ArrayList<>();
        for (int i = 0; i < numberOfIngredients; i++) {
            ingredientNames.add(new JTextField());
            ingredientAmounts.add(new JTextField());
        }
        addRecipe = new JButton();
        backToMenu = new JButton();
        this.addDisplayOfIngredientNameAndAmount();
        this.addButtons();
    }

    //EFFECTS: adds a list of JPanels of form instruction: textField to this panel
    public void addDisplayOfIngredientNameAndAmount() {
        for (int i = 0; i < numberOfIngredients; i++) {
            this.addRow("Enter ingredient's name: ", ingredientNames.get(i), this);
            this.addRow("Enter ingredient's amount: ", ingredientAmounts.get(i), this);
        }
        this.addRow("Enter recipe's name: ", recipeName, this);
        add(message);
    }

    //MODIFIES: this
    //EFFECTS: adds the buttons backToMenu and addRecipe to the container
    public void addButtons() {
        backToMenu = MainMenuPanel.addAButton("Back", "Back to Menu", this);
        addRecipe = MainMenuPanel.addAButton("Add", "Add a recipe to the menu", this);
    }

    //EFFECTS: returns a panel in the form s: textField, also adds the panel to the container
    public static JPanel addRow(String s, JTextField textField, Container container) {
        JPanel panel = new JPanel(new GridLayout(2,2));
        JLabel label = new JLabel(s);
        panel.add(label);
        panel.add(textField);
        container.add(panel);
        return panel;
    }

    //EFFECTS: if s can be converted to a double value d, return d; else, return 0
    public double convert(String s) {
        try {
            Double d = Double.parseDouble(s);
            return d;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds all the ingredients and their corresponding amount to recipe, and then returns it
    public Recipe getRecipe() throws NegativeAmountException {
        recipe = new Recipe(recipeName.getText());
        for (int i = 0; i < ingredientNames.size(); i++) {
            recipe.addIngredient(
                    new Ingredient(ingredientNames.get(i).getText(), convert(ingredientAmounts.get(i).getText())));
        }
        return recipe;
    }


    //EFFECTS: returns a list of buttons that AddRecipePanel contains
    public java.util.List<JButton> getButtons() {
        List<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(backToMenu);
        buttonList.add(addRecipe);
        return buttonList;
    }

    //MODIFIES: this
    //EFFECTS: returns message
    public JLabel getMessage() {
        return this.message;
    }

    //MODIFIES: this
    //EFFECTS: returns recipeName
    public JTextField getRecipeName() {
        return this.recipeName;
    }


}

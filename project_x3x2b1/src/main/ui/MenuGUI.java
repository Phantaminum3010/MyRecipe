package ui;

import exception.NegativeAmountException;
import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


//This class is programmed based on the TellerApp Application
//This class uses the code written in the WorkRoomApp in JSonSerializationDemo to write saveMenu and loadMenu
//methods

public class MenuGUI extends JFrame implements ActionListener {
    private String jsonStore;

    private RecipeList recipeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Recipe recipe;

    private JPanel panel;
    private MainMenuPanel mainMenuPanel;
    private ViewRecipePanel viewRecipesPanel;
    private FindRecipePanel findRecipePanel;
    private AddRecipePanel addRecipePanel;
    private DeleteRecipePanel deleteRecipePanel;
    private SaveRecipePanel saveRecipePanel;
    private LoadRecipePanel loadRecipePanel;
    private ImagePanel imagePanel;
    private ImageSadFacePanel imageSadFacePanel;

    private final String mainMenuPanelName = "MenuApp";
    private final String viewRecipePanelName = "ViewRecipe";
    private final String findRecipePanelName = "FindRecipe";
    private final String addRecipePanelName = "AddRecipe";
    private final String deleteRecipePanelName = "DeleteRecipe";
    private final String saveRecipePanelName = "SaveRecipe";
    private final String loadRecipePanelName = "LoadRecipe";


    //MODIFIES: this
    //EFFECTS: constructs MenuApp and run Application
    public MenuGUI() throws FileNotFoundException {
        super("MenuApp");
        recipeList = new RecipeList();
        recipe = new Recipe("");
        panel = new JPanel();
        mainMenuPanel = new MainMenuPanel();
        viewRecipesPanel = new ViewRecipePanel();
        findRecipePanel = new FindRecipePanel();
        addRecipePanel = new AddRecipePanel();
        deleteRecipePanel = new DeleteRecipePanel();
        saveRecipePanel = new SaveRecipePanel();
        loadRecipePanel = new LoadRecipePanel();
        imagePanel = new ImagePanel();
        imageSadFacePanel = new ImageSadFacePanel();

        jsonStore = "./data/recipe.json";
        this.jsonWriter = new JsonWriter(jsonStore);
        this.jsonReader = new JsonReader(jsonStore);
        init();
        createGUI();
    }


    //Taken from ListDemoProject
    public void createGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 10000));


        setUpContentPane();

        frame.add(panel);

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }

    public void setUpContentPane() {
        panel = new JPanel(new CardLayout());
        addActionToButtons(mainMenuPanel.getButtons());
        addActionToButtons(addRecipePanel.getButtons());
        addActionToButtons(deleteRecipePanel.getButtons());
        addActionToButtons(viewRecipesPanel.getButtons());
        addActionToButtons(findRecipePanel.getButtons());
        viewRecipesPanel.add(findRecipePanel, findRecipePanelName);
        addActionToButtons(saveRecipePanel.getButtons());
        addActionToButtons(loadRecipePanel.getButtons());

        panel.add(mainMenuPanel, mainMenuPanelName);
        panel.add(addRecipePanel, addRecipePanelName);
        panel.add(deleteRecipePanel, deleteRecipePanelName);
        panel.add(viewRecipesPanel, viewRecipePanelName);
        panel.add(saveRecipePanel, saveRecipePanelName);
        panel.add(loadRecipePanel, loadRecipePanelName);
    }


    public void addActionToButtons(List<JButton> buttons) {
        for (JButton button: buttons) {
            button.addActionListener(this);
        }
    }


    //MODIFIES: this
    //EFFECTS: display a default list of recipes
    public void init() {
        Recipe recipe1 = new Recipe("Kung-pao chicken");
        Recipe recipe2 = new Recipe("Spaghetti Carbonara");
        Ingredient ingredient1 = new Ingredient("Chicken's breast", 500.0);
        Ingredient ingredient2 = new Ingredient("White wine", 28.0);
        Ingredient ingredient3 = new Ingredient("Soy sauce", 28.0);
        Ingredient ingredient4 = new Ingredient("Sesame oil", 28.0);
        Ingredient ingredient5 = new Ingredient("Corn starch", 28.0);
        Ingredient ingredient6 = new Ingredient("Chilli paste", 28.0);
        Ingredient ingredient7 = new Ingredient("Pasta", 300.0);

        try {
            recipe1.addIngredient(ingredient1);
            recipe1.addIngredient(ingredient2);
            recipe1.addIngredient(ingredient3);
            recipe1.addIngredient(ingredient4);
            recipe1.addIngredient(ingredient5);
            recipe1.addIngredient(ingredient6);
            recipe2.addIngredient(ingredient7);
        } catch (NegativeAmountException e) {
            System.out.println("Invalid ingredients added!");
        }


        recipeList.addRecipe(recipe1);
        recipeList.addRecipe(recipe2);
    }

    //EFFECTS: display a list of recipes to the menu
    public void displayMenu() {
        if (recipeList.getRecipeList().size() == 0) {
            viewRecipesPanel.getMessage().setText("The recipe is empty!");
        } else {
            String text = "<html>";
            for (int i = 0; i < recipeList.getRecipeList().size(); i++) {
                text += (i + 1) + ". " +  recipeList.getRecipeList().get(i).getRecipeName() + "<br>";
            }
            text += "</html>";

            viewRecipesPanel.getMessage().setText(text);
        }
    }


    public void viewRecipe() {
        int recipeID = 0;
        try {
            recipeID = Integer.parseInt(findRecipePanel.getRecipeID().getText());
            for (int i = 0; i < recipeList.getRecipeList().size(); i++) {
                if (recipeID == (i + 1)) {
                    recipe = recipeList.getRecipeList().get(i);
                }
            }
            if (recipeID == 0 || recipeID > recipeList.getRecipeList().size()) {
                findRecipePanel.getMessage().setText("Sorry, there is no such recipe in the list");
            } else {
                String text = "<html>";
                for (Ingredient i : recipe.getIngredients()) {
                    if (i.getAmount() != 0 && i.getIngredientName() != null) {
                        text += i.getIngredientName() + ": " + i.getAmount() + " grams" + "<br>";
                    }
                }
                text += "</html>";
                findRecipePanel.getMessage().setText(text);
            }
        } catch (NumberFormatException e) {
            findRecipePanel.getMessage().setText("Sorry, please input the index of the recipe to know its details!");
        }
    }

    //MODIFIES: this
    //EFFECTS: add a new recipe to a list of recipes
    public void addNewRecipe() {
        String name = addRecipePanel.getRecipeName().getText();
        List<Ingredient> ingredients = null;
        try {
            ingredients = addRecipePanel.getRecipe().getIngredients();
        } catch (NegativeAmountException e) {
            addRecipePanel.getMessage().setText("Sorry, you entered a negative amount for 1 of the ingredients!");
            return;
        }
        Recipe recipe = new Recipe(name);
        for (int i = 0; i < ingredients.size(); i++) {
            recipe.getIngredients().add(ingredients.get(i));
        }

        if (recipe.getRecipeName().equals("")) {
            addRecipePanel.getMessage().setText("Sorry, but you forgot to enter the recipe's name!");
            addRecipePanel.add(imageSadFacePanel);
        } else {
            recipeList.addRecipe(recipe);
            addRecipePanel.getMessage().setText("This recipe is added to the recipe list!");
            addRecipePanel.add(imagePanel);
        }
    }


    //MODIFIES: this
    //EFFECTS: delete a recipe from the list of recipes if it's already there,
    //         otherwise, announce the user that the recipe he or she wanted to delete is not in the list of recipes
    public void deleteRecipe() {
        String name = deleteRecipePanel.getRecipeName().getText();
        for (Recipe r: recipeList.getRecipeList()) {
            if (r.getRecipeName().equals(name)) {
                recipeList.removeRecipe(r);
                deleteRecipePanel.getMessage().setText("Deleted recipe " + name + " from the current menu!");
                deleteRecipePanel.add(imagePanel);
                return;
            } else {
                deleteRecipePanel.getMessage().setText("There is no such recipe in the recipe list");
                deleteRecipePanel.remove(imagePanel);
                deleteRecipePanel.add(imageSadFacePanel);
            }
        }
    }

    //EFFECTS: save the current menu to file
    public void saveMenu() {
        //System.out.println("Please choose a name for your recipeList: \n");
        //String command = input.nextLine();
        String name = saveRecipePanel.getFileName().getText();
        this.jsonStore = "./data/" + name + ".json";
        this.jsonWriter = new JsonWriter(jsonStore);
        try {
            jsonWriter.open();
            jsonWriter.write(recipeList);
            jsonWriter.close();
            saveRecipePanel.getMessage().setText("Saved recipeList" + " to " + jsonStore);
            saveRecipePanel.add(imagePanel);
        } catch (FileNotFoundException e) {
            saveRecipePanel.getMessage().setText("Unable to write to file: " + jsonStore);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the menu from file
    public void loadMenu() {
        String name = loadRecipePanel.getFileName().getText();
        this.jsonStore = "./data/" + name + ".json";
        this.jsonReader = new JsonReader(jsonStore);
        try {
            recipeList = jsonReader.read();
            loadRecipePanel.getMessage().setText("Loaded recipeList" + " from " + jsonStore);
            loadRecipePanel.add(imagePanel);
        } catch (IOException | NegativeAmountException e) {
            loadRecipePanel.getMessage().setText("Unable to read from file: " + jsonStore);
            loadRecipePanel.add(imageSadFacePanel);
        }
    }

    //This method is taken from
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Show the panel to view all the recipes")) {
            viewCurrentPanel(viewRecipePanelName);
            displayMenu();
        } else if (action.equals("Show the panel to find a recipe")) {
            viewCurrentPanel(findRecipePanelName);
        } else if (action.equals("Show the panel to add the recipes")) {
            viewCurrentPanel(addRecipePanelName);
        } else if (action.equals("Show the panel to delete a recipe")) {
            viewCurrentPanel(deleteRecipePanelName);
        } else if (action.equals("Show the panel to save this menu")) {
            viewCurrentPanel(saveRecipePanelName);
        } else if (action.equals("Show the panel to load a recipe list")) {
            viewCurrentPanel(loadRecipePanelName);
        } else if (action.equals("Back to Menu")) {
            viewCurrentPanel(mainMenuPanelName);
        } else if (action.equals("Add a recipe to the menu")) {
            addNewRecipe();
        } else if (action.equals("Delete a recipe from the menu")) {
            deleteRecipe();
        }
        continueActionPerformed(e);
    }


    //This method is taken from
    //https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    public void continueActionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Find a recipe from the menu")) {
            viewRecipe();
        } else if (action.equals("Save this recipe list")) {
            saveMenu();
        } else if (action.equals("Load a recipe list")) {
            loadMenu();
        }
    }


    //Taken from https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
    public void viewCurrentPanel(String name) {
        CardLayout cardLayout = (CardLayout) (panel.getLayout());
        cardLayout.show(panel, name);
    }

}



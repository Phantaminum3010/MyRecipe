package ui;

import exception.NegativeAmountException;
import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


//This class is programmed based on the TellerApp Application
//This class uses the code written in the WorkRoomApp in JSonSerializationDemo to write saveMenu and loadMenu
//methods

public class MenuApp2 {
    private String jsonStore;

    private Scanner input;
    private RecipeList recipeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //MODIFIES: this
    //EFFECTS: constructs MenuApp and run Application
    public MenuApp2() throws FileNotFoundException {
        input = new Scanner(System.in);
        recipeList = new RecipeList();
        jsonStore = "./data/recipe.json";
        this.jsonWriter = new JsonWriter(jsonStore);
        this.jsonReader = new JsonReader(jsonStore);
        runMenu();
    }

    //MODIFIES: this
    //EFFECTS: processes the user's input
    //Citation: This method is based on the runTeller method in the TellerApp Application
    public void runMenu() {
        String command;
        System.out.println("Hello! Welcome to your own menu!");
        System.out.println("Please follow the instructions below to keep going: \n");

        init();

        while (true) {
            instruct();
            command = input.nextLine();

            if (command.equals("exit")) {
                break;
            } else {
                continueProcessing(command);
            }
        }
        System.out.println("Goodbye, see you later!");
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
            System.out.println("NegativeAmountException caught!");
        }



        recipeList.addRecipe(recipe1);
        recipeList.addRecipe(recipe2);
    }

    //EFFECTS: display a list of recipes to the menu
    public void displayMenu() {
        for (int i = 0; i <= recipeList.getRecipeList().size() - 1; i++) {
            System.out.println(Integer.toString(i + 1) + "." + recipeList.getRecipeList().get(i).getRecipeName());
        }
    }

    //EFFECTS: display a menu of options for the users
    public void instruct() {
        System.out.println("To view the recipe's list, press view\n");
        System.out.println("To add a new recipe to the list, press add\n");
        System.out.println("To delete a recipe from the list, press delete\n");
        System.out.println("To exit from the program, press exit\n");
        System.out.println("To save the current menu to a file, press save\n");
        System.out.println("To load a menu from file, press load\n");
    }

    //MODIFIES: this
    //EFFECTS: processes user's command
    public void continueProcessing(String command) {
        if (command.equals("view")) {
            displayMenu();
            recipeChoice();
        } else if (command.equals("add")) {
            addRecipe();
        } else if (command.equals("delete")) {
            deleteRecipe();
        } else if (command.equals("save")) {
            saveMenu();
        } else if (command.equals("load")) {
            loadMenu();
        } else {
            System.out.println("You have entered an invalid command. Please choose again!!!");
        }
    }

    //EFFECTS: asks a user whether he or she wants to see a specific recipe in the list
    public void recipeChoice() {
        System.out.println("\nWould you like to view a specific recipe?");
        System.out.println("If yes, press 1, otherwise press 0\n");
        String newCommand = null;
        newCommand = input.nextLine();
        if (newCommand.equals("1")) {
            viewRecipe();

//            System.out.println("\nWould you like to adjust any ingredient from this list of ingredients?");
//            System.out.println("If yes, press 1, otherwise press 0");
//
//            String adjustIng = null;
//            adjustIng = input.nextLine();
//
//            if (adjustIng.equals("1")) {
//                adjustIngredient();
//            }
        }

    }

    //EFFECTS: displays a list of ingredients with their amounts needed for a recipe
    public void viewRecipe() {
        System.out.println("Please enter the index of the recipe that you want to view");
        String viewRecipe = input.nextLine();
        int index = Integer.parseInt(viewRecipe);
        if (index >= 1 && index <= recipeList.getRecipeList().size()) {
            System.out.println("The name of this recipe is: "
                    + recipeList.getRecipeList().get(index - 1).getRecipeName() + "\n");
            System.out.println("The ingredients of this recipe are: ");
            List<Ingredient> ingredients = recipeList.getRecipeList().get(index - 1).getIngredients();
            for (int i = 0; i < ingredients.size(); i++) {
                System.out.println(ingredients.get(i).getIngredientName()
                        + " :" + ingredients.get(i).getAmount() + " grams");
            }
        }
    }

//    public void adjustIngredient() {
//    }

    //MODIFIES: this
    //EFFECTS: adds a new recipe to the list of recipes
    public void addRecipe() {
        System.out.println("Please enter the name of the recipe that you want to add: ");
        String newRecipe = input.nextLine();
        Recipe recipe = new Recipe(newRecipe);
        System.out.println("Please enter the number of ingredients needed for this recipe: ");
        String num = input.nextLine();
        int number = Integer.parseInt(num);
        for (int i = 1; i <= number; i++) {
            System.out.println(i + ". Ingredient's name: ");
            String name = input.nextLine();
            System.out.println("Ingredient's amount: ");
            double amount = Double.parseDouble(input.nextLine());
            Ingredient newIngredient = new Ingredient(name, amount);
            try {
                recipe.addIngredient(newIngredient);
            } catch (NegativeAmountException e) {
                System.out.println("Invalid amount entered!");
            }
        }
        recipeList.addRecipe(recipe);
        System.out.println(recipeList.getRecipeList().size());
    }

    //MODIFIES: this
    //EFFECTS: delete a recipe from the list of recipes if it's already there,
    //         otherwise, announce the user that the recipe he or she wanted to delete is not in the list of recipes

    public void deleteRecipe() {
        System.out.println("Please enter the name of the recipe you want to delete: ");
        boolean remove = false;
        String newRecipe = input.nextLine();
        for (int i = 0; i < recipeList.getRecipeList().size(); i++) {
            if (newRecipe.equals(recipeList.getRecipeList().get(i).getRecipeName())) {
                recipeList.removeRecipe(recipeList.getRecipeList().get(i));
                remove = true;
            }
        }

        if (!remove) {
            System.out.println("The recipe that you entered doesn't exist in this recipe list");
        }

    }

    //EFFECTS: save the current menu to file
    public void saveMenu() {
        System.out.println("Please choose a name for your recipeList: \n");
        String command = input.nextLine();
        this.jsonStore = "./data/" + command + ".json";
        this.jsonWriter = new JsonWriter(jsonStore);
        try {
            jsonWriter.open();
            jsonWriter.write(recipeList);
            jsonWriter.close();
            System.out.println("Saved recipeList" + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the menu from file
    public void loadMenu() {
        System.out.println("Please enter the name of the file you want to load: \n");
        String command = input.nextLine();
        this.jsonStore = "./data/" + command + ".json";
        this.jsonReader = new JsonReader(jsonStore);
        try {
            recipeList = jsonReader.read();
            System.out.println("Loaded recipeList" + " from " + jsonStore);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStore);
        } catch (NegativeAmountException e) {
            System.out.println("There's a problem with file: " + jsonStore);
        }
    }




}


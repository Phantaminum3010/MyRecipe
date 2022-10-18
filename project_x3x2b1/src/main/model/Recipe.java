package model;

import exception.NegativeAmountException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents a specific recipe having an index, a name, and a list of ingredients
public class Recipe implements Writable {
    private static int nextRecipeID = 1;
    private String recipeName;
    private int id;
    private List<Ingredient> ingredients;

    /*
    *REQUIRES: recipeName has a non-zero length
    * EFFECTS: the recipe's name is set to recipeName, recipe's id is
    *          a positive integer not set to any other recipes
     */


    public Recipe(String recipeName) {
        this.recipeName = recipeName;
        this.id += nextRecipeID;
        this.ingredients = new ArrayList<>();
    }


    //EFFECTS: returns the id of a recipe
    public int getId() {
        return id;
    }

    //EFFECTS: returns the name of a recipe
    public String getRecipeName() {
        return recipeName;
    }


    //EFFECTS: returns the list of ingredients of a recipe
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    //MODIFIES: this
    // EFFECTS: adds an ingredient to the list of ingredients of a recipe if it's not there,
    //          only update the amount(added) if the ingredient is already there

    public void addIngredient(Ingredient ingredient) throws NegativeAmountException {
        boolean existed = false;
        for (int i = 0; i <= ingredients.size() - 1; i++) {
            if (ingredients.get(i).getIngredientName().equals(ingredient.getIngredientName())) {
                ingredients.get(i).addAmount(ingredient.getAmount());
                existed = true;
            }
        }

        if (ingredient.getAmount() < 0) {
            throw new NegativeAmountException();
        }

        if (!existed) {
            ingredients.add(ingredient);
        }
    }




    //MODIFIES: this
    //EFFECTS: remove an ingredient from ingredients if it's already there,
    //         does nothing if it's not in ingredients

    public void removeIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            ingredients.remove(ingredient);
        }
    }

    @Override
    //NOTES: this method is adapted from the code written in Thingy class in  JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Recipe's name", recipeName);
        json.put("Ingredients", ingredientsToJSon());
        return json;
    }

    // EFFECTS: returns things in the ingredients list as a JSON array
    //NOTES: this method is adapted from the code written in Workroom class in  JsonSerializationDemo
    private JSONArray ingredientsToJSon() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : ingredients) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }


}

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class RecipeList implements Writable {
    private List<Recipe> recipeList;

    //EFFECTS: creates an empty recipeList
    public RecipeList() {
        this.recipeList = new ArrayList<>();
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    //MODIFIES: this
    //EFFECTS: adds a recipe to the recipeList if it's not there,
    //         does nothing if the recipe is already in the recipeList
    public void addRecipe(Recipe recipe) {
        if (!recipeList.contains(recipe)) {
            recipeList.add(recipe);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a recipe from the recipeList if it's already there,
    //         does nothing if the recipe is not in the recipeList
    public void removeRecipe(Recipe recipe) {
        if (recipeList.contains(recipe)) {
            recipeList.remove(recipe);
        }
    }

    // EFFECTS: returns things in the recipe list as a JSON array
    private JSONArray ingredientsToJSon() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipeList) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }


    @Override
    //NOTES: this method is adapted from the code written in Workroom class in  JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Recipes", ingredientsToJSon());
        return jsonObject;
    }
}

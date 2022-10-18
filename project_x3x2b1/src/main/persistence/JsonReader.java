package persistence;

import exception.NegativeAmountException;
import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

//This class is a modification of the JsonReader class of the JsonSerializationDemo project
//Represents a reader that reads a recipe list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads RecipeList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeList read() throws IOException, NegativeAmountException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    //EFFECTS: parses recipe's list from JSONObject and returns it
    private RecipeList parseRecipeList(JSONObject jsonObject) throws NegativeAmountException {
        RecipeList rpl = new RecipeList();
        addRecipes(rpl, jsonObject);
        return rpl;
    }

    //MODIFIES: rpl
    //EFFECTS: parses recipes from JSON object and adds them to recipe's list
    private void addRecipes(RecipeList rpl, JSONObject jsonObject) throws NegativeAmountException {
        JSONArray jsonArray = jsonObject.getJSONArray("Recipes");
        for (Object json: jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(rpl, nextRecipe);
        }
    }

    // MODIFIES: rpl
    // EFFECTS: parses recipe from JSON object and adds it to recipe's list
    private void addRecipe(RecipeList rpl, JSONObject jsonObject) throws NegativeAmountException {
        String name = jsonObject.getString("Recipe's name");
        Recipe rp = new Recipe(name);
        addIngredients(rp, jsonObject);
        rpl.addRecipe(rp);
    }

    // MODIFIES: rp
    // EFFECTS: parses ingredients from JSON object and adds them to recipe
    private void addIngredients(Recipe rp, JSONObject jsonObject) throws NegativeAmountException {
        JSONArray jsonArray = jsonObject.getJSONArray("Ingredients");
        for (Object json: jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredient(rp, nextIngredient);
        }
    }

    // MODIFIES: rp
    // EFFECTS: passes ingredient to JSON Object and adds it to recipe
    private void addIngredient(Recipe rp, JSONObject jsonObject) throws NegativeAmountException {
        String name = jsonObject.getString("name");
        Double amount = Double.valueOf(jsonObject.getDouble("amount"));
        Ingredient ingredient = new Ingredient(name, amount);
        rp.addIngredient(ingredient);
    }


}

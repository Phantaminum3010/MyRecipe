package model;

import exception.NegativeAmountException;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JSonWriterTest {
    private JsonWriter jsonWriter;

    @Test
    public void testJsonWriterWritesInvalidFile() {
        RecipeList recipeList = new RecipeList();
        jsonWriter = new JsonWriter ("./data/my\0illegal:fileName.json");
        try {
            jsonWriter.open();
            fail("FileNotFoundException expected to be thrown");
        } catch (FileNotFoundException e) {
        }

    }

    @Test
    public void testJsonWriterWritesEmptyRecipeList() {
        RecipeList recipeList = new RecipeList();
        jsonWriter = new JsonWriter("./data/myEmptyRecipeList1.json");
        try {
            jsonWriter.open();
            jsonWriter.write(recipeList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            fail("Caught FileNotFoundException");
        }
        JsonReader jsonReader = new JsonReader("./data/myEmptyRecipeList1.json");
        try {
            RecipeList recipeList1 = jsonReader.read();
            assertEquals(recipeList1.getRecipeList().size(), 0);
        } catch (IOException e) {
            fail("Caught IOException");
        } catch (NegativeAmountException e) {
            fail ("NegativeAmountException caught!");
        }

    }

    @Test
    public void testJsonWriterWritesNonEmptyRecipeList() {
        jsonWriter = new JsonWriter("./data/myRecipeList.json");
        RecipeList recipeList = new RecipeList();
        Recipe recipe = new Recipe("Cheesecake");
        Ingredient firstIngredient = new Ingredient("Cheese", 50);
        Ingredient secondIngredient = new Ingredient("Butter", 30);
        Ingredient thirdIngredient = new Ingredient("Flour", 150);
        try {
            recipe.addIngredient(firstIngredient);
            recipe.addIngredient(secondIngredient);
            recipe.addIngredient(thirdIngredient);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        recipeList.addRecipe(recipe);
        try {
            jsonWriter.open();
            jsonWriter.write(recipeList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            fail("Caught FileNotFoundException");
        }
        JsonReader jsonReader = new JsonReader("./data/myRecipeList.json");
        try {
            RecipeList recipeList1 = jsonReader.read();
            Recipe recipe1 = recipeList1.getRecipeList().get(0);
            assertEquals(recipeList1.getRecipeList().size(), 1);
            assertEquals(recipe1.getIngredients().size(), 3);
            assertEquals(recipe1.getIngredients().get(0).getIngredientName(), "Cheese");
            assertEquals(recipe1.getIngredients().get(1).getIngredientName(), "Butter");
            assertEquals(recipe1.getIngredients().get(2).getIngredientName(), "Flour");
            assertEquals(recipe1.getIngredients().get(0).getAmount(), 50);
            assertEquals(recipe1.getIngredients().get(1).getAmount(), 30);
            assertEquals(recipe1.getIngredients().get(2).getAmount(), 150);
        } catch (IOException e) {
            fail("Caught IOException");
        } catch (NegativeAmountException e) {
            fail("Caught NegativeAmount exception");
        }
    }


}

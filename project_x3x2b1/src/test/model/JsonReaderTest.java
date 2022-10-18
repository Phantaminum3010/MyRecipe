package model;

import exception.NegativeAmountException;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    private JsonReader jsonReader;

    @Test
    public void testJsonReaderReadsNonExistedFile() {
        jsonReader = new JsonReader("./data/noSuchFile.json");
        try {
            jsonReader.read();
            fail("IOException expected to be thrown");
        } catch (IOException e) {
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }

    }

    @Test
    public void testJsonReaderEmptyRecipeList() {
        jsonReader = new JsonReader("./data/emptyRecipeList.json");
        try {
            RecipeList rpl = jsonReader.read();
            assertEquals(rpl.getRecipeList().size(), 0);
        } catch (IOException e) {
            fail("Caught IOException");
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
    }

    @Test
    public void testJsonReaderNonEmptyReciperList() {
        jsonReader = new JsonReader("./data/recipeList.json");
        try {
            RecipeList rpl = jsonReader.read();
            assertEquals(rpl.getRecipeList().size(), 2);
            Recipe firstRecipe = rpl.getRecipeList().get(0);
            Recipe secondRecipe = rpl.getRecipeList().get(1);
            assertEquals(firstRecipe.getRecipeName(), "Kung-pao chicken");
            assertEquals(secondRecipe.getRecipeName(), "Spaghetti Carbonara");
            assertEquals(firstRecipe.getIngredients().size(), 6);
            assertEquals(secondRecipe.getIngredients().size(), 1);
            assertEquals(firstRecipe.getIngredients().get(0).getIngredientName(), "Chicken's breast");
            assertEquals(firstRecipe.getIngredients().get(5).getIngredientName(), "Chilli paste");
            assertEquals(secondRecipe.getIngredients().get(0).getIngredientName(), "Pasta");
        } catch (IOException e) {
            fail("Caught IOException");
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
    }


}

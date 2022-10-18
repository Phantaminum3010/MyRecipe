package model;

import exception.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    private Recipe testRecipe;
    private Ingredient ingredient1;

    @BeforeEach
    public void runBefore() {
        testRecipe = new Recipe("Kung-pao chicken");
        ingredient1 = new Ingredient("Chicken", 200.0);
    }

    @Test
    public void testConstructor() {
        assertEquals(testRecipe.getRecipeName(), "Kung-pao chicken");
        assertTrue(testRecipe.getId() > 0);
        assertEquals(testRecipe.getIngredients().size(), 0);
    }

    @Test
    public void testAddIngredient() {
        try {
            testRecipe.addIngredient(ingredient1);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        assertEquals(testRecipe.getIngredients().get(0).getIngredientName(), "Chicken");
        assertEquals(testRecipe.getIngredients().get(0).getAmount(), 200.0);
    }


    @Test
    public void testAddMultipleIngredients() {
        Ingredient ingredient2 = new Ingredient("Chillies", 15.0);
        try {
            testRecipe.addIngredient(ingredient1);
            testRecipe.addIngredient(ingredient2);
        } catch (NegativeAmountException e) {
            System.out.println("NegativeAmountException caught!");
        }
        assertEquals(testRecipe.getIngredients().get(0).getIngredientName(), "Chicken");
        assertEquals(testRecipe.getIngredients().get(0).getAmount(), 200.0);
        assertEquals(testRecipe.getIngredients().get(1).getIngredientName(), "Chillies");
        assertEquals(testRecipe.getIngredients().get(1).getAmount(), 15.0);
    }

    @Test
    public void testAddIngredientAlreadyThere() {
        Ingredient ingredient2 = new Ingredient("Chicken", 30.0);
        try {
            testRecipe.addIngredient(ingredient1);
            testRecipe.addIngredient(ingredient2);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        assertEquals(testRecipe.getIngredients().get(0).getIngredientName(), "Chicken");
        assertEquals(testRecipe.getIngredients().size(), 1);
        assertEquals(testRecipe.getIngredients().get(0).getAmount(), 230.0);
    }

    @Test
    public void testAddIngredientInvalidAmount() {
        Ingredient ingredient2 = new Ingredient("Chicken", -30.0);
        try {
            testRecipe.addIngredient(ingredient2);
            fail("NegativeAmountException should be caught!");
        } catch (NegativeAmountException e) {
        }
    }

    @Test
    public void testRemoveIngredientNotThere() {
        try {
            testRecipe.addIngredient(ingredient1);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        Ingredient ingredient2 = new Ingredient("Chillies", 15.0);
        testRecipe.removeIngredient(ingredient2);
        assertEquals(testRecipe.getIngredients().get(0).getIngredientName(), "Chicken");
        assertEquals(testRecipe.getIngredients().size(), 1);
    }

    @Test
    public void testRemoveIngredientToEmptyList() {
        try {
            testRecipe.addIngredient(ingredient1);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        testRecipe.removeIngredient(ingredient1);
        assertEquals(testRecipe.getIngredients().size(), 0);
    }

    @Test
    public void testRemoveIngredientNotEmpty() {
        try {
            testRecipe.addIngredient(ingredient1);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        Ingredient ingredient2 = new Ingredient("Chillies", 15.0);
        try {
            testRecipe.addIngredient(ingredient2);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        testRecipe.removeIngredient(ingredient1);
        assertEquals(testRecipe.getIngredients().get(0).getAmount(), 15.0);
        assertEquals(testRecipe.getIngredients().get(0).getIngredientName(), "Chillies");
    }

    @Test
    public void testRemoveMultipleIngredients() {
        try {
            testRecipe.addIngredient(ingredient1);
            Ingredient ingredient2 = new Ingredient("Chillies", 15.0);
            Ingredient ingredient3 = new Ingredient("Peanuts", 113.4);
            testRecipe.addIngredient(ingredient2);
            testRecipe.addIngredient(ingredient3);
            testRecipe.removeIngredient(ingredient1);
            testRecipe.removeIngredient(ingredient3);
            assertEquals(testRecipe.getIngredients().get(0).getAmount(), 15.0);
            assertEquals(testRecipe.getIngredients().get(0).getIngredientName(), "Chillies");
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
    }
}

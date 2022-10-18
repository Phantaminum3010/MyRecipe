package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class RecipeListTest {
    private RecipeList testRecipeList;
    private Recipe recipe;

    @BeforeEach
    public void runBefore() {
        testRecipeList = new RecipeList();
        recipe = new Recipe("Kung-pao chicken");
    }

    @Test
    public void testConstructor() {
        assertEquals(testRecipeList.getRecipeList().size(), 0);
    }

    @Test
    public void testAddRecipeNotThere() {
        testRecipeList.addRecipe(recipe);
        assertEquals(testRecipeList.getRecipeList().get(0), recipe);
    }

    @Test
    public void testAddRecipeAlreadyThere() {
        Recipe recipe1 = recipe;
        testRecipeList.addRecipe(recipe);
        testRecipeList.addRecipe(recipe1);
        assertEquals(testRecipeList.getRecipeList().size(), 1);
        assertEquals(testRecipeList.getRecipeList().get(0), recipe);
    }

    @Test
    public void testAddMultipleRecipes() {
        Recipe recipe1 = new Recipe("Spaghetti Carbonara");
        Recipe recipe2 = new Recipe("Hamburger");
        Recipe recipe3 = new Recipe("Pizza Pepperoni");
        testRecipeList.addRecipe(recipe);
        testRecipeList.addRecipe(recipe1);
        testRecipeList.addRecipe(recipe2);
        testRecipeList.addRecipe(recipe3);
        assertEquals(testRecipeList.getRecipeList().size(), 4);
        assertEquals(testRecipeList.getRecipeList().get(0), recipe);
        assertEquals(testRecipeList.getRecipeList().get(1), recipe1);
        assertEquals(testRecipeList.getRecipeList().get(2), recipe2);
        assertEquals(testRecipeList.getRecipeList().get(3), recipe3);
    }

    @Test
    public void testRemoveRecipeAlreadyThere() {
        Recipe newRecipe = new Recipe("Spaghetti Carbonara");
        testRecipeList.addRecipe(recipe);
        testRecipeList.addRecipe(newRecipe);
        testRecipeList.removeRecipe(recipe);
        assertEquals(testRecipeList.getRecipeList().size(), 1);
        assertEquals(testRecipeList.getRecipeList().get(0), newRecipe);
    }

    @Test
    public void testRemoveRecipeNotThere() {
        testRecipeList.addRecipe(recipe);
        Recipe recipe1 = new Recipe("Spaghetti Carbonara");
        testRecipeList.removeRecipe(recipe1);
        assertEquals(testRecipeList.getRecipeList().size(), 1);
        assertEquals(testRecipeList.getRecipeList().get(0), recipe);
    }
}

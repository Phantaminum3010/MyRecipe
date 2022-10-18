package model;

import exception.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    // delete or rename this class!
    private Ingredient testIngredient;

    @BeforeEach
    public void runBefore() {
        testIngredient = new Ingredient("Chicken", 200.0);
    }

    @Test
    public void testConstructor() {
        assertEquals(testIngredient.getIngredientName(), "Chicken");
        assertEquals(testIngredient.getAmount(), 200.0);
    }

    @Test
    public void testAddAmountSuccessfully() {
        try {
            testIngredient.addAmount(50);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        assertEquals(testIngredient.getAmount(), 250.0);
    }

    @Test
    public void testAddAmountFail() {
        try {
            testIngredient.addAmount(-100);
            fail("NegativeAmountException not caught!");
        } catch (NegativeAmountException e) {
        }
    }

    @Test
    public void testAddMultipleAmountsFail() {
        try {
            testIngredient.addAmount(50);
            testIngredient.addAmount(-100);
            fail("NegativeAmountException not caught!");
        } catch (NegativeAmountException e) {
        }
    }

    @Test
    public void testAddMultipleAmountsSuccessfully() {
        try {
            testIngredient.addAmount(100.0);
            testIngredient.addAmount(50.0);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException caught!");
        }
        assertEquals(testIngredient.getAmount(), 350.0);
    }


}
package model;

import exception.NegativeAmountException;
import org.json.JSONObject;
import persistence.Writable;

public class Ingredient implements Writable {
    private String ingredientName;
    private double amount;

    //Construct an ingredient with its name and amount in grams
    public Ingredient(String ingredientName, double amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    //EFFECTS: return the name of the ingredient
    public String getIngredientName() {
        return ingredientName;
    }

    //EFFECTS: return the amount of the ingredient (in grams)
    public double getAmount() {
        return amount;
    }

    //MODIFIES: this
    //EFFECTS: amountAdded is added to amount, return amount
    public double addAmount(double amountAdded) throws NegativeAmountException {
        if (amountAdded >= 0) {
            amount += amountAdded;
        } else {
            throw new NegativeAmountException();
        }
        return amount;
    }



    @Override
    //NOTES: this method is adapted from the code written in Workroom class in  JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("name", ingredientName);
        return json;
    }
}

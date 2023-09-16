package com.springboot.Entities;

import java.util.Arrays;

public class Drink {
    String drinkName;
    String alcoholic;
    String glass;
    String instructions;
    String linkToDrinkIcon;
    String[] ingredients;

    //getters and setters
    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getLinkToDrinkIcon() {
        return linkToDrinkIcon;
    }

    public void setLinkToDrinkIcon(String linkToDrinkIcon) {
        this.linkToDrinkIcon = linkToDrinkIcon;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "----------------------------------" +
                "\n• Drink name: " + drinkName +
                "\n • Contains alcohol? " + alcoholic +
                "\n • Appropriate glass: " + glass +
                "\n • Instructions: " + instructions +
                "\n • Icon: " + linkToDrinkIcon +
                "\n • Ingredients: " + Arrays.toString(ingredients).replace("[", "").replace("]", "") +
                "\n----------------------------------";
    }
}

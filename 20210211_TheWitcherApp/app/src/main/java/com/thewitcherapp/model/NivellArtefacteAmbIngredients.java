package com.thewitcherapp.model;

import java.util.List;

public class NivellArtefacteAmbIngredients {

    public NivellArtefacteAmbIngredients(NivellArtefacte nivell, List<Ingredient> ingredients) {
        this.nivell = nivell;
        this.ingredients = ingredients;
    }

    NivellArtefacte nivell;
    List<Ingredient> ingredients;
}

package com.thewitcherapp.dao;

import com.thewitcherapp.model.ArtefacteAmbNivells;
import com.thewitcherapp.model.Ingredient;

import java.util.List;

import androidx.room.Query;
import androidx.room.Transaction;

public interface IngredientDAO {

    @Query("SELECT i.* FROM ingredient i INNER JOIN nivell_artefacte_ingredient nai ON i.id = nai.ingredient_id " +
            "  WHERE nai.artefacte_id = :artefacte_id AND nai.nivell = :nivell ")
    @Transaction
    List<Ingredient> getArtefactesAmbNivells(long artefacte_id, long nivell);

}

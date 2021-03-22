package com.thewitcherapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "nivell_artefacte_ingredient" ,
        primaryKeys = {"artefacte_id","nivell","ingredient_id"})
public class NivellArtefacte_Ingredient {

    @ColumnInfo(name="artefacte_id")
    public long artefacteId;
    public long nivell;
    @ColumnInfo(name="ingredient_id")
    public long ingredientId;
    public int quant;
}

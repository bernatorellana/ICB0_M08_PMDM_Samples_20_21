package com.thewitcherapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient")
public class Ingredient {
    @PrimaryKey
    public long id;
    public String nom;
    public String imageName;
}

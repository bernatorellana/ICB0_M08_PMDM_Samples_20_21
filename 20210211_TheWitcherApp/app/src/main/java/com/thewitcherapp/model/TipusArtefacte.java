package com.thewitcherapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipus_artefacte")
public class TipusArtefacte {
    @PrimaryKey
    public long id;
    public String nom;

    public TipusArtefacte(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}

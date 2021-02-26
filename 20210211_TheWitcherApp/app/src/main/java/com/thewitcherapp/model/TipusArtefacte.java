package com.thewitcherapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipus_artefacte")
public class TipusArtefacte {
    @PrimaryKey
    long id;
    String nom;
}

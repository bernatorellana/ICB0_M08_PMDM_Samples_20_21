package com.thewitcherapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artefacte")
public class Artefacte {
    @PrimaryKey
    long id;
    String nom;
    String descripcio;
    @ColumnInfo(name="tipus_id")
    long tipusId;
    String imatge;
}

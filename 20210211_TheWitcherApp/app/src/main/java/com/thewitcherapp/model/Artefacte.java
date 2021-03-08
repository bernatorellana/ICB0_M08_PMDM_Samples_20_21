package com.thewitcherapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "artefacte",indices = {@Index(value = {"key"},
        unique = true)})
public class Artefacte {
    @PrimaryKey
    public long id;
    public String key;
    public String nom;
    public String descripcio;
    @ColumnInfo(name="tipus_id")
    public long tipusId;
    public String imatge;

    public Artefacte(long id, String key, String nom, String descripcio, long tipusId, String imatge) {
        this.id = id;
        this.key= key;
        this.nom = nom;
        this.descripcio = descripcio;
        this.tipusId = tipusId;
        this.imatge = imatge;
    }
}

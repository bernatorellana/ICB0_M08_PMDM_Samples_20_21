package com.thewitcherapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nivell_artefacte",
        primaryKeys = {"artefacte_id","nivell"})
public class NivellArtefacte {
    @ColumnInfo(name="artefacte_id")
    long artefacteId;
    long nivell;
    int charges;
    int duration;
    @ColumnInfo(name="fire_dg")
    int fireDg;
    @ColumnInfo(name="silver_dg")
    int silverDg;
    @ColumnInfo(name="phys_dg")
    int physDg;
}

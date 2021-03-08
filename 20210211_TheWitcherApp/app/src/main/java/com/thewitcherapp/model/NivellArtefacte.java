package com.thewitcherapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nivell_artefacte",
        primaryKeys = {"artefacte_id","nivell"})
public class NivellArtefacte {
    @ColumnInfo(name="artefacte_id")
    public long artefacteId;
    public long nivell;
    public int charges;
    public int duration;
    @ColumnInfo(name="fire_dg")
    public int fireDg;
    @ColumnInfo(name="silver_dg")
    public int silverDg;
    @ColumnInfo(name="phys_dg")
    public int physDg;
}

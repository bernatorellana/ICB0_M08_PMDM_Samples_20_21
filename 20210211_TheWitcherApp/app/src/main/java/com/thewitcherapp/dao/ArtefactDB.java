package com.thewitcherapp.dao;

import com.thewitcherapp.model.Artefacte;
import com.thewitcherapp.model.NivellArtefacte;
import com.thewitcherapp.model.TipusArtefacte;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Artefacte.class, TipusArtefacte.class, NivellArtefacte.class}, version = 1, exportSchema = false)
public abstract class ArtefactDB extends RoomDatabase {
    public abstract ArtefacteDAO getArtefacteDao();
    public abstract TipusArtefacteDAO getTipusArtefacteDao();
}
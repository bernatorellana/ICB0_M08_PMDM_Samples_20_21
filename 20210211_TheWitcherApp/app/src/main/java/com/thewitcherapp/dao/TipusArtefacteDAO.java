package com.thewitcherapp.dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thewitcherapp.model.ArtefacteAmbNivells;
import com.thewitcherapp.model.TipusArtefacte;

import java.util.List;

public interface TipusArtefacteDAO {

    @Query("SELECT * FROM tipus_artefacte")
    List<TipusArtefacte> getAll();

    @Insert
    void insert(TipusArtefacte ta);

    @Update
    void update(TipusArtefacte ta);
}

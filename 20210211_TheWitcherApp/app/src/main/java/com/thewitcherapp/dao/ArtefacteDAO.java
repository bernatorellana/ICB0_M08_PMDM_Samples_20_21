package com.thewitcherapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thewitcherapp.model.Artefacte;
import com.thewitcherapp.model.ArtefacteAmbNivells;

import java.util.List;

@Dao
public interface ArtefacteDAO {

    @Query("SELECT * FROM artefacte")
    @Transaction
    List<ArtefacteAmbNivells> getArtefactesAmbNivells();


    @Query("SELECT * FROM artefacte WHERE tipus_id = :tipus_id")
    @Transaction
    List<ArtefacteAmbNivells> getArtefactesAmbNivells(long tipus_id);

    @Insert
    void insert(Artefacte a);

    @Update
    void update(Artefacte a);
}

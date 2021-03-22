package com.thewitcherapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thewitcherapp.model.Artefacte;
import com.thewitcherapp.model.ArtefacteAmbNivells;
import com.thewitcherapp.model.Ingredient;
import com.thewitcherapp.model.NivellArtefacte;
import com.thewitcherapp.model.NivellArtefacteAmbIngredients;

import java.util.List;

@Dao
public interface ArtefacteDAO {

    @Query("SELECT * FROM artefacte")
    @Transaction
    List<ArtefacteAmbNivells> getArtefactesAmbNivells();


    @Query("SELECT * FROM artefacte WHERE tipus_id = :tipus_id")
    @Transaction
    List<ArtefacteAmbNivells> getArtefactesAmbNivells(long tipus_id);


    @Query("SELECT * FROM artefacte WHERE id = :artefacte_id")
    @Transaction
    ArtefacteAmbNivells getArtefacteAmbNivells(long artefacte_id);

    @Query("SELECT * FROM nivell_artefacte WHERE artefacte_id = :artefacte_id and nivell = :nivell")
    @Transaction
    NivellArtefacte getNivell(long artefacte_id, long nivell);


    @Insert
    void insert(Artefacte a);

    @Update
    void update(Artefacte a);

    @Delete
    void delete(Artefacte a);
}

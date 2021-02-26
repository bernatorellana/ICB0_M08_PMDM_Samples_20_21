package com.thewitcherapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ArtefacteAmbNivells {
    @Embedded Artefacte artefacte;
    @Relation(parentColumn = "id",
    entityColumn = "artefacte_id")
    List<NivellArtefacte> nivells;
}

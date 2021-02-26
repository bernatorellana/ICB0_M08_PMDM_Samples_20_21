package com.thewitcherapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ArtefacteMesTipus {
    @Embedded Artefacte artefacte;
    @Relation(
            parentColumn = "tipus_id",
            entityColumn = "id")
    TipusArtefacte tipus;
}

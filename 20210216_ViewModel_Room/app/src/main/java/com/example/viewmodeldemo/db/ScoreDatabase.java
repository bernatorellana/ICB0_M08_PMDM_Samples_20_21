package com.example.viewmodeldemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.viewmodeldemo.dao.GameScoreDao;
import com.example.viewmodeldemo.model.GameScore;

@Database(entities = {GameScore.class}, version = 1)
public abstract class ScoreDatabase extends RoomDatabase {
    public abstract GameScoreDao gameScoreDao();
}

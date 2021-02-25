package com.example.viewmodeldemo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.viewmodeldemo.dao.GameScoreDao;
import com.example.viewmodeldemo.model.GameScore;

@Database(entities = {GameScore.class}, version = 1, exportSchema = false)
public abstract class ScoreDatabase extends RoomDatabase {
    public abstract GameScoreDao gameScoreDao();

    public static ScoreDatabase getDB(Context c){
        ScoreDatabase db = Room.databaseBuilder(
                c,ScoreDatabase.class,"database_scores").build();
        return db;
    }
}

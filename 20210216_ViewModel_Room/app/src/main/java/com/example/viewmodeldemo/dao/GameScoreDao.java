package com.example.viewmodeldemo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.viewmodeldemo.model.GameScore;

@Dao
public interface GameScoreDao {

    @Query("SELECT * FROM game_score LIMIT 1")
    GameScore getTheGame();

    @Insert
    void insert(GameScore score);

    @Update
    void update(GameScore score);

}

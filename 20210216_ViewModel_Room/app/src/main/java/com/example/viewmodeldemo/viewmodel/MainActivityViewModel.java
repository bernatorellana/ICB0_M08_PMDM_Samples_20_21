package com.example.viewmodeldemo.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.viewmodeldemo.MainActivity;
import com.example.viewmodeldemo.dao.GameScoreDao;
import com.example.viewmodeldemo.db.ScoreDatabase;
import com.example.viewmodeldemo.model.GameScore;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivityViewModel extends AndroidViewModel {



    public MainActivityViewModel(Application a){
        super(a);
        //carregaDeDisc(a);
        //carregaDeSharedPreferences(a);
        carregaDeRoom(a);
    }

    private void carregaDeRoom(Application a) {
        ScoreDatabase db = Room.databaseBuilder(
                a.getApplicationContext(),ScoreDatabase.class,"database_scores").build();
        GameScoreDao gsdao = db.gameScoreDao();

        GameScore gsc = gsdao.getTheGame();
        if(gsc!=null) {
            this.scoreA = gsc.getScoreA();
            this.scoreB = gsc.getScoreB();
        } else {
            gsc = new GameScore();
            gsc.setId(1);
            gsc.setScoreA(0);
            gsc.setScoreB(0);
            gsdao.insert(gsc);
        }
    }

    private void carregaDeSharedPreferences(Application a) {
        Context c = a.getApplicationContext();
        SharedPreferences sp =  c.getSharedPreferences(c.getPackageName(),Context.MODE_PRIVATE);
        this.scoreA = sp.getInt(MainActivity.SCORE_A, 0);
        this.scoreB = sp.getInt(MainActivity.SCORE_B, 0);
    }

    private void carregaDeDisc(Application a) {
        Context c = a.getApplicationContext();
        FileInputStream fis=null;
        try {
            fis =  c.openFileInput(MainActivity.getFileName());
            ObjectInputStream das = new ObjectInputStream(fis);
            this.scoreA = das.readInt();
            this.scoreB = das.readInt();

        } catch (FileNotFoundException e) {
            Log.d("APP", "Puntuacions anteriors no disponibles.");

        } catch (IOException e) {
            Log.e("APP", "Error obrint arxiu per escriure.",e);

        }
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    private int scoreA;
    private int scoreB;

}

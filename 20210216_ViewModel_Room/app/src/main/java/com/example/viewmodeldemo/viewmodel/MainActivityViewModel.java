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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivityViewModel extends AndroidViewModel {

    private GameScore mGsc;
    private int scoreA;
    private int scoreB;


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


    public MainActivityViewModel(Application a){
        super(a);
        //carregaDeDisc(a);
        //carregaDeSharedPreferences(a);
        carregaDeRoom(a);
    }

    private void carregaDeRoom(Application a) {

        ScoreDatabase db = ScoreDatabase.getDB(a.getApplicationContext());
        GameScoreDao gsdao = db.gameScoreDao();

        mGsc = gsdao.getTheGame();
        if(mGsc!=null) {
            this.scoreA = mGsc.getScoreA();
            this.scoreB = mGsc.getScoreB();
        } else {
            mGsc = new GameScore();
            mGsc.setId(1);
            mGsc.setScoreA(0);
            mGsc.setScoreB(0);
            gsdao.insert(mGsc);
        }
    }

    private void carregaDeSharedPreferences(Application a) {
        Context c = a.getApplicationContext();
        SharedPreferences sp =  c.getSharedPreferences(c.getPackageName(),Context.MODE_PRIVATE);
        this.scoreA = sp.getInt(SCORE_A, 0);
        this.scoreB = sp.getInt(SCORE_B, 0);
    }

    private void carregaDeDisc(Application a) {
        Context c = a.getApplicationContext();
        FileInputStream fis=null;
        try {
            fis =  c.openFileInput(getFileName());
            ObjectInputStream das = new ObjectInputStream(fis);
            this.scoreA = das.readInt();
            this.scoreB = das.readInt();

        } catch (FileNotFoundException e) {
            Log.d("APP", "Puntuacions anteriors no disponibles.");

        } catch (IOException e) {
            Log.e("APP", "Error obrint arxiu per escriure.",e);

        }
    }




    public static final String SCORE_A = "SCORE_A";
    public static final String SCORE_B = "SCORE_B";

    private void updateScoresVersioSharedPreferences(int scoreA, int scoreB) {
        Context c = getApplication().getApplicationContext();
        SharedPreferences sp = c.getSharedPreferences(
                c.getPackageName(), c.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sp.edit();
        editor.putInt(SCORE_A, scoreA);
        editor.putInt(SCORE_B, scoreB);
        editor.commit();
    }


    private void updateScoresVersioFile(int scoreA, int scoreB) {
        FileOutputStream fos=null;
        try {
            fos =  getApplication().getApplicationContext().openFileOutput(getFileName(), Context.MODE_PRIVATE );
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(scoreA);
            oos.writeInt(scoreB);
            oos.flush();
            oos.close();

        } catch (FileNotFoundException e) {
            Log.e("APP", "Error obrint arxiu per escriure",e);
        } catch (IOException e) {
            Log.e("APP", "Error obrint arxiu per escriure",e);
        }
        finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e("APP", "Error obrint arxiu per escriure",e);
                }
            }
        }
    }

    public static String getFileName() {
        return "scores.bin";
    }


    public void update() {

        updateVersioRoom();
                /*updateScoresVersioFile(
                viewmodel.getScoreA(),
                viewmodel.getScoreB());*/

       /* updateScoresVersioSharedPreferences(
                getScoreA(),
                getScoreB());*/
    }

    private void updateVersioRoom() {

        ScoreDatabase db = ScoreDatabase.getDB(getApplication().getApplicationContext());
        GameScoreDao gsdao = db.gameScoreDao();
        mGsc.setScoreA(this.getScoreA());
        mGsc.setScoreB(this.getScoreB());
        gsdao.update(mGsc);

    }
}

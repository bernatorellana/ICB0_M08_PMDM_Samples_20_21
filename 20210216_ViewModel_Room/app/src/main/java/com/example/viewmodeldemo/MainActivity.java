package com.example.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewmodeldemo.databinding.ActivityMainBinding;
import com.example.viewmodeldemo.viewmodel.MainActivityViewModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SCORE_A = "SCORE_A";
    public static final String SCORE_B = "SCORE_B";
    ActivityMainBinding binding;
    MainActivityViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("APP", "onCreate");

        //--------------------------------------------------------------
        // Recuperem el Binding que ha fet automàticament
        // NOTA: hem hagut de modificar el Build.gradle i posar:
        //viewBinding {
        //    enabled = true
        //}

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);

        //------------------------------------------------------------
        // Instanciar el ViewModel
        ViewModelProvider vmp=new ViewModelProvider(this);
        // cal afegir al Gradle:
        //     implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
        viewmodel = vmp.get(MainActivityViewModel.class);

        binding.btnPlayerA.setOnClickListener(this);
        binding.btnPlayerB.setOnClickListener(this);

        mostraPuntuacions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("APP", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("APP", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("APP", "onStop");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnPlayerA){
            viewmodel.setScoreA(viewmodel.getScoreA()+1);
        } else {
            viewmodel.setScoreB(viewmodel.getScoreB()+1);
        }
        /*updateScoresVersioFile(
                viewmodel.getScoreA(),
                viewmodel.getScoreB());*/

        updateScoresVersioSharedPreferences(
                viewmodel.getScoreA(),
                viewmodel.getScoreB());
        mostraPuntuacions();
    }


    private void updateScoresVersioSharedPreferences(int scoreA, int scoreB) {
        SharedPreferences sp = getSharedPreferences(this.getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor =  sp.edit();
        editor.putInt(SCORE_A, scoreA);
        editor.putInt(SCORE_B, scoreB);
        editor.commit();
    }


    private void updateScoresVersioFile(int scoreA, int scoreB) {
        FileOutputStream fos=null;
        try {
            fos =  openFileOutput(getFileName(), Context.MODE_PRIVATE );
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


    private void mostraPuntuacions() {
        binding.edtPlayerA.setText(""+viewmodel.getScoreA());
        binding.edtPlayerB.setText(""+viewmodel.getScoreB());
    }
}
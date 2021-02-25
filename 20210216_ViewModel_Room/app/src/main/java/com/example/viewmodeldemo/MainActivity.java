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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


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
        //------------------------------------------------------------
        // Aquest bloc de codi s'encarrega de llançar l'operació
        // de recuperar el ViewModel. Donat que implica
        // un accés a base de dades, cal dur a terme aquesta operació
        // en un fil. Utilitzarem l'API de Reactive per engegar
        // el fil de background i gestionar la resposta al fil
        // d'interfície gràfica.

        setIsLoading(true);
        Observable.fromCallable(() -> {
            //---------------- START OF THREAD ------------------------------------
            // Això és el codi que s'executarà en un fil
            viewmodel = vmp.get(MainActivityViewModel.class);
            //Thread.sleep(3000);
            return false;
            //--------------- END OF THREAD-------------------------------------
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe((result) -> {
                //-------------  UI THREAD ---------------------------------------
                // El codi que tenim aquí s'executa només quan el fil
                // ha acabat !! A més, aquest codi s'executa en el fil ç
                // d'interfície gràfica.
                setIsLoading(false);
                mostraPuntuacions();
                //-------------  END OF UI THREAD ---------------------------------------
            });

        //---------------------------------------------------
        binding.btnPlayerA.setOnClickListener(this);
        binding.btnPlayerB.setOnClickListener(this);
    }


    private void setIsLoading(boolean isLoading){
        binding.btnPlayerA.setEnabled(!isLoading);
        binding.btnPlayerB.setEnabled(!isLoading);
        binding.progressBar.setVisibility(isLoading?View.VISIBLE:View.INVISIBLE);
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

        setIsLoading(true);
        Observable.fromCallable(() -> {
            //---------------- START OF THREAD ------------------------------------
            // Això és el codi que s'executarà en un fil
            viewmodel.update();
            return false;
            //--------------- END OF THREAD-------------------------------------
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //-------------  UI THREAD ---------------------------------------
                    // El codi que tenim aquí s'executa només quan el fil
                    // ha acabat !! A més, aquest codi s'executa en el fil ç
                    // d'interfície gràfica.
                    setIsLoading(false);
                    mostraPuntuacions();
                    //-------------  END OF UI THREAD ---------------------------------------
                });




    }

    private void mostraPuntuacions() {
        binding.edtPlayerA.setText(""+viewmodel.getScoreA());
        binding.edtPlayerB.setText(""+viewmodel.getScoreB());
    }

}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.pokeapi.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /// descarreguem JSON
        NetworkUtils.getJSon("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=200");
    }
}
package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.Bestiary;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txvSortida = findViewById(R.id.txvSortida);

        Bestiary b = loadBestiary();
        txvSortida.setText(b.toString());

    }


    private Bestiary loadBestiary() {
        String json = loadJSON();
        Gson gson = new Gson();
        return gson.fromJson(json, Bestiary.class);
    }

    private String loadJSON(){
        String json = "";
        InputStream is = null;
        try {
            is = getResources().openRawResource(R.raw.bestiary);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader reader2 = new BufferedReader(reader);

            String line = "";
            try {
                while ((line = reader2.readLine()) != null) {
                    json += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }
}
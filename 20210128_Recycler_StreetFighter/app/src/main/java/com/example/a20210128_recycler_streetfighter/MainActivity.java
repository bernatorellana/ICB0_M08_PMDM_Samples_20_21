package com.example.a20210128_recycler_streetfighter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a20210128_recycler_streetfighter.adapter.CharacterAdapter;

public class MainActivity extends AppCompatActivity {


    RecyclerView rcyPersonatges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcyPersonatges = findViewById(R.id.rcyPersonatges);

        rcyPersonatges.setLayoutManager(new LinearLayoutManager(this));
        CharacterAdapter adapter = new CharacterAdapter();
        rcyPersonatges.setAdapter(adapter);
    }
}
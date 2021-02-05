package com.example.a20210128_recycler_streetfighter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.a20210128_recycler_streetfighter.adapter.CharacterAdapter;

public class MainActivity extends AppCompatActivity {

    private CharacterAdapter mAdapter;
    private RecyclerView mRcyPersonatges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcyPersonatges = findViewById(R.id.rcyPersonatges);



        mRcyPersonatges.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CharacterAdapter();
        mRcyPersonatges.setAdapter(mAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itmDelete:    itemDelete();break;
                    case R.id.itmUp:        itemUp(); break;
                    case R.id.itmDown:      itemDown(); break;
                }
                return false;
            }
        });


    }

    private void itemDown() {
        Log.d("STREETFIGHTER","DOWN");
        mAdapter.moveDownSelected();
    }

    private void itemUp() {
        Log.d("STREETFIGHTER","UP");
        mAdapter.moveUpSelected();
    }

    private void itemDelete() {
        Log.d("STREETFIGHTER","DELETE");
        mAdapter.deleteSelected();
    }
}
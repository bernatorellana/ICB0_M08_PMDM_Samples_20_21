package com.example.a20210128_recycler_streetfighter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.a20210128_recycler_streetfighter.adapter.CharacterAdapter;
import com.example.a20210128_recycler_streetfighter.model.Personatge;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null) {
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.llistaContainer,
                    LlistaFragment.class,
                    null, LlistaFragment.TAG).
                    commit();
        }

    }

}
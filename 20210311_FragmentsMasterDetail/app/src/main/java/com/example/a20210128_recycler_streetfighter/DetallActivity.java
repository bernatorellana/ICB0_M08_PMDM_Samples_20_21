package com.example.a20210128_recycler_streetfighter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class DetallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall);

        int idPersonatge = getIntent().getIntExtra("ID_PERSONATGE", -1);
        DetallFragment df = DetallFragment.newInstance(idPersonatge);

        // estic en horitzontal
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detallContainer, df, DetallFragment.TAG)
                .commit();
    }
}
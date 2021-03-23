package com.example.a20210322_paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Pissarra mPissarra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPissarra = findViewById(R.id.pissarra);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.tools_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.mniBrush: mPissarra.setBrushMode();break;
            case R.id.mniLine: mPissarra.setLineMode();break;
            case R.id.mniMove: mPissarra.setMoveMode();break;
            case R.id.mniColor: mPissarra.setColorMode();break;

        }

        return true;
    }
}
package com.example.a20210308_fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import java.io.InterruptedIOException;

public class MainActivity extends AppCompatActivity implements
        EdicioFragment.NavigationListener,
        FragmentB.NavigationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Posar el fragment al contenidor
        getSupportFragmentManager()
                .beginTransaction()
                .add(
                R.id.frgEdicio,
                EdicioFragment.class,
                null)
                .commit();
    }

    @Override
    public void MarxarDeA() {
        getSupportFragmentManager().beginTransaction().replace(
                R.id.frgEdicio,FragmentB.class,null).addToBackStack("A").commit();
    }

    @Override
    public void MarxarDeB() {
        getSupportFragmentManager().beginTransaction().replace(
                R.id.frgEdicio,EdicioFragment.class,null).addToBackStack("B").commit();

    }
}
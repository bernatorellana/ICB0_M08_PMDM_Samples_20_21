package com.example.a20210308_fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.InterruptedIOException;

public class MainActivity extends AppCompatActivity implements
        EdicioFragment.NavigationListener,
        FragmentB.NavigationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // impedim que si es recrea l'Activty per culpa d'una rotació
        // es tornin a crear els fragments...
         // NOTA: quan girem savedInstanceState conté dades.
        if(savedInstanceState==null) {
            // Posar el fragment al contenidor
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(
                            R.id.frgEdicio,
                            EdicioFragment.class,
                            null, "A") //*
                    .commit();
        }
    }
/*
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
*/
    @Override
    public void MarxarDeA() {
        switchDeFragment("A", "B");
    }

    @Override
    public void MarxarDeB() {
        switchDeFragment("B", "A");
    }


    public void switchDeFragment( String tagFrom , String tagTo){

        Fragment fragFrom = getSupportFragmentManager().findFragmentByTag(tagFrom);
        Fragment fragTo = getSupportFragmentManager().findFragmentByTag(tagTo);
        if(fragTo==null){
            // el fragment destí mai s'ha creat, i cal afegir-lo per primera vegada.
            getSupportFragmentManager().
                    beginTransaction().
                    detach(fragFrom).
                    add(R.id.frgEdicio, // contenidor on posem el fragment
                            tagTo.equals(FragmentB.TAG) ? FragmentB.class: EdicioFragment.class,
                            null, //arguments....
                            tagTo //etiqueta a la quedarà associada el fragment
                            ).
                    commit();
        } else {
            //el fragment de destí s'ha trobat
            getSupportFragmentManager().
                    beginTransaction().
                    detach(fragFrom).
                    attach(fragTo).
                    commit();
        }

    }

}
package com.example.appcreaciodinamica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appcreaciodinamica.model.Persona;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llyMain = findViewById(R.id.llyMain);
        //-----------------------------------------------
        List<Persona> persones = Persona.getPersones();
        for(Persona p:persones) {
            /*
            // Creació manual d'un TextView per a cadascuna de les persones.
            TextView txv = new TextView(this);
            txv.setText(p.getNom());
            llyMain.addView(txv);
            */
            // v2.- Ara farem servir un inflador de layout.
            LayoutInflater li = getLayoutInflater();
            View fila = li.inflate(R.layout.fila, null);
            // faig el findViewId DINS DE LA FILA !!!!! <ATENTTION!>
            TextView txvNom = fila.findViewById(R.id.txvNom);
            txvNom.setText(p.getNom());
            ImageView imvFoto = fila.findViewById(R.id.imvFoto);
            imvFoto.setImageResource(p.getImatgeResourceId());
            llyMain.addView(fila);
        }
    }
}
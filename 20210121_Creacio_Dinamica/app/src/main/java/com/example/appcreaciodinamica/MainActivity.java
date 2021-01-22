package com.example.appcreaciodinamica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appcreaciodinamica.model.Persona;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private LinearLayout llyMain;
    private Spinner spnFiltreSexe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------------------------
        // Configuració de l'spinner
        spnFiltreSexe =  findViewById(R.id.spnFiltreSexe);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item, new String[]{"Home" , "Dona"});
        spnFiltreSexe.setAdapter(adapter);
        // Programar l'esdeveniment de canvi
        spnFiltreSexe.setOnItemSelectedListener(this);

        llyMain = findViewById(R.id.llyMain);
        //-----------------------------------------------
        mostraPersones();
    }

    private void mostraPersones() {
        List<Persona> persones = Persona.getPersones();

        llyMain.removeAllViews();
        for(Persona p:persones) {
            if(spnFiltreSexe.getSelectedItem()==null ||
                    (spnFiltreSexe.getSelectedItem().equals("Home") &&  p.isEsHome()) ||
                    (spnFiltreSexe.getSelectedItem().equals("Dona") &&  !p.isEsHome())
            ) {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mostraPersones();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mostraPersones();
    }
}
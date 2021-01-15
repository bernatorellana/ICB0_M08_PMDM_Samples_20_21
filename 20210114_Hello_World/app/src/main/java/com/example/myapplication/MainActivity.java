package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.myapplication.model.Persona;

public class MainActivity extends AppCompatActivity {

    /**
     * Índex de la persona actualment visualitzada
     */
    private int indexPersona;
    private EditText edtNom, edtCognom, edtTelefon;
    private Spinner spnProvincia;
    private RadioGroup rdgSexe;
    private Button btnLeft, btnRight, btnOk, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // recuperem les dades de la persona actual
        Persona p = Persona.getPersones().get(indexPersona);
        String nom = p.getNom();

        // Cerquem tots els elements de la interfície gràfica i els desem en una cache.
        edtNom = findViewById(R.id.edtNom);
        edtCognom = findViewById(R.id.edtCognom);
        edtTelefon = findViewById(R.id.edtTelefon);
        spnProvincia = findViewById(R.id.spnProvincia);
        rdgSexe = findViewById(R.id.rdgSexe);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);

        edtNom.setText(nom);

    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.myapplication.model.Persona;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Índex de la persona actualment visualitzada
     */
    private int indexPersona;
    private EditText edtNom, edtCognom, edtTelefon;
    private Spinner spnProvincia;
    private RadioGroup rdgSexe;
    private Button btnLeft, btnRight, btnOk, btnCancel;
    private ImageView imvFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        imvFoto = findViewById(R.id.imvFoto);

        //----------------------------------------
        // Programar esdeveniments
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HelloWorld", "Han clicat el botó right");
                // aquest és el codi que s'executa quan faig click
                indexPersona = (indexPersona+1)%Persona.getPersones().size();
                mostrarPersonaActual();
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexPersona--;
                if(indexPersona<0) indexPersona = Persona.getPersones().size()-1;
                mostrarPersonaActual();
            }
        });
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
/*
        edtNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                btnOk.setEnabled(s.toString().trim().length()>1);
            }
        });
*/

        mostrarPersonaActual();
     }

    private void mostrarPersonaActual() {
        // recuperem les dades de la persona actual
        if(indexPersona>=0 && indexPersona<Persona.getPersones().size())
        {
            Persona p = Persona.getPersones().get(indexPersona);
            String nom = p.getNom();
            edtNom.setText(nom);
            edtCognom.setText(p.getCognom());
            edtTelefon.setText(p.getTelefon());
            rdgSexe.check(p.isEsHome()?R.id.rdbSexeH:R.id.rdbSexeD);
            imvFoto.setImageResource(p.getImatgeResourceId());
        }
    }

    @Override
    public void onClick(View sender) {
        switch (sender.getId()){
            case  R.id.btnOk:  {
                saveData();
                break;
            }
            case R.id.btnCancel: {
                cancelEdit();
                break;
            }
        }
    }

    private void cancelEdit() {
        mostrarPersonaActual();
    }

    private void saveData() {
        Persona p = getPersonaActual();
        p.setNom(edtNom.getText().toString());
        p.setCognom(edtCognom.getText().toString());
        p.setTelefon(edtTelefon.getText().toString());
        p.setEsHome( rdgSexe.getCheckedRadioButtonId()==R.id.rdbSexeH );
    }

    private Persona getPersonaActual() {
        return Persona.getPersones().get(this.indexPersona);
    }



    //--------------------------------------------------
    class TextWatcherPersonalitzat implements TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            btnOk.setEnabled(s.toString().trim().length()>1);
        }
    }




}
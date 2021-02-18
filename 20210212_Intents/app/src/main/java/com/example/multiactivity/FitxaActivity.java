package com.example.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FitxaActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PARAM1_CODI = "Codi";
    public static final String PARAM_OUT_NOM_PERSONATGE = "Nom_Personatge";

    Button btnBack;
    EditText edt1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa);
        //---------------------------------


        btnBack = findViewById(R.id.btnBack);
        edt1 = findViewById(R.id.edt1);
        btnBack.setOnClickListener(this);
        //---------------------------------
        // recuperar el paràmetre:
        Intent i = getIntent();
        String codiS = i.getStringExtra(PARAM1_CODI);
        Log.d("APP",">"+codiS);
        int codi = Integer.parseInt(codiS);
        Personatge p = Personatge.getPersonatges().get(codi);

        edt1.setText(p.getNom());

    }

    @Override
    public void onClick(View v) {

        String nom = edt1.getText().toString();
        // Hem de crear un nou intent per retornar les dades a l'activity anterior.
        Intent i = new Intent();
        i.putExtra(PARAM_OUT_NOM_PERSONATGE, nom);
        setResult(Activity.RESULT_OK,i);
        finish();
    }

}
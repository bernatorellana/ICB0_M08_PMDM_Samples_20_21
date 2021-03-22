package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Transaction;

import android.os.Bundle;
import android.widget.TextView;

import com.example.Bestiary;
import com.example.MonsterType;
import com.google.gson.Gson;
import com.thewitcherapp.adapters.MonsterTypeAdapter;
import com.thewitcherapp.dao.ArtefactDB;
import com.thewitcherapp.dao.ArtefacteDAO;
import com.thewitcherapp.dao.DbUtils;
import com.thewitcherapp.dao.TipusArtefacteDAO;
import com.thewitcherapp.model.Artefacte;
import com.thewitcherapp.model.ArtefacteAmbNivells;
import com.thewitcherapp.model.Ingredient;
import com.thewitcherapp.model.NivellArtefacte;
import com.thewitcherapp.model.NivellArtefacteAmbIngredients;
import com.thewitcherapp.model.TipusArtefacte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements MonsterTypeAdapter.SelectedMonsterTypeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txvSortida = findViewById(R.id.txvSortida);

        Bestiary b = loadBestiary();
        txvSortida.setText(b.toString());

        inicialitzacioBD();

    }

    private void inicialitzacioBD() {
        ArtefactDB db = DbUtils.getDb(this);

        TipusArtefacteDAO tartDAO = db.getTipusArtefacteDao();
        tartDAO.insert(new TipusArtefacte(1, "oil"));

        ArtefacteDAO artDAO = db.getArtefacteDao();
        Artefacte a = new Artefacte(1,"oil_bomb","oil bomb","xaxi guai",1,"imatge");
        artDAO.insert(a);

    }

    @Transaction
    public  NivellArtefacteAmbIngredients getNivellArtefacteAmbIngredients(long artefacte_id, long nivell, long  ingredientId) {
        ArtefactDB db = DbUtils.getDb(this);

        ArtefacteDAO artDAO = db.getArtefacteDao();
        ArtefacteAmbNivells an = artDAO.getArtefacteAmbNivells(artefacte_id);
        an.nivells
        NivellArtefacte n = getNivell(artefacte_id,nivell);
        List<Ingredient> ingredients = getIngredients(artefacte_id,nivell);

        return  new NivellArtefacteAmbIngredients(n,ingredients);
    }



    private Bestiary loadBestiary() {
        String json = loadJSON();
        Gson gson = new Gson();
        return gson.fromJson(json, Bestiary.class);
    }

    private String loadJSON(){
        String json = "";
        InputStream is = null;
        try {
            is = getResources().openRawResource(R.raw.bestiary);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader reader2 = new BufferedReader(reader);

            String line = "";
            try {
                while ((line = reader2.readLine()) != null) {
                    json += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }



    @Override
    public void onSelectedMonsterType(MonsterType mt) {

    }
}
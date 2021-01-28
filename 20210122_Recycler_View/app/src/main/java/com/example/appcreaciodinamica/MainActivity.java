package com.example.appcreaciodinamica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appcreaciodinamica.adapters.PersonesAdapter;
import com.example.appcreaciodinamica.model.Persona;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectedItemListener {

    RecyclerView rcyLlistaPersones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        rcyLlistaPersones = findViewById(R.id.rcyLlistaPersones);
        rcyLlistaPersones.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,
                        false));
        /*rcyLlistaPersones.setLayoutManager(
                new GridLayoutManager(this, 2)
        );*/

        rcyLlistaPersones.setHasFixedSize(true);
        // ara li posem l'adapter
        PersonesAdapter adapter = new PersonesAdapter(Persona.getPersones(),this);
        rcyLlistaPersones.setAdapter(adapter);



    }


    @Override
    public void onSelectedItem(int filaSeleccionada) {
    }
}
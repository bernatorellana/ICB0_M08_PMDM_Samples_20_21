package com.example.a20210128_recycler_streetfighter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.a20210128_recycler_streetfighter.adapter.CharacterAdapter;
import com.example.a20210128_recycler_streetfighter.model.Personatge;

public class MainActivity extends AppCompatActivity implements LlistaFragment.SelectedItemListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null) {
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.llistaContainer,
                    LlistaFragment.class,
                    null, LlistaFragment.TAG).
                    commit();
        }

    }

    @Override
    public void onSelectedItem(Personatge seleccionat) {
        if(seleccionat==null) {
            Fragment detallFrag = getSupportFragmentManager().findFragmentById(R.id.detallContainer);

            // volem treure el personatge
            getSupportFragmentManager().beginTransaction().remove(detallFrag).commit();
        }
        else {
            Log.d("XXX", "Onselected item a la MainACtivity" + seleccionat);
            if (findViewById(R.id.detallContainer) != null) {

                DetallFragment df = DetallFragment.newInstance(seleccionat.getId());

                // estic en horitzontal
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detallContainer, df, DetallFragment.TAG)
                        .commit();
            } else {
                // estic en vertical
                // fer un intent
                Intent i = new Intent(this, DetallActivity.class);
                i.putExtra("ID_PERSONATGE", seleccionat.getId());
                startActivity(i);
            }
        }
    }
}
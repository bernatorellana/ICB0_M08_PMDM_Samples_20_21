package com.example.a20210128_recycler_streetfighter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.a20210128_recycler_streetfighter.adapter.CharacterAdapter;
import com.example.a20210128_recycler_streetfighter.model.Personatge;

public class MainActivity extends AppCompatActivity implements CharacterAdapter.OnSelectedItemListener, ActionMode.Callback {

    private CharacterAdapter mAdapter;
    private RecyclerView mRcyPersonatges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcyPersonatges = findViewById(R.id.rcyPersonatges);



        mRcyPersonatges.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CharacterAdapter(this);
        mRcyPersonatges.setAdapter(mAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        /*
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itmDelete:    itemDelete();break;
                    case R.id.itmUp:        itemUp(); break;
                    case R.id.itmDown:      itemDown(); break;
                }
                return false;
            }
        });*/


    }

    private void itemDown() {
        Log.d("STREETFIGHTER","DOWN");
        mAdapter.moveDownSelected();
    }

    private void itemUp() {
        Log.d("STREETFIGHTER","UP");
        mAdapter.moveUpSelected();
    }

    private void itemDelete() {
        Log.d("STREETFIGHTER","DELETE");
        mAdapter.deleteSelected();
    }

    @Override
    public void onSelectedItem(Personatge seleccionat) {
        Log.d("STREETFIGHTER","Personatge seleccionat:"+seleccionat);

        // activarem el Contextual Action Mode
        startActionMode(this);
    }


    //---------------------------------------------------------------------
    // conjunt de mètodes relacions amb el Contextual Action Mode
    //---------------------------------------------------------------------

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        // Aquí hem de crear el menú contextual
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.menu_main_contextual, menu);
        updateMenu(menu);
        return true;
    }

    private void updateMenu(Menu menu) {
        menu.findItem(R.id.itmUp).setVisible(mAdapter.getSelectedIndex()>0);
        menu.findItem(R.id.itmDown).setVisible(mAdapter.getSelectedIndex()<mAdapter.getNumeroPersonatges()-1);
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.itmDelete:    itemDelete(); actionMode.finish();break;
            case R.id.itmUp:        itemUp(); updateMenu(actionMode.getMenu());break;
            case R.id.itmDown:      itemDown(); updateMenu(actionMode.getMenu());break;
        }

        return false;
    }
    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {  return false; }
    @Override
    public void onDestroyActionMode(ActionMode actionMode) {    }
    //---------------------------------------------------------------------
    // FI del conjunt de mètodes relacions amb el Contextual Action Mode
    //---------------------------------------------------------------------

}
package com.example.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.pokeapi.NetworkUtils;
import com.example.myapplication.pokeapi.Pokemon;
import com.example.myapplication.pokeapi.PokemonApiParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    public List<Pokemon> mPokemons;

    public MainActivityViewModel() {
        String json = NetworkUtils.getJSon("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=200");
        try {
            mPokemons = PokemonApiParser.parsePokemons(json);
        } catch (JSONException e) {
            Log.e("XXX", "error carregant json");
            mPokemons = new ArrayList<>();
        }
    }



}

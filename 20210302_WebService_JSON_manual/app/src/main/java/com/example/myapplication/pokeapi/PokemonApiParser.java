package com.example.myapplication.pokeapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonApiParser {


    public static List<Pokemon> parsePokemons(String json) throws JSONException {
        JSONObject arrel = new JSONObject(json);
        JSONArray results = arrel.getJSONArray("results");
        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0;i< results.length();i++) {
            JSONObject pokemon = results.getJSONObject(i);
            Pokemon p = new Pokemon();
            p.name =  pokemon.getString("name");
            p.url =  pokemon.getString("url");
            loadImatgePokemon(p);
            pokemons.add(p);
        }
        return pokemons;
    }

    private static void loadImatgePokemon(Pokemon p) throws JSONException {
        String json = NetworkUtils.getJSon(p.url);
        JSONObject arrel = new JSONObject(json);
        JSONObject sprites =  arrel.getJSONObject("sprites");
        p.urlImatge =  sprites.getString("front_default");
    }

}

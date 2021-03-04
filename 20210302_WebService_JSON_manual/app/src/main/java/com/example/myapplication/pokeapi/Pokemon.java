package com.example.myapplication.pokeapi;

public class Pokemon {
    public String name;
    public String url;
    public String urlImatge;

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", urlImatge='" + urlImatge + '\'' +
                "}\n";
    }
}

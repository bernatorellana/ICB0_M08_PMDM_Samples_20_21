package com.example.myapplication.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.pokeapi.Pokemon;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    OnPokemonSelectedListener mListener;
    List<Pokemon> mPokemons;

    public interface OnPokemonSelectedListener{
        public void onPokemonSelected(Pokemon theChoosenOne);
    }

    public PokemonAdapter(List<Pokemon> pokemons, OnPokemonSelectedListener listener){
        mPokemons = pokemons;
        mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.pokemon_row;
        View filaView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(filaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon actual = mPokemons.get(position);
        holder.txvNom.setText(actual.name);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(actual.urlImatge, holder.imvSprite);

    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvSprite;
        public TextView txvNom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvSprite = itemView.findViewById(R.id.imvSprite);
            txvNom = itemView.findViewById(R.id.txvNom);
        }
    }
}

package com.example.appcreaciodinamica.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcreaciodinamica.MainActivity;
import com.example.appcreaciodinamica.R;
import com.example.appcreaciodinamica.model.Persona;

import java.util.List;

public class PersonesAdapter extends RecyclerView.Adapter<PersonesAdapter.ViewHolder> {

    private List<Persona> mPersones;
    private MainActivity mActivity;

    public PersonesAdapter(List<Persona> pPersones, MainActivity activity){
        // desem les dades a un atribut de la classe
        mPersones = pPersones;
        mActivity= activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View filaView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fila, viewGroup, false);

        return new ViewHolder(filaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Persona p = mPersones.get(position);
        holder.txvNom.setText(p.getNom());
        holder.imvFoto.setImageResource(p.getImatgeResourceId());
        if(position==filaSeleccionada) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        } else {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return mPersones.size();
    }
private int filaSeleccionada = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvFoto;
        public TextView txvNom;
        public ViewHolder(@NonNull View fila) {
            super(fila);
            imvFoto = fila.findViewById(R.id.imvFoto);
            txvNom = fila.findViewById(R.id.txvNom);
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int seleccionadaAnterior = filaSeleccionada;
                    filaSeleccionada = getAdapterPosition();
                    //notifyDataSetChanged();
                    notifyItemChanged(filaSeleccionada);
                    notifyItemChanged(seleccionadaAnterior);
                    mActivity.onSelectedItem(filaSeleccionada);
                }
            });
        }
    }
}

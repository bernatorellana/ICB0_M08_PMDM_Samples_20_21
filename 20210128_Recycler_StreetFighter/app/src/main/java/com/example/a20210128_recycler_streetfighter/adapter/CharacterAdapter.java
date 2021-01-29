package com.example.a20210128_recycler_streetfighter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20210128_recycler_streetfighter.R;
import com.example.a20210128_recycler_streetfighter.model.Personatge;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private static final int TIPUS_HEADER = 0;
    private static final int TIPUS_BO = 1;
    private static final int TIPUS_DOLENT = 2;

    private int idxPersonatgeSeleccionat = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case TIPUS_BO:      layout = R.layout.fila; break;
            case TIPUS_DOLENT:  layout = R.layout.fila_dolent; break;
            case TIPUS_HEADER:  layout = R.layout.header; break;
            default:throw new RuntimeException("tipus no suportat");
        }
        View filaView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(filaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(getItemViewType(position)!=TIPUS_HEADER) {
            Personatge p = Personatge.getPersonatges().get(position - 1);
            holder.txtNumero.setText((p.getId() + 1)+"");
            holder.txvDesc.setText(p.getDesc());
            holder.txvNom.setText(p.getNom());
            holder.imvCara.setImageResource(p.getIdRecursImatge());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0) return TIPUS_HEADER;

        Personatge p = Personatge.getPersonatges().get(position-1);
        return p.esDolent()?TIPUS_DOLENT:TIPUS_BO;
    }
    // 0            1           2
    // header       p(0)        P(1)

    @Override
    public int getItemCount() {
        return Personatge.getPersonatges().size() + 1 /*capçalera*/;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvCara;
        public TextView txtNumero;
        public TextView txvNom;
        public TextView txvDesc;
        public ViewHolder(@NonNull View fila) {
            super(fila);
            imvCara = fila.findViewById(R.id.imvCara);
            txtNumero = fila.findViewById(R.id.txtNumero);
            txvNom = fila.findViewById(R.id.txvNom);
            txvDesc = fila.findViewById(R.id.txvDesc);
            /// programar esdeveniments
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharacterAdapter.this.idxPersonatgeSeleccionat = getAdapterPosition();
                }
            });
        }
    }
}

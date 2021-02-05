package com.example.a20210128_recycler_streetfighter.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20210128_recycler_streetfighter.R;
import com.example.a20210128_recycler_streetfighter.model.Personatge;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private static final int TIPUS_HEADER = 0;
    private static final int TIPUS_BO = 1;
    private static final int TIPUS_DOLENT = 2;

    private int idxPersonatgeSeleccionat = -1;
    private ArrayList<Personatge> mPersonatges;

    public CharacterAdapter(){
        mPersonatges = Personatge.getPersonatges();
    }

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
            Personatge p = mPersonatges.get(position - 1);
            holder.txtNumero.setText((p.getId() + 1)+"");
            holder.txvDesc.setText(p.getDesc());
            holder.txvNom.setText(p.getNom());
            holder.imvCara.setImageResource(p.getIdRecursImatge());
            boolean isSeleccionat = this.idxPersonatgeSeleccionat == position;
            holder.itemView.setSelected(isSeleccionat);
            //holder.itemView.setBackgroundColor(isSeleccionat? Color.YELLOW: Color.TRANSPARENT);
//            holder.itemView.setBackgroundResource(!isSeleccionat?
//                                                R.drawable.border_normal:
//                                                R.drawable.border_highlighted);


        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0) return TIPUS_HEADER;

        Personatge p = mPersonatges.get(position-1);
        return p.esDolent()?TIPUS_DOLENT:TIPUS_BO;
    }
    // 0            1           2
    // header       p(0)        P(1)

    @Override
    public int getItemCount() {
        return mPersonatges.size() + 1 /*capçalera*/;
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

                    int anticIdxSeleccionat = idxPersonatgeSeleccionat;
                    idxPersonatgeSeleccionat = getAdapterPosition();
                    notifyItemChanged(anticIdxSeleccionat);
                    notifyItemChanged(idxPersonatgeSeleccionat);

                    Log.d("STREETFIGHTER", "idxPersonatgeSeleccionat:"+idxPersonatgeSeleccionat);
                }
            });
        }
    }

    // mètodes per modificar la llista d'elements
    public void deleteSelected(){
        if(idxPersonatgeSeleccionat>=1) {
            mPersonatges.remove(idxPersonatgeSeleccionat - 1);

            notifyItemRemoved(idxPersonatgeSeleccionat );
            //
            idxPersonatgeSeleccionat = -1;
        }
    }

    public void moveDownSelected() {
        moveSelected(+1);
    }

    public void moveUpSelected() {
        moveSelected(-1);
    }

    private void moveSelected(int i) {

        if(idxPersonatgeSeleccionat>=1) { // -1 no n'hi ha cap seleccionat, 0 capçalera

            int idxFutur = idxPersonatgeSeleccionat+i;
            if(idxFutur>=1 && idxFutur<mPersonatges.size()+1) {
                Personatge p =  mPersonatges.remove(this.idxPersonatgeSeleccionat-1);
                mPersonatges.add(idxFutur - 1, p);
                int idxAnterior = idxPersonatgeSeleccionat;
                idxPersonatgeSeleccionat = idxFutur;
                notifyItemMoved(idxAnterior, idxFutur);
                //notifyItemChanged(idxPersonatgeSeleccionat);

            }
        }
    }
}

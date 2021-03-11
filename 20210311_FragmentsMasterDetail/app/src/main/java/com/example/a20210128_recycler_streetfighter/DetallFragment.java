package com.example.a20210128_recycler_streetfighter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a20210128_recycler_streetfighter.model.Personatge;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallFragment extends Fragment {

    public static final String TAG = "Detall";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_ID = "paramId";


    // TODO: Rename and change types of parameters
    private int mParamId;


    public DetallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param idPersonatge Parameter 1.
     * @return A new instance of fragment DetallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetallFragment newInstance(int idPersonatge) {
        DetallFragment fragment = new DetallFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_ID, idPersonatge);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getInt(ARG_PARAM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detall, container, false);
        Log.d("XXX", "p>"+mParamId);
        ImageView imvCara = root.findViewById(R.id.imvCara);
        TextView txvNom = root.findViewById(R.id.txvNom);
        Personatge p = Personatge.getPersonatge(mParamId);
        Log.d("XXX", "p>"+p);

        imvCara.setImageResource(p.getIdRecursImatge());
        txvNom.setText(p.getNom());

        return root;
    }
}
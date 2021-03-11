package com.example.a20210308_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EdicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EdicioFragment extends Fragment {

    public static final String TAG="A";

    public interface NavigationListener {
        public void MarxarDeA();
    }

    NavigationListener listener;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EdicioFragment() {
        // Required empty public constructor
        // Li diem que mantingui l'estat encara que hi hagi canvi de configuració (girs, etc.)
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof NavigationListener)) {
            throw new RuntimeException("Contenidor no vàlid per aquest fragment. Implementeu la interfície NavigationListener");
        }
        listener = (NavigationListener) context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EdicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EdicioFragment newInstance(String param1, String param2) {
        EdicioFragment fragment = new EdicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edicio, container, false);

        Button button = root.findViewById(R.id.btnGoB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.MarxarDeA();
            }
        });

        return root;

    }
}
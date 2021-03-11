package com.example.a20210128_recycler_streetfighter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20210128_recycler_streetfighter.adapter.CharacterAdapter;
import com.example.a20210128_recycler_streetfighter.model.Personatge;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LlistaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LlistaFragment extends Fragment implements CharacterAdapter.OnSelectedItemListener, ActionMode.Callback {

    public static final String TAG = "LLISTA" ;
    private CharacterAdapter mAdapter;
    private RecyclerView mRcyPersonatges;
    private SelectedItemListener mListener;

    public interface SelectedItemListener {
        public void onSelectedItem(Personatge seleccionat);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(! (context instanceof SelectedItemListener)) {
            throw new RuntimeException("La activity no implementa SelectedItemListener");
        }
        mListener = (SelectedItemListener)context;
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LlistaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LlistaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LlistaFragment newInstance(String param1, String param2) {
        LlistaFragment fragment = new LlistaFragment();
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
        View root = inflater.inflate(R.layout.fragment_llista, container, false);

        mRcyPersonatges = root.findViewById(R.id.rcyPersonatges);
        mRcyPersonatges.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new CharacterAdapter(this);
        mRcyPersonatges.setAdapter(mAdapter);

        Toolbar toolbar = root.findViewById(R.id.toolbar);

        return root;
    }




    //-------------------------------------------------



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
        mListener.onSelectedItem(null);
    }

    @Override
    public void onSelectedItem(Personatge seleccionat) {
        Log.d("STREETFIGHTER","Personatge seleccionat:"+seleccionat);

       mListener.onSelectedItem(seleccionat);
    }

    @Override
    public void onSelectedItemLongClick(Personatge seleccionat) {
        mListener.onSelectedItem(seleccionat);
        // activarem el Contextual Action Mode
        getActivity().startActionMode(this);

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
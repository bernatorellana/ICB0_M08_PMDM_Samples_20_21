package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.adapters.PokemonAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.pokeapi.NetworkUtils;
import com.example.myapplication.pokeapi.Pokemon;
import com.example.myapplication.pokeapi.PokemonApiParser;
import com.example.myapplication.viewmodel.MainActivityViewModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.OnPokemonSelectedListener {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initImageLoader();

        preparaLlista();



        setLoading(true);


        // Crida assíncrona per descarregar el JSON
        Observable.fromCallable(() -> {
            //---------------- START OF THREAD ------------------------------------
            // Això és el codi que s'executarà en un fil
            ViewModelProvider vmp = new ViewModelProvider(this);
            viewModel = vmp.get(MainActivityViewModel.class);
            return true;
            //--------------- END OF THREAD-------------------------------------
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((retornInutil) -> {
                    //-------------  UI THREAD ---------------------------------------
                    // El codi que tenim aquí s'executa només quan el fil
                    // ha acabat !! A més, aquest codi s'executa en el fil
                    // d'interfície gràfica.
                    mostraPokemons();
                    setLoading(false);
                    //-------------  END OF UI THREAD ---------------------------------------
                });

    }

    private void mostraPokemons() {
        if(viewModel!=null) {
            PokemonAdapter adapter = new PokemonAdapter(viewModel.mPokemons, this);
            binding.rcyPokemons.setAdapter(adapter);
        }
    }

    private void initImageLoader() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher_foreground)
                .build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                 .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);


    }

    private void preparaLlista() {

        binding.rcyPokemons.setLayoutManager(new LinearLayoutManager(this));
        binding.rcyPokemons.setHasFixedSize(true);
    }

    private void setLoading(boolean isLoading){
        binding.pgrLoading.setVisibility(isLoading? View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void onPokemonSelected(Pokemon theChoosenOne) {

    }
}
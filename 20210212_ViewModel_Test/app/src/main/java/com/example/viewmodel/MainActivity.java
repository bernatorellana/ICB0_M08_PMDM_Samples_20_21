package com.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

   /* private Button btnA, btnB;
    private TextView txvA, txvB;*/
    private MAViewModel mViewModel;
    ActivityMainBinding binding;
    public static class MAViewModel extends ViewModel {
        private int scoreA, scoreB;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());//R.layout.activity_main);

        //mViewModel = ViewModelProviders.of(this).get(MAViewModel.class);
        ViewModelProvider vmp=new ViewModelProvider(this);
        mViewModel =vmp.get(MAViewModel.class);

        binding.btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.scoreA++;
                update();
            }
        });

        binding.btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.scoreB++;
                update();
            }
        });
        update();
    }
    private void update() {
        binding.txvA.setText(""+mViewModel.scoreA);
        binding.txvB.setText(""+mViewModel.scoreB);
    }
}
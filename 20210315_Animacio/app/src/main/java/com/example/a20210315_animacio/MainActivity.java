package com.example.a20210315_animacio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener {
    private ImageView imvSubZero;
    private ConstraintLayout clyRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imvSubZero = findViewById(R.id.imvSubZero);
        clyRoot = findViewById(R.id.clyRoot);

        imvSubZero.setBackgroundResource(R.drawable.animacio_idle);
        AnimationDrawable animation = (AnimationDrawable) imvSubZero.getBackground();

        animation.start();

        // Modificant directament les propietats gràfiques d'un element de la UI
        /*imvSubZero.setScaleX(5);
        imvSubZero.setScaleY(2);
        imvSubZero.setRotation(45);
        imvSubZero.setImageAlpha(128);
        imvSubZero.setTranslationX(40);
        imvSubZero.setTranslationY(-60);
        */

        clyRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);

    }

    @Override
    public void onGlobalLayout() {
        // des d'aquí podem saber de veritat les amplades i alçades de tots els elements de la interfície

        int w = clyRoot.getWidth();
        int w_sz = imvSubZero.getWidth();
        int separacio = 20;

        float x_inicial_subZero = imvSubZero.getX();


        Log.d("XXX", "amplada:" + w);
        ObjectAnimator o1 = ObjectAnimator.ofFloat(imvSubZero,"X",w-w_sz-separacio);
        o1.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator o2 = ObjectAnimator.ofFloat(imvSubZero,"ScaleX",-1);
        ObjectAnimator o3 = ObjectAnimator.ofFloat(imvSubZero,"X",x_inicial_subZero);
        o3.setInterpolator(new AccelerateDecelerateInterpolator());
        o1.setDuration(3000);
        o3.setDuration(3000);
        o2.setDuration(10);
        AnimatorSet as1 = new AnimatorSet();
        as1.playSequentially(o1, o2, o3);


        //as1.setDuration(6000);
        as1.start();

    }
}
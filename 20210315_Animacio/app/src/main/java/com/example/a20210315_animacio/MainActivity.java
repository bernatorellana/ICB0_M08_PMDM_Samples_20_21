package com.example.a20210315_animacio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener, View.OnClickListener {
    private ImageView imvSubZero;
    private View imvBola;
    private ConstraintLayout clyRoot, clyPersonatge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imvSubZero = findViewById(R.id.imvSubZero);
        clyRoot = findViewById(R.id.clyRoot);
        imvBola = findViewById(R.id.imvBola);
        clyPersonatge = findViewById(R.id.clyPersonatge);
        clyRoot.setOnClickListener(this);

        activaAnimacioSprite(R.drawable.animacio_walk, false);

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
        clyRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        int w = clyRoot.getWidth();
        int w_sz = imvSubZero.getWidth();
        int h_sz = imvSubZero.getHeight();
        int separacio = 20;

        float x_inicial_subZero = imvSubZero.getX();

        Log.d("XXX", "amplada:" + w);
        ObjectAnimator o1 = ObjectAnimator.ofFloat(clyPersonatge,"X",w-w_sz-separacio);
        o1.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator o2 = ObjectAnimator.ofFloat(clyPersonatge,"ScaleX",-1);
        ObjectAnimator o3 = ObjectAnimator.ofFloat(clyPersonatge    ,"X",x_inicial_subZero);
        o3.setInterpolator(new AccelerateDecelerateInterpolator());
        o1.setDuration(3000);
        o3.setDuration(3000);
        o2.setDuration(10);
        AnimatorSet as1 = new AnimatorSet();
        as1.playSequentially(o1, o2, o3);
        //as1.start();
        as1.addListener(new AnimatorEndListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                // aquest codi s'executa quan acaba la seqüència d'animació
                activaAnimacioSprite(R.drawable.animacio_idle, false);
            }
        });
        imvBola.setRotation(45);
        imvBola.setPivotY(0);
        imvBola.setPivotX(imvBola.getWidth()*0.5f);
        imvBola.setPivotY( imvBola.getHeight() + h_sz*0.5f);

        ObjectAnimator ob1 = ObjectAnimator.ofFloat(imvBola, "Rotation", 180);
        ObjectAnimator ob2 = ObjectAnimator.ofFloat(imvBola, "ScaleX", -1);
        ObjectAnimator ob3 = ObjectAnimator.ofFloat(imvBola, "Rotation", 0);
        ob1.setDuration(3000);
        ob3.setDuration(3000);
        ob2.setDuration(10);
        AnimatorSet as2 = new AnimatorSet();
        as2.playSequentially(ob1, ob2, ob3);

        AnimatorSet asConjunt = new AnimatorSet();
        asConjunt.playTogether(as1, as2);
        asConjunt.start();
    }

    @Override
    public void onClick(View v) {
        double salt = imvSubZero.getHeight() * 0.75;
        ObjectAnimator o1 = ObjectAnimator.ofFloat(imvSubZero,
                "TranslationY",
                0,
                (float)-salt,
                0 );
        o1.setDuration(1500);
        o1.addListener(new AnimatorEndListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                AnimationDrawable animationDrawable =activaAnimacioSprite(R.drawable.animacio_land, true);
                engegaFil();
            }
        });
        activaAnimacioSprite(R.drawable.animacio_jump, true);

        o1.start();

    }

    private void engegaFil() {
        Observable.fromCallable(() -> {
            //---------------- START OF THREAD ------------------------------------
            // Això és el codi que s'executarà en un fil
            Thread.sleep(500);
            return true;
            //--------------- END OF THREAD-------------------------------------
        })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((retornInutil) -> {
                    //-------------  UI THREAD ---------------------------------------
                    activaAnimacioSprite(R.drawable.animacio_idle, false);
                    //-------------  END OF UI THREAD ---------------------------------------
                });
    }

    private AnimationDrawable activaAnimacioSprite(int animacioResId, boolean isOneShot) {
        imvSubZero.setBackgroundResource(animacioResId);
        AnimationDrawable animationDrawable = (AnimationDrawable) imvSubZero.getBackground();
        animationDrawable.setOneShot(isOneShot);
        animationDrawable.start();
        return animationDrawable;
    }
}
package com.example.appcreaciodinamica.model;


import com.example.appcreaciodinamica.R;

import java.util.ArrayList;
import java.util.List;

public class Persona {

    private String  mNom;
    private String  mCognom;
    private String  mTelefon;
    private boolean mEsHome;
    private int     mImatgeResourceId;
    private String  mProvincia;


    private static List<Persona> persones;
    public static List<Persona> getPersones(){

        if(persones==null) {
            persones = new ArrayList<Persona>();
            persones.add(
                    new Persona("Donald", "Trump",
                            "666", true, R.drawable.mr_trump, "Ohio")
            );
            persones.add(
                    new Persona("Hillary", "Clinton",
                            "777", false, R.drawable.mr_trump, "Wisconsin")
            );
        }
        return persones;
    }


    public Persona(String mNom, String mCognom, String mTelefon, boolean esHome, int imatgeResourceId, String provincia) {
        this.mNom = mNom;
        this.mCognom = mCognom;
        this.mTelefon = mTelefon;
        this.mEsHome = esHome;
        this.mImatgeResourceId = imatgeResourceId;
        this.mProvincia = provincia;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String mNom) {
        this.mNom = mNom;
    }

    public String getCognom() {
        return mCognom;
    }

    public void setCognom(String mCognom) {
        this.mCognom = mCognom;
    }

    public String getTelefon() {
        return mTelefon;
    }

    public void setTelefon(String mTelefon) {
        this.mTelefon = mTelefon;
    }

    public boolean isEsHome() {
        return mEsHome;
    }

    public void setEsHome(boolean esHome) {
        this.mEsHome = esHome;
    }

    public int getImatgeResourceId() {
        return mImatgeResourceId;
    }

    public void setImatgeResourceId(int imatgeResourceId) {
        this.mImatgeResourceId = imatgeResourceId;
    }

    public String getProvincia() {
        return mProvincia;
    }

    public void setProvincia(String provincia) {
        this.mProvincia = provincia;
    }
}

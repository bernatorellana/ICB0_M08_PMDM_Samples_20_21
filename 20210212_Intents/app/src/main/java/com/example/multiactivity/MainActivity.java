package com.example.multiactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGo, btnCamera;
    EditText edtCodi;
    ImageView imvFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-----------------------
        btnGo = findViewById(R.id.btnGo);
        edtCodi = findViewById(R.id.edtCodi);
        btnCamera = findViewById(R.id.btnCamera);
        imvFoto = findViewById(R.id.imvFoto);
        btnGo.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
    }
    private static final int REQUEST_CODE_1 = 1;
    private static final int REQUEST_CODE_2 = 2;
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnGo) {
            Intent i = new Intent(this, FitxaActivity.class);
            i.putExtra(FitxaActivity.PARAM1_CODI, edtCodi.getText().toString());
            //startActivity(i);
            startActivityForResult(i, REQUEST_CODE_1);
        } else if(v.getId()==R.id.btnCamera) {


            // Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
            /*
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, REQUEST_CODE_2);

            //Intent i = new Intent(Intent.ACTION_SENDTO);
            //i.setType("text/plain");
            if(isAvailable(this, i)) {
                startActivityForResult(i, REQUEST_CODE_2);

            } else {
                Toast.makeText(this,
                        "Intent no disponible",
                        Toast.LENGTH_LONG);
            }*/
        }
    }

    /**
     * Aquesta classe ens permet saber si un intent es podrà obrir
     * en el mòbil actual
     * @param ctx
     * @param intent
     * @return
     */
    public static boolean isAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_1) {
            if(resultCode== Activity.RESULT_OK){
                String nom = data.getStringExtra(FitxaActivity.PARAM_OUT_NOM_PERSONATGE);
                Log.d("APP", "El nom canviat és:"+nom);
            }
        } else  if(requestCode==REQUEST_CODE_2) {
            if(resultCode== Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imvFoto.setImageBitmap(imageBitmap);
            }
        }
    }
}
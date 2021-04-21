package com.example.a20210415_camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback {

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    SurfaceView srfPreview;
    Camera cam ;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);

        }
        mp = MediaPlayer.create(this, R.raw.shutter);

        srfPreview = findViewById(R.id.srfPreview);
        srfPreview.getHolder().addCallback(this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        cam = Camera.open();
        Camera.Parameters parameters = cam.getParameters();
        parameters.setJpegQuality(80);
        parameters.setPictureFormat(ImageFormat.JPEG);
        cam.setParameters(parameters);

        //-----------------------
        if(this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE){
            cam.setDisplayOrientation(90);
        }
        try {
            cam.setPreviewDisplay(srfPreview.getHolder());
        }catch (IOException ex){
            Log.e("XXX", "Error obrint càmera" , ex);
            cam.release();
        }
        cam.startPreview();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        cam.stopPreview();
        cam.release();
    }

    public void takePhoto(View view) {
        cam.takePicture(this,null, null, this);
    }

    @Override
    public void onShutter() {
        mp.start();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        // Metadescripció de la fotografia
        ContentValues contentValues = new ContentValues(3);
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "Títol de prova");
        contentValues.put(MediaStore.Images.Media.TITLE, "Títol de prova");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Descripció de prova");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");



        Uri imageFileUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues);
        // imageFileUri  és de l’estil : content://media/external/images/media/231

        // Ara desem la imatge
        try {
            OutputStream imageFileOS =
                    getContentResolver().openOutputStream(imageFileUri);
            imageFileOS.write(data);

            imageFileOS.flush();
            imageFileOS.close();
        } catch(Exception ex){
            Log.e("XXXX", "error desant jpg", ex);
        }

        cam.startPreview();

    }
}
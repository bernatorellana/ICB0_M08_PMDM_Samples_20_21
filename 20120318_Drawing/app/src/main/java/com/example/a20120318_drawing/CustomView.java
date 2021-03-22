package com.example.a20120318_drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;

public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        Paint pBlack = new Paint();
        pBlack.setColor(Color.argb(255,27,27,80));
        pBlack.setStyle(Paint.Style.FILL);

        Paint pWhite = new Paint();
        pWhite.setColor(Color.WHITE);
        pWhite.setStyle(Paint.Style.FILL);

        float l=0,t=0,r,b;
        float midaCasellaX = getWidth() / 8.0f;
        float midaCasellaY = getHeight() / 8.0f;

        b = t + midaCasellaY-1;
        for(int fila=0;fila<8;fila++) {
            l=0;
            r = l + midaCasellaX-1;
            for(int columna=0;columna<8;columna++) {
                canvas.drawRect(l,t,r,b,(fila+columna)%2==0? pWhite:pBlack);
                l+= midaCasellaX;
                r+= midaCasellaX;
            }
            t += midaCasellaY;
            b += midaCasellaY;
        }
        Bitmap pawnBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.black_pawn);
        //canvas.drawBitmap(pawnBitmap,0,0,pWhite);
        Rect src = new Rect(0,0,pawnBitmap.getWidth(), pawnBitmap.getHeight());

        int files[]={1,6};
        for(int f=0;f<files.length;f++ ){
            for(int columna=0;columna<8;columna++) {
                pintaFigura(canvas, pBlack, midaCasellaX, midaCasellaY, pawnBitmap, src,
                        files[f], columna);
            }
        }

        // Dibuixem un cercle
        canvas.drawCircle(midaCasellaX*0.5f, midaCasellaY*0.5f,
                Math.min( midaCasellaX*0.5f,  midaCasellaY*0.5f),pBlack);
        // Dibuixem línies
        Paint pLinia = new Paint();
        pLinia.setColor(Color.GREEN);
        pLinia.setStyle(Paint.Style.STROKE);
        pLinia.setStrokeWidth(3);
        float[] punts = {   0.f,0.f,
                            midaCasellaX, midaCasellaY,
                                midaCasellaX, midaCasellaY,
                                2*midaCasellaX, 0,
                            2*midaCasellaX, 0,
                            3*midaCasellaX, midaCasellaY
        };
        canvas.drawLines(punts, pLinia);

        //----------------------------------------
        // Dibuix de camins

        float w = getWidth();
        float h = getHeight();
        float ratio = 0.25f;
        Path p = new Path();
        p.moveTo(0, 0);
        p.cubicTo(ratio * w, ratio * h, (1 - ratio) * w, ratio * h, w, 0);
        p.cubicTo((1 - ratio) * w, ratio * h , (1 - ratio) * w, h * (1-ratio) , w   , h);
        p.cubicTo((1 - ratio) * w, h * (1-ratio) , (ratio) * w, h * (1-ratio) , 0,h);
        p.cubicTo((ratio) * w, h * (1-ratio), ratio*w, ratio*h, 0,0);

        canvas.drawPath(p,pLinia);


    }

    private void pintaFigura(Canvas canvas, Paint pBlack, float midaCasellaX, float midaCasellaY, Bitmap pawnBitmap, Rect src, int fila, int columna) {
        int xDst=(int)(columna*midaCasellaX);
        int yDst=(int)(fila*midaCasellaY);
        Rect dst = new Rect(xDst,yDst,(int)(xDst+midaCasellaX), (int)(yDst+midaCasellaY));
        canvas.drawBitmap(pawnBitmap,src, dst, pBlack);
    }

}

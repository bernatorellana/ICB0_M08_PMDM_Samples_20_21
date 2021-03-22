package com.example.a20210322_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Pissarra extends View implements View.OnLayoutChangeListener {

    private Bitmap buffer;
    private Canvas bufferCanvas;
    private Paint paint;
    public Pissarra(Context context) {
        this(context, null);
    }

    public Pissarra(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnLayoutChangeListener(this);
        //---------------------------------------------------
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        buffer = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bufferCanvas = new Canvas(buffer);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(buffer,0,0,paint);
        /*if(cursorActiu) {
            canvas.drawCircle(coordCursor.x, coordCursor.y, 100, paint);
        }*/
    }

    boolean cursorActiu = false;
    PointF coordCursor = new PointF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //
        int action = event.getActionMasked();
        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                cursorActiu=true;
                coordCursor.x =  event.getX();
                coordCursor.y =  event.getY();

                bufferCanvas.drawCircle(coordCursor.x, coordCursor.y, 100, paint);

                break;
            case MotionEvent.ACTION_UP:
                cursorActiu=false;
        }
        this.invalidate();

        return true;
    }


}

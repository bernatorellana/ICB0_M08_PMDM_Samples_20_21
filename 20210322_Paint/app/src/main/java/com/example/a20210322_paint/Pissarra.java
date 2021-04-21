package com.example.a20210322_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Pissarra extends View implements View.OnLayoutChangeListener {

    private Estat mEstat  = Estat.BRUSH;
    private Bitmap buffer;
    private Bitmap bufferColorPicker;
    private Canvas bufferCanvas;
    private Paint paint;
    private Paint paintStroke;
    private Paint foscSemitransparent;
    private Path liniaActual;
    private int mColorSeleccionat;
    //------------------------------------------
    RectF rscDstColorPicker;
    float ampladaColorPicker, alsadaColorPicker;
    //-------------------------------------------
    public Pissarra(Context context) {
        this(context, null);
    }

    public Pissarra(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnLayoutChangeListener(this);

        mColorSeleccionat = Color.GREEN;
        //---------------------------------------------------
        paint = new Paint();
        paint.setColor(mColorSeleccionat);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //---------------------------------------------------
        paintStroke = new Paint();
        paintStroke.setColor(mColorSeleccionat);
        paintStroke.setStrokeWidth(3);
        paintStroke.setStyle(Paint.Style.STROKE);
        //---------------------------------------------------
        foscSemitransparent = new Paint();
        foscSemitransparent.setColor(Color.argb(125, 50,50,50));
        foscSemitransparent.setStyle(Paint.Style.FILL_AND_STROKE);
        setEstat(Estat.BRUSH);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        buffer = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bufferColorPicker= BitmapFactory.decodeResource(getResources(),R.drawable.color_picker);
        bufferCanvas = new Canvas(buffer);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(buffer,0,0,paint);
        if(mEstat==Estat.LINE) {
            if (liniaActual != null) {
                canvas.drawPath(liniaActual, paintStroke);
            }
        } else
        if(mEstat==Estat.COLOR) {
            canvas.drawRect(0,0, getWidth(),getHeight(), foscSemitransparent);
            Rect recSrc = new Rect(0,0,bufferColorPicker.getWidth(), bufferColorPicker.getHeight());

            float RA_Src = bufferColorPicker.getWidth() / bufferColorPicker.getHeight(); // 16/9

            if( this.getWidth()> this.getHeight()) {
                // horitzontal
                alsadaColorPicker = this.getHeight();
                ampladaColorPicker = alsadaColorPicker * RA_Src;
                float baseX = (getWidth()-ampladaColorPicker)*0.5f;
                rscDstColorPicker = new RectF(baseX,0, baseX+ampladaColorPicker, alsadaColorPicker);
            } else {
                // vertical
                ampladaColorPicker = this.getWidth();
                alsadaColorPicker = ampladaColorPicker / RA_Src;
                float baseY = (getHeight()-alsadaColorPicker)*0.5f;
                rscDstColorPicker = new RectF(0,baseY, ampladaColorPicker, baseY+alsadaColorPicker);
            }
            canvas.drawBitmap(bufferColorPicker,recSrc, rscDstColorPicker, null);

            paint.setColor(mColorSeleccionat);
            paintStroke.setColor(mColorSeleccionat);

            canvas.drawCircle(60,200, 60, paint);
        }

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
        if (mEstat == Estat.BRUSH) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    cursorActiu = true;
                    coordCursor.x = event.getX();
                    coordCursor.y = event.getY();
                    bufferCanvas.drawCircle(coordCursor.x, coordCursor.y, 100, paint);
                    break;
                case MotionEvent.ACTION_UP:
                cursorActiu = false;
            }
        } else if (mEstat == Estat.LINE) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    liniaActual = new Path();
                    coordCursor.x = event.getX();
                    coordCursor.y = event.getY();
                case MotionEvent.ACTION_MOVE:
                     PointF coordActual = new PointF();
                    coordActual.x = event.getX();
                    coordActual.y = event.getY();
                    liniaActual.rewind();
                    liniaActual.moveTo(coordCursor.x, coordCursor.y);
                    liniaActual.lineTo(coordActual.x, coordActual.y);
                    break;
                case MotionEvent.ACTION_UP:
                    bufferCanvas.drawPath(liniaActual, paintStroke);
                    liniaActual=null;
                    break;
            }
        } else if(mEstat == Estat.COLOR){


            float factorZoom = bufferColorPicker.getWidth() / ampladaColorPicker ;
            float x = (event.getX() - rscDstColorPicker.left) * factorZoom;
            float y = (event.getY() - rscDstColorPicker.top) * factorZoom;
            if(x>=0 && x<bufferColorPicker.getWidth() &&
                    y>=0 && y<bufferColorPicker.getHeight()  ) {
                mColorSeleccionat = bufferColorPicker.getPixel((int) x, (int) y);
            }
        }
        this.invalidate();

        return true;
    }


    public void setBrushMode() {
        setEstat(Estat.BRUSH);
    }

    public void setLineMode() {
        setEstat(Estat.LINE);
    }

    public void setMoveMode() {
        setEstat(Estat.MOVE);
    }


    public void setColorMode() {
        setEstat(Estat.COLOR);
    }

    Estat mAnterior;
    public void setEstat(Estat nouEstat) {

        if(nouEstat==Estat.COLOR) {
            mAnterior = mEstat;

        }

        mEstat = nouEstat;
        this.invalidate();
    }


    //--------------------------------------------
    private enum Estat {
        BRUSH,
        LINE,
        MOVE,
        COLOR
    }
}

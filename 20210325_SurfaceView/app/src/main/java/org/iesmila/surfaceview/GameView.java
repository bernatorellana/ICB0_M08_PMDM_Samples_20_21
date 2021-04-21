package org.iesmila.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private ThreadGameView mThread;

    Bitmap w[] = new Bitmap[5];
    Bitmap worldBitmap, backgroundBitmap;
    PointF wormPos;
    PointF wormDims;
    PointF windowPos;
    int wormXDirecion;
    private int fallSpeed=15;

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.getHolder().setFormat(PixelFormat.RGBA_8888);
        this.getHolder().addCallback(this);
        this.setOnTouchListener(this);
        worldBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.map);
        /*.
                copy(Bitmap.Config.ARGB_8888, true);
        worldBitmap.setHasAlpha(true);*/
        backgroundBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sky);
        w[0]=BitmapFactory.decodeResource(getResources(),R.drawable.w1);
        w[1]=BitmapFactory.decodeResource(getResources(),R.drawable.w2);
        w[2]=BitmapFactory.decodeResource(getResources(),R.drawable.w3);
        w[3]=BitmapFactory.decodeResource(getResources(),R.drawable.w4);
        w[4]=BitmapFactory.decodeResource(getResources(),R.drawable.w5);

        wormDims = new PointF(w[0].getWidth(),w[0].getHeight());
        wormPos = new PointF(1408,800);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mThread = new ThreadGameView(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if(mThread!=null){
            mThread.stopIt();
        }
    }

    PointF downP;
    float speedX;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("XXX", "*"+motionEvent.getActionMasked());
        switch (motionEvent.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                downP = getPoint(motionEvent);
                break;
            case MotionEvent.ACTION_MOVE:
                PointF currentP = getPoint(motionEvent);
                speedX = currentP.x-downP.x;
                wormXDirecion = (int)Math.signum(speedX);
                Log.d("XXX", ">"+speedX);
                break;
            case MotionEvent.ACTION_UP:
                speedX = 0;
                break;
        }
        return true;
    }
private class XocH{
        public boolean xoca;
        public float up;
}

    public void updateScene(){
        int groundDistance = groundDistance(this.wormPos, this.wormDims);
        if(groundDistance>0) {
            this.wormPos.y+=Math.min(groundDistance, fallSpeed );
        } else {
            if(speedX!=0) {
                PointF newPos = new PointF(this.wormPos);
                newPos.x += speedX/30.0f;
                XocH xoc = isXocWorm(newPos, this.wormDims);
                if(!xoc.xoca) {
                    this.wormPos = newPos;
                 }
            }


        }
        // update sprite if we are moving
        if(speedX!=0) {
            spriteIndex = (spriteIndex + 1) % 5;
        }
    }

    private XocH isXocWorm(PointF pos, PointF wormDims) {
        PointF p = new PointF(pos);
        for(int j=0;j<wormDims.y;j++){
               p.x = pos.x;
            for(int i=0;i<wormDims.x;i++){
                if (Color.alpha(worldBitmap.getPixel((int)p.x,(int)p.y)) > 0) {
                    XocH x = new XocH();
                    x.up= p.y-pos.y;
                    x.xoca =x.up>5;
                    Log.d("XXX", "x.up"+x.up);
                    if(x.xoca) {
                        pos.y+=x.up;
                    }
                    return x;
                }
                p.x++;
            }
            p.y++;
        }
        XocH x = new XocH();
        x.up= 0;
        x.xoca=false;
        return x;
    }

    int spriteIndex =0;
    float x=0.1f, y=100.0f;
    public void paint(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        if(wormPos==null) return;
        this.windowPos = this.windowPosFromPlayerPos(this.wormPos);
        Rect src = new Rect((int)windowPos.x, (int)windowPos.y,
                (int)windowPos.x+getWidth(),(int)windowPos.y+getHeight());

        Rect dst = new Rect(0,0,getWidth(),getHeight());
        canvas.drawBitmap(backgroundBitmap,src,dst,null);
        canvas.drawBitmap(worldBitmap,src,dst,null);


        Matrix m = new Matrix();
        if(wormXDirecion>0) {
            m.postTranslate(-w[0].getWidth(),0);
            m.postScale(-1,1);
        }
        PointF wormPosScreen = this.worldToScreen(this.wormPos, this.windowPos);
        m.postTranslate(wormPosScreen.x,wormPosScreen.y);

        canvas.drawBitmap(w[spriteIndex],m, null);
        //canvas.drawBitmap(w[i],0,0,null);


    }

    private int groundDistance(PointF playerPos, PointF dims){
        int h = worldBitmap.getHeight();
        int y = (int)( playerPos.y+dims.y);
        int d=0;
        while(y<h) {
            for (int x = (int) playerPos.x; x < playerPos.x + dims.x; x++) {
                if (Color.alpha(worldBitmap.getPixel(x, y)) > 0) {
                    return d;
                }
            }
            d++;
            y++;
        }
        return d;
    }


    private PointF windowPosFromPlayerPos(PointF playerPos) {
        Log.d("XXX", playerPos+">"+getWidth()+"--"+getHeight());
        float mx = getWidth()/2;
        float my = getHeight() * 3.0f/4.0f;
        float x = playerPos.x - mx;
        float y = playerPos.y - my;
        x=clamp(x,0,worldBitmap.getWidth()-getWidth());
        y=clamp(y,0,worldBitmap.getHeight()-getHeight());
        return new PointF(x,y);
    }

    private float clamp(float val, float min, float max) {
        if (val<min) return min;
        if(val>max) return max;
        return val;
    }

    private PointF worldToScreen(PointF worldPoint, PointF windowPos){
        return new PointF( worldPoint.x - windowPos.x, worldPoint.y-windowPos.y );
    }


    private PointF getPoint(MotionEvent motionEvent) {
        return new PointF(motionEvent.getX(), motionEvent.getY());
    }
}

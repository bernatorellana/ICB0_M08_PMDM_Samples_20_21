package org.iesmila.surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;

class ThreadGameView extends Thread{
    /**
     * Time per frame for 60 FPS
     */
    private static final int MAX_FRAME_TIME = (int) (1000.0 / 10.0);
    //private static final int MAX_FRAME_TIME = (int) (1000.0 / 60.0);

    private final GameView gameView;
    boolean stopped = false;
    long frameStartTime = System.nanoTime();
    long frameTime;

    public ThreadGameView(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void run() {
        super.run();
        Log.d("XXX","Runinng");
        while(!stopped) {
            frameStartTime = System.nanoTime();
            Canvas canvas = gameView.getHolder().lockCanvas();
            if (canvas != null) {
                gameView.updateScene();
                gameView.paint(canvas);
                gameView.getHolder().unlockCanvasAndPost(canvas);
            }
            frameTime = (System.nanoTime() - frameStartTime) / 1000000;

            if (frameTime < MAX_FRAME_TIME)
            {
                try
                {
                    Thread.sleep(MAX_FRAME_TIME - frameTime);
                } catch (InterruptedException e)
                {
                    // ignore
                }
            }


        }
    }


    public void stopIt() {
        stopped = true;
    }
}

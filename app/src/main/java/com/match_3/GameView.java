package com.match_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

private final DrawThread thread;
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new DrawThread(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
thread.setRunning(true);
thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
         canvas.drawColor(Color.RED);
    }
}

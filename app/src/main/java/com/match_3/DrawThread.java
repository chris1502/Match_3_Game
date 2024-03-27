package com.match_3;

import android.graphics.Canvas;

public class DrawThread extends Thread {

    private final GameView view;
    public boolean running = false;
    public static int fps;
    public static int ups;

    public DrawThread(GameView gameView) {
        this.view = gameView;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        final int Max_FPS = 60;
        final int MAX_UPS = 60;

        final double fOPTIMAL_TIME = (double) 1000000000 / Max_FPS;
        final double uOPTIMAL_TIME = (double) 1000000000 / MAX_UPS;

        double uDeltaTime = 0, fDeltaTime = 0;
        int frames = 0, updates = 0;
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();

//Game Loop starts from here...

        while (running) {
            long currentTime = System.nanoTime();
        uDeltaTime +=(currentTime - startTime);
        fDeltaTime +=(currentTime - startTime);
        startTime = currentTime;

        if (uDeltaTime >= uOPTIMAL_TIME){
            view.update();
            updates ++;
            uDeltaTime -= uOPTIMAL_TIME;
        }
        if (fDeltaTime >fOPTIMAL_TIME) {
            Canvas canvas = view.getHolder().lockCanvas(null);
            if (canvas != null) {
                synchronized (view.getHolder()) {
                    view.draw(canvas);
                }
                view.getHolder().unlockCanvasAndPost(canvas);
            }
            frames++;
            fDeltaTime -= fOPTIMAL_TIME;
        }
        if (System.currentTimeMillis() - timer >= 1000){
            fps = frames;
            ups = updates;
            updates = 0;
            frames = 0;
            timer += 1000;
        }
        }

    }
}
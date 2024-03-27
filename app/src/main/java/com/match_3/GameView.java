package com.match_3;

import static com.match_3.Constants.cellWidth;
import static com.match_3.Constants.drawX;
import static com.match_3.Constants.drawY;
import static com.match_3.Constants.screenHeight;
import static com.match_3.Constants.screenWidth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private  DrawThread thread;
    private SpriteSheet spriteSheet;
    private Jewel jewel;
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new DrawThread(this);
        spriteSheet = new SpriteSheet(getContext());
        jewel = new Jewel((int)drawX,(int)drawY,0);
        System.out.println("drawY" + drawY);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {


    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK);
        canvas.drawBitmap(spriteSheet.topBG,0,0,null);
        canvas.drawBitmap(spriteSheet.bottomBG,0,drawY+cellWidth*9,null);
        canvas.drawBitmap(spriteSheet.bg_middle,0,drawY,null);
        for (int i = 0; i < 10;i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawLine(0, drawY + (i * cellWidth), cellWidth * 10, drawY + (i * cellWidth), p);
                canvas.drawLine(j*cellWidth,drawY,j*cellWidth,drawY + cellWidth*9,p);

            }
        }
        canvas.drawBitmap(spriteSheet.blue,jewel.poseX,jewel.poseY,null);

    }
}



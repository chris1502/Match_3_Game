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
    private  Jewel [][] board;
    private int [][]level = {
            {3, 2, 1, 2, 3, 1, 5, 4, 6},
            {5, 3, 6, 2, 1, 5, 4, 5, 1},
            {4, 5, 3 ,2, 3, 4, 4, 6, 4},
            {5, 6, 1, 4, 2, 6, 2, 5, 6},
            {6, 3, 3, 5, 3, 3, 2, 2, 4},
            {2, 2, 4, 3, 4, 2, 4, 2, 3},
            {1, 6, 4, 2, 6, 4, 3, 3, 5},
            {4, 3, 5, 2, 1, 6, 2, 3, 5},
            {5, 2, 1, 2, 3, 1, 5, 2, 1}
    };
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new DrawThread(this);
        spriteSheet = new SpriteSheet(getContext());
        init();

    }
    public void init(){
        board = new Jewel[level.length][level[0].length];
        for (int i=0;i<level.length;i++){
            for (int j=0;j<level[0].length;j++){
                board[i][j] = new Jewel((int)drawX + (cellWidth*j),(int)drawY+(cellWidth*i),level[i][j]);
            }
        }
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
        canvas.drawBitmap(spriteSheet.topBG,0,-cellWidth*2,null);
        canvas.drawBitmap(spriteSheet.bottomBG,0,drawY+cellWidth*9,null);
        canvas.drawBitmap(spriteSheet.bg_middle,0,drawY,null);
        for (int i = 0; i < 10;i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawLine(0, drawY + (i * cellWidth), cellWidth * 10, drawY + (i * cellWidth), p);
                canvas.drawLine(j*cellWidth,drawY,j*cellWidth,drawY + cellWidth*9,p);

            }
        }
        for (Jewel[] jewels : board){
            for (Jewel jewel : jewels){
                jewel.drawJewel(canvas,spriteSheet);
            }
        }

    }
}



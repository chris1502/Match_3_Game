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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private  DrawThread thread;
    private SpriteSheet spriteSheet;
    private Jewel jewel;
    private  Jewel [][] board;
    private float oldX;
    private float oldY;
    private int poseI;
    private int poseJ;
    public String direction;
    private int newPoseI;
    private int newPoseJ;
    private boolean move = false;
    enum GameState{
        swapping,checkSwapping,crushing,update,nothing
    }
    private int swapIndex = 8;
    public GameState gameState;
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
        gameState = GameState.nothing;
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

    public void update(){
        switch (gameState){
            case swapping:
                swap();
                break;
        }
    }
private void swap(){
        if (swapIndex > 0) {
            switch (direction) {
                case "right":
                    board[poseI][poseJ + 1].poseX -= cellWidth / 8;
                    board[poseI][poseJ].poseX += cellWidth / 8;
                    break;
                case "left":
                    board[poseI][poseJ - 1].poseX += cellWidth / 8;
                    board[poseI][poseJ].poseX -= cellWidth / 8;
                    break;
                case "up":
                    board[poseI - 1][poseJ].poseY += cellWidth / 8;
                    board[poseI][poseJ].poseY -= cellWidth / 8;
                    break;
                case "down":
                    board[poseI + 1][poseJ].poseY -= cellWidth / 8;
                    board[poseI][poseJ].poseY += cellWidth / 8;
                    break;
            }
            swapIndex--;
        }else{
            Jewel jewel;
            jewel = board[poseI][poseJ];
            board[poseI][poseJ] = board[newPoseI][newPoseJ];
            board[newPoseI][newPoseJ] = jewel;

            board[poseI][poseJ].poseX = (int) (poseJ * cellWidth + drawX);
            board[poseI][poseJ].poseY = (int) (poseI * cellWidth + drawY);
            board[newPoseI][newPoseJ].poseX = (int) (newPoseJ * cellWidth +drawX);
            board[newPoseI][newPoseJ].poseY = (int) (newPoseI * cellWidth +drawY);
            if (gameState == GameState.swapping){
                gameState = GameState.nothing;
            }
        }
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                poseI = (int) (oldY - drawY) / cellWidth;
                poseJ = (int) (oldX - drawX) / cellWidth;
                move = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (gameState == GameState.nothing){
                    float newX = event.getX();
                    float newY = event.getY();
                    float deltaX = Math.abs(newX - oldX);
                    float deltaY = Math.abs(newY - oldY);
                    if (move && (deltaX > 5 || deltaY > 5 )){
                        //checking how many pixels moved, if we move more than 3o pixels we start checking in which direction
                        move = false;
                        if (Math.abs( oldX - newX) > Math.abs(oldY - newY)){
                            //if the first x touch  - the end of the x touch is bigger
                            //first y touch - the end of the touch y so the direction is is in the x direction
                            //now wil check which is bigger the oldX or the newX, know if its left or right
                            if (newX > oldX){
                                direction = "right";
                                newPoseJ = poseJ + 1;
                            }else{
                                direction = "left";
                                newPoseJ = poseJ - 1;
                            }
                            newPoseI = poseI;
                        }
                        if (Math.abs(oldY - newY) > Math.abs(oldX - newX)){
                            if (newY > oldY){
                                direction = "down";
                                newPoseI = poseI + 1;
                            }else{
                                direction = "up";
                                newPoseI = poseI - 1;
                            }
                            newPoseJ = poseJ;
                        }
                        gameState = GameState.swapping;

                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {}




    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {}

    }





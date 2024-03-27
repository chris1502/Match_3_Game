package com.match_3;

import android.graphics.Canvas;

public class Jewel {
    public int poseX;
    public int poseY;
    public int color;

    public Jewel(int poseX, int poseY, int color) {
        this.poseX = poseX;
        this.poseY = poseY;
        this.color = color;
    }

    public void drawJewel(Canvas canvas, SpriteSheet spriteSheet){
        switch (color){
            case 1:
                canvas.drawBitmap(spriteSheet.red,poseX,poseY,null);
                break;
            case 2:
                canvas.drawBitmap(spriteSheet.blue,poseX,poseY,null);
                break;
            case 3:
                canvas.drawBitmap(spriteSheet.yellow,poseX,poseY,null);
                break;
            case 4:
                canvas.drawBitmap(spriteSheet.green,poseX,poseY,null);
                break;
            case 5:
                canvas.drawBitmap(spriteSheet.purple,poseX,poseY,null);
                break;
            case 6:
                canvas.drawBitmap(spriteSheet.orange,poseX,poseY,null);
                break;

        }
    }


}

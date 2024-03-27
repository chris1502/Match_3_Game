package com.match_3;

import static com.match_3.Constants.*;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

public class SpriteSheet {
    public Bitmap topBG;
    public Bitmap bottomBG;
    public Bitmap bg_middle;
    private Bitmap jewelsBMP;
    public Bitmap red;
    public Bitmap orange;
    public Bitmap yellow;
    public Bitmap green;
    public Bitmap blue;
    public Bitmap purple;
    public SpriteSheet(Context context){
        AssetManager assetManager = context.getAssets();

        try{
            InputStream istr = assetManager.open("playbg_top.jpg");
            topBG = BitmapFactory.decodeStream(istr);
            topBG = Bitmap.createBitmap(topBG,0,0,topBG.getWidth(),topBG.getHeight());
            topBG = Bitmap.createScaledBitmap(topBG,screenWidth,cellWidth*5,false);
            istr = assetManager.open("playbg_bottom.png");
            bottomBG = BitmapFactory.decodeStream(istr);
            bottomBG = Bitmap.createBitmap(bottomBG,0,0,bottomBG.getWidth(),bottomBG.getHeight());
            bottomBG = Bitmap.createScaledBitmap(bottomBG,screenWidth,bottomBG.getHeight(),false);
            istr = assetManager.open("bg_middle.jpg");
            bg_middle = BitmapFactory.decodeStream(istr);
            bg_middle = Bitmap.createBitmap(bg_middle,0,0,bg_middle.getWidth(),bg_middle.getHeight());
            bg_middle = Bitmap.createScaledBitmap(bg_middle,screenWidth,cellWidth*9,false);
            istr = assetManager.open("jewels.jpg");
            jewelsBMP = BitmapFactory.decodeStream(istr);
            jewelsBMP = Bitmap.createBitmap(jewelsBMP,0,0,jewelsBMP.getWidth(),jewelsBMP.getHeight());
            red = Bitmap.createBitmap(jewelsBMP,0,0,51,51);
            red = Bitmap.createScaledBitmap(red,cellWidth,cellWidth,false);
            orange = Bitmap.createBitmap(jewelsBMP,272,0,51,51);
            orange = Bitmap.createScaledBitmap(orange,cellWidth,cellWidth,false);
            yellow = Bitmap.createBitmap(jewelsBMP,111,0,51,51);
            yellow = Bitmap.createScaledBitmap(yellow,cellWidth,cellWidth,false);
            green = Bitmap.createBitmap(jewelsBMP,165,0,51,51);
            green = Bitmap.createScaledBitmap(green,cellWidth,cellWidth,false);
            blue = Bitmap.createBitmap(jewelsBMP,56,0,51,51);
            blue = Bitmap.createScaledBitmap(blue,cellWidth,cellWidth,false);
            purple = Bitmap.createBitmap(jewelsBMP,218,0,51,51);
            purple = Bitmap.createScaledBitmap(purple,cellWidth,cellWidth,false);




        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

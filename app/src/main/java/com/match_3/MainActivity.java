package com.match_3;
import static com.match_3.Constants.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        cellWidth = screenWidth / 9;
        setContentView(R.layout.activity_main);
        FrameLayout root_layout = findViewById(R.id.root_layout);
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        GameView gameView = new GameView(this);
        drawX = (float) (screenWidth - cellWidth * 9)/2;
        drawY = cellWidth * 4;
        root_layout.addView(gameView);
        gameView.setLayoutParams(params);
    }
}

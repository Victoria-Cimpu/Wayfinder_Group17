package com.example.masterproject;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Wayfinder extends AppCompatActivity {
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wayfinder);
    }

    // Screen Swiping
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 <  x2 && (x2-x1) > THRESHOLD){
                    // Left activity
                    Intent i = new Intent(Wayfinder.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }else if(x1 > x2 && (x1-x2) > THRESHOLD){
                    // Right activity
                    Intent i = new Intent(Wayfinder.this, CallDesk.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                break;
        }
        return false;
    }

    public void onClickImage(View view) {
        TextView gettingReady = findViewById(R.id.nav_main_loading_text);
        gettingReady.setText("Navigating...");

        TextView navDestination = findViewById(R.id.nav_main_destination);
        navDestination.setText("Photocopy Centre");
        ProgressBar loadingBar = findViewById(R.id.nav_main_loading_bar);
        loadingBar.setVisibility(View.INVISIBLE);

        ImageView navIcon = findViewById(R.id.nav_main_icon);
        @DrawableRes int imgDrawable = R.drawable.ic_baseline_navigation_24;
        navIcon.setImageResource(imgDrawable);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navIcon.setRotation(30);
            }
        }, 5000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navIcon.setRotation(60);
            }
        }, 7000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navIcon.setRotation(30);
            }
        }, 10000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navIcon.setRotation(0);
            }
        }, 12000);
    }

    public void backToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
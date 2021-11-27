package com.example.masterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class WayfinderFR extends AppCompatActivity {
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wayfinder_fr);
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
                    Intent i = new Intent(WayfinderFR.this, MainActivityFR.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }else if(x1 > x2 && (x1-x2) > THRESHOLD){
                    // Right activity
                    Intent i = new Intent(WayfinderFR.this, CallDeskFR.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                break;
        }
        return false;
    }
}
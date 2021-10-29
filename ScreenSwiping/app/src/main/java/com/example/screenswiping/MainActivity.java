package com.example.screenswiping;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;


public class MainActivity extends AppCompatActivity {
    // y1 and y2 aren't necessary but are there if we want to use a swipe up and down feature
    float x1, x2, y1, y2;

    // User needs to travel 150 pixels left or right for it to register as a swiping motion
    // This number can be modified after experimentation
    static float THRESHOLD = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Screen swiping method. Make sure to add an android resource directory called anim in res
    // that contains the 4 different animation resource files
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 <  x2){
                    // No Left activity
            }else if(x1 > x2 && (x1-x2) > THRESHOLD){
                    // Right activity
                    Intent i = new Intent(MainActivity.this, Activity2.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    // finish() isn't necessary here, it also works without it
                    finish();
            }
            break;
        }
        return false;
    }
}
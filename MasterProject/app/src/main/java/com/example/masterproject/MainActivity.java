package com.example.masterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;
    Button button_settings;
    Button button_calldesk;
    Button button_wayfinder;
    Button button_faq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_settings = (Button) findViewById(R.id.btn_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        button_calldesk = (Button) findViewById(R.id.btn_callDesk);
        button_calldesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCallDesk();
            }
        });

        button_wayfinder = (Button) findViewById(R.id.btn_wayfinder);
        button_wayfinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openWayfinder();
            }
        });
        button_faq = (Button) findViewById(R.id.btn_question);
        button_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFAQ();
            }
        });
    }



    public void openSettings(){
        Intent i = new Intent(MainActivity.this, Settings.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openCallDesk(){
        Intent i = new Intent(MainActivity.this, CallDesk.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openWayfinder(){
        Intent i = new Intent(MainActivity.this, Wayfinder.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openFAQ() {
        Intent i = new Intent(MainActivity.this, Faq.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

                }else if(x1 > x2 && (x1-x2) > THRESHOLD){
                    // Right activity
                    openWayfinder();
                }
                break;
        }
        return false;
    }
}
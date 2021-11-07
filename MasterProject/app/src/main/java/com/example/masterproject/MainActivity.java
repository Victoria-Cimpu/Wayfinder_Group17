package com.example.masterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_settings;
    Button button_calldesk;

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
}
package com.example.masterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;


import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button_settings;
    Button button_calldesk;
    CheckBox TTS_checkBox;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
         tts variable declaration, we can make this inside a new class and instantiate a
         variable from that class for the other .java files to make code re-usable
         */

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    int result = tts.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

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

    private String speak() {
        String txt = "Welcome to the main menu, click wayfinder to access directional information" +
                ", click F.A.Q to view frequently asked questions, settings to enable or disable" +
                "text-to-speech, call desk to call for additional assistance, and francais to" +
                "change language to french.";
        return txt;
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
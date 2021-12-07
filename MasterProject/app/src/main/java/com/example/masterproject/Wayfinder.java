package com.example.masterproject;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class Wayfinder extends AppCompatActivity {
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;
    private TextToSpeech mTTS;
    private TextView mEditText;
    private Button verbalInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wayfinder);

        verbalInstructions = findViewById(R.id.nav_main_tts);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        verbalInstructions.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        verbalInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    private void speak() {
        String text = "Please Move Forward";
        //mTTS.setPitch(100);
        //mTTS.setSpeechRate(100);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speak2() {
        String text = "Make a Slight Right";
        //mTTS.setPitch(100);
        //mTTS.setSpeechRate(100);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speak3() {
        String text = "Make a Slight Left";
        //mTTS.setPitch(100);
        //mTTS.setSpeechRate(100);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void speak4() {
        String text = "You have arrived";
        //mTTS.setPitch(100);
        //mTTS.setSpeechRate(100);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
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

        speak();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak2();
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
                speak3();
                navIcon.setRotation(30);
            }
        }, 10000);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak4();
                navIcon.setRotation(0);
            }
        }, 12000);
    }

    public void backToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
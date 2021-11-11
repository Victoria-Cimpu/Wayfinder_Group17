package com.example.masterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Context;

import java.util.Locale;


public class Settings extends AppCompatActivity {
    CheckBox TTS_checkBox;
    EditText FontSize;
    Button saveButton;
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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


        TTS_checkBox = (CheckBox) findViewById(R.id.TTS);

        if (TTS_checkBox.isChecked()) {
            tts.speak(speak(), TextToSpeech.QUEUE_FLUSH, null);
        }

        FontSize = (EditText) findViewById(R.id.FontSize);
        saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences("TTS_on", TTS_checkBox.isChecked());
                if (FontSize.getText().toString().matches("\\d+"))
                        savePreferences("FontSize", Integer.parseInt(FontSize.getText().toString()));
            }});

        loadSavedPreferences();
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        boolean TTS_on = sharedPreferences.getBoolean("TTS_on", true);
        int fontSize = sharedPreferences.getInt("FontSize", 24);
        if (TTS_on) {
            TTS_checkBox.setChecked(true);
        } else {
            TTS_checkBox.setChecked(false);
        }

        // Not having the quotation marks below made the app crash for some reason...
        FontSize.setText(fontSize+"");
    }

    private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void savePreferences(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

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
                    Intent i = new Intent(Settings.this, CallDesk.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }else if(x1 > x2 && (x1-x2) > THRESHOLD){
                    // Right activity

                }
                break;
        }
        return false;
    }

    private String speak() {
        String txt = "Welcome to settings (enter more information here)";
        return txt;
    }

}
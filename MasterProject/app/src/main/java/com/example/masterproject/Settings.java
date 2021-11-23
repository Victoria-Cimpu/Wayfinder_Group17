package com.example.masterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Settings extends AppCompatActivity {
    CheckBox TTS_checkBox;
    RadioGroup FontSize;
    Button saveButton;
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TTS_checkBox = (CheckBox) findViewById(R.id.TTS);
        //FontSize = (EditText) findViewById(R.id.FontSize);
        FontSize = (RadioGroup) findViewById(R.id.FontSize);
        saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences("TTS_on", TTS_checkBox.isChecked());
                savePreferences("FontSize", FontSize.indexOfChild(findViewById(FontSize.getCheckedRadioButtonId())));
                setFontSize(FontSize.indexOfChild(findViewById(FontSize.getCheckedRadioButtonId())));
            }});

        loadSavedPreferences();
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        boolean TTS_on = sharedPreferences.getBoolean("TTS_on", true);
        if (TTS_on) {
            TTS_checkBox.setChecked(true);
        } else {
            TTS_checkBox.setChecked(false);
        }

        int fontSizeSel = sharedPreferences.getInt("FontSize",1);
        RadioButton fSChoice = (RadioButton) FontSize.getChildAt(fontSizeSel);
        fSChoice.setChecked(true);
        setFontSize(fontSizeSel);
    }

    private void setFontSize(int fontSizeSel) {
        int themeID = R.style.FontSizeMedium;
        if (fontSizeSel == 0) {
            themeID = R.style.FontSizeSmall;
        }
        else if (fontSizeSel == 2) {
            themeID = R.style.FontSizeLarge;
        }
        setTheme(themeID);

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

}
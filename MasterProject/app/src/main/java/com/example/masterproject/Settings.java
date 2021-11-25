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
import android.widget.TextView;
import android.widget.Toast;


public class Settings extends AppCompatActivity{
    CheckBox TTS_checkBox;
    RadioGroup FontSize;
    Button saveButton;
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;
    int txtSize;


    //public Settings(Callback callback) {
    //    this.callback = callback;
    //}

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

        int fontSizeSel = 1;
        fontSizeSel = sharedPreferences.getInt("FontSize",1);

        RadioButton fSChoice = (RadioButton) FontSize.getChildAt(fontSizeSel);
        fSChoice.setChecked(true);
        setFontSize(fontSizeSel);
    }

    private void setFontSize(int fontSizeSel) {
        TextView enableTTS = (TextView) findViewById(R.id.TTs_text);
        CheckBox TTS = (CheckBox) findViewById(R.id.TTS);
        TextView fontSize = (TextView) findViewById(R.id.FontSize_text);
        RadioButton small = (RadioButton) findViewById(R.id.radioSmall);
        RadioButton medium = (RadioButton) findViewById(R.id.radioMedium);
        RadioButton large = (RadioButton) findViewById(R.id.radioLarge);
        TextView expl = (TextView) findViewById(R.id.explanation);

        Intent intent = new Intent(Settings.this,CallDesk.class);

        //int themeID = R.style.FontSizeMedium;
        if (fontSizeSel == 0) {
            //themeID = R.style.FontSizeSmall;
            enableTTS.setTextSize(14);
            TTS.setTextSize(14);
            fontSize.setTextSize(14);
            small.setTextSize(14);
            medium.setTextSize(14);
            large.setTextSize(14);
            intent.putExtra("SIZE", "14");

            //expl.setTextSize(14);
        } else if (fontSizeSel == 1) {
            enableTTS.setTextSize(24);
            TTS.setTextSize(24);
            fontSize.setTextSize(24);
            small.setTextSize(24);
            medium.setTextSize(24);
            large.setTextSize(24);
            intent.putExtra("SIZE", "24");

            //expl.setTextSize(24);
        }
        else if (fontSizeSel == 2) {
            //themeID = R.style.FontSizeLarge;
            enableTTS.setTextSize(34);
            TTS.setTextSize(34);
            fontSize.setTextSize(34);
            small.setTextSize(34);
            medium.setTextSize(34);
            large.setTextSize(34);
            intent.putExtra("SIZE", "34");

            //expl.setTextSize(34);
        }
        //setTheme(themeID);

        //startActivity(intent);
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
                    Intent i = new Intent(Settings.this, CallDesk.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }else if(x1 > x2 && (x1-x2) > THRESHOLD){
                    // Right activity
                    Intent i = new Intent(Settings.this, Faq.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                break;
        }
        return false;
    }

}
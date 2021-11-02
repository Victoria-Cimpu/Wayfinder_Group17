package com.victoriacimpu.prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Context;


public class MainActivity extends AppCompatActivity {
    CheckBox TTS_checkBox;
    EditText FontSize;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TTS_checkBox = (CheckBox) findViewById(R.id.TTS);
        FontSize = (EditText) findViewById(R.id.FontSize);
        saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences("TTS_on", TTS_checkBox.isChecked());
                if (FontSize.getText().toString().matches("\\d+"))
                    savePreferences("FontSize", Integer.getInteger(FontSize.getText().toString()));
        }});

        loadSavedPreferences();
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(MainActivity.this);
        boolean TTS_on = sharedPreferences.getBoolean("TTS_on", true);
        int fontSize = sharedPreferences.getInt("FontSize", 24);
        if (TTS_on) {
            TTS_checkBox.setChecked(true);
        } else {
            TTS_checkBox.setChecked(false);
        }

        FontSize.setText(fontSize);
    }

    private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void savePreferences(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

}
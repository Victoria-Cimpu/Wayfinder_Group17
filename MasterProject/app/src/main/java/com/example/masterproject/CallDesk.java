package com.example.masterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class CallDesk extends AppCompatActivity {
    private TextToSpeech mTTS;
    private TextView mEditText;
    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;
    private Button mButtonSpeak;
    float x1, x2, y1, y2;
    static float THRESHOLD = 150;


    Button buttonCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_desk);

        Intent intent2 = getIntent();

        buttonCall = (Button) findViewById(R.id.button_help);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:6135625213"));
                startActivity(intent);
            }
        });


        //String strSize = intent2.getStringExtra("SIZE");
        //TextView descrip = (TextView) findViewById(R.id.explanation);
        //descrip.setTextSize(Float.valueOf(strSize));
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
                    Intent i = new Intent(CallDesk.this, Wayfinder.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }else if(x1 > x2 && (x1-x2) > THRESHOLD){
                    // Right activity
                    Intent i = new Intent(CallDesk.this, Faq.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                break;
        }
        return false;
    }


}
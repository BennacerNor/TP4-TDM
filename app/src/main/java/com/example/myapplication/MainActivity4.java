package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainActivity4 extends AppCompatActivity {

    private static final String STATE_NOTE = "note";
    private static final String STATE_TIME = "time";

    ImageView img;
    TextToSpeech t1;
    String note;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView textView1 = findViewById(R.id.tv1);
        TextView textView2 = findViewById(R.id.tv2);
        TextView textView3 = findViewById(R.id.tv3);
        img = findViewById(R.id.speech);

        if (savedInstanceState != null) {
            note = savedInstanceState.getString(STATE_NOTE);
            time = savedInstanceState.getString(STATE_TIME);
        } else {
            Intent intent = getIntent();
            note = intent.getStringExtra("note");
            time = intent.getStringExtra("time");
        }

        textView1.setText(note);
        textView2.setText("BENNACER NOR FARAH");
        textView3.setText(time);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.speak(note, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_NOTE, note);
        outState.putString(STATE_TIME, time);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MonActivity2 extends Activity {
Animation rotateaniation;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon2);

        imageView = findViewById(R.id.image1);
       rotateaniation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MonActivity2.this, MainActivity3.class);
                startActivity(i);
                finish();
            }
        }, 4000);
    }

    private void rotateaniation() {
        rotateaniation= AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(rotateaniation);
    }
}
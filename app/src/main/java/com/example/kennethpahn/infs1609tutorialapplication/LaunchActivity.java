package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LaunchActivity extends AppCompatActivity {
    //variable for splash screen time delay
    private static int launch_time = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
        //open splash screen. sourced from: https://www.youtube.com/watch?v=jXtof6OUtcE
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }
        }, launch_time);
    }
}

package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        TextView introText = findViewById(R.id.textView);
        introText.setText(R.string.intro);

        final Button beginBtn = findViewById(R.id.beginBtn);
        beginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent a = new Intent(IntroActivity.this, login.class);
                startActivity(a);
            }
        });

    }
}

package com.example.kennethpahn.infs1609tutorialapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class tfQuizDisp extends AppCompatActivity {
    // get the UI stuff
    private TextView questionTxt;
    private Button trueBtn;
    private Button falseBtn;
    private TextView resultTxt;
    private int moduleNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tf_quiz_disp);
        // link UI
        questionTxt = (TextView) findViewById(R.id.questionTxt);
        trueBtn = (Button) findViewById(R.id.trueBtn);
        falseBtn = (Button) findViewById(R.id.falseBtn);
        resultTxt = (TextView) findViewById(R.id.resultTxt);
        // get intent details
        Bundle infoPassed = getIntent().getExtras();
        moduleNo = infoPassed.getInt("moduleNo");
        // now load the correct questions

    }
}

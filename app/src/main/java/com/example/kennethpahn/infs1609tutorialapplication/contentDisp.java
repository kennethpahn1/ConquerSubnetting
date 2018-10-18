package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class contentDisp extends AppCompatActivity {
    private Button nextBtn;
    private Button prevBtn;
    private TextView moduleNameTxt;
    private TextView moduleContentTxt;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_disp);
        // get intent details
        Bundle infoPassed = getIntent().getExtras();
        String moduleName = infoPassed.getString("moduleName");
        final String[] moduleContents = infoPassed.getStringArray("moduleContents");
        // load UI elements
        nextBtn = (Button) findViewById(R.id.nextBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);
        moduleNameTxt = (TextView) findViewById(R.id.moduleNameTxt);
        moduleContentTxt = (TextView) findViewById(R.id.moduleContentTxt);
        // load data
        moduleNameTxt.setText(moduleName);
        moduleContentTxt.setText(moduleContents[i]);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moduleContents[i + 1].equals(null)){

                } else{
                    i++;
                    moduleContentTxt.setText(moduleContents[i]);
                }

            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moduleContents[i - 1].equals(null)){

                } else{
                    i--;
                    moduleContentTxt.setText(moduleContents[i]);
                }

            }
        });
    }
}

package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class moduleDetail extends AppCompatActivity {
    // get the UI elements set
    private TextView moduleNameTxt;
    private TextView moduleDescTxt;
    private Button startBtn;
    public String[] moduleContents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_detail);
        // get intent and information
        Bundle infoPassed = getIntent().getExtras();
        int moduleNo = infoPassed.getInt("moduleNo");
        String moduleName = infoPassed.getString("moduleName");
        String moduleDesc = infoPassed.getString("moduleDesc");
        moduleContents = infoPassed.getStringArray("moduleContents");
        // load into the UI
        moduleNameTxt = (TextView) findViewById(R.id.moduleNameTxt);
        moduleNameTxt.setText(moduleNo + ": " + moduleName);
        moduleDescTxt = (TextView) findViewById(R.id.moduleDescTxt);
        moduleDescTxt.setText(moduleDesc);
        // link button to the UI
        startBtn = (Button) findViewById(R.id.startBtn);
        // allow it to do shit
        startBtn.setOnClickListener(new View.OnClickListener(){
              @Override
               public void onClick(View v) {
                  Intent a = new Intent(moduleDetail.this, contentDisp.class);
                  a.putExtra("moduleName", moduleNameTxt.getText().toString());
                  a.putExtra("moduleContents", moduleContents);
                  startActivity(a);
               }
            }
        );
    }
}

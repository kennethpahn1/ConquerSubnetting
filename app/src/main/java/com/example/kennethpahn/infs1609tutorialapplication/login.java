package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class login extends AppCompatActivity {
    private Button loginBtn;
    private EditText zIDinput;
    private EditText zPassinput;
    private Button registerBtn;
    // stolen from https://mobilesiri.com/json-parsing-in-android-using-android-studio/
    // this is used to parse the html stuff into text the program can read.
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    // usual setting up buttons and stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // stolen from https://stackoverflow.com/questions/2730855/prevent-screen-rotation-on-android
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        zIDinput = (EditText) findViewById(R.id.zIDinput);
        zPassinput = (EditText) findViewById(R.id.zPassinput);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        //Bundle infoPassed = getIntent().getExtras();
        //zIDinput.setText(infoPassed.getString("zid"));
        View.OnClickListener loginListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    // uses our custom php api to check if the user actually exists
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String url = "http://feewka.kennethpahn.info/login.php?zid=" +
                            zIDinput.getText().toString() + "&zpass=" + zPassinput.getText().toString();
                    URL url2 = new URL(url);
                    InputStream input = (url2).openStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                    String name = readAll(rd);
                    // get their name
                    System.out.println("Name grabbed: " + name);
                    // if their name isn't blank, then it's basically a legit user
                    if (name != ""){
                        Toast.makeText(getApplicationContext(), "Login successful!",
                                Toast.LENGTH_LONG).show();
                        Intent a = new Intent(login.this, MainActivity.class);
                        a.putExtra("zid", Integer.valueOf(zIDinput.getText().toString()));
                        a.putExtra("name", name);
                        startActivity(a);
                    } else{
                        Toast.makeText(getApplicationContext(), "Login failed!",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        loginBtn.setOnClickListener(loginListener);
        // move to the register screen
        View.OnClickListener registerListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent a = new Intent(login.this, register.class);
                a.putExtra("zid", zIDinput.getText().toString());
                a.putExtra("zpass", zPassinput.getText().toString());
                startActivity(a);
            }
        };
        registerBtn.setOnClickListener(registerListener);
    }
}

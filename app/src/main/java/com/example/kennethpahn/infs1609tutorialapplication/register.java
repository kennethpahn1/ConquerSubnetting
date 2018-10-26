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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class register extends AppCompatActivity {
    private Button registerBtn;
    private EditText zIDinput;
    private EditText zPassinput;
    private EditText nameInput;
    // this has been referenced before...
    // parse.
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        zIDinput = (EditText) findViewById(R.id.zIDinput);
        zPassinput = (EditText) findViewById(R.id.zPassinput);
        nameInput = (EditText) findViewById(R.id.nameInput);
        Bundle infoPassed = getIntent().getExtras();
        zIDinput.setText(infoPassed.getString("zid"));
        zPassinput.setText(infoPassed.getString("zpass"));
        View.OnClickListener loginListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    //register test
                    // sourced from https://developer.android.com/reference/android/os/StrictMode
                    // Used to allow http to run on main thread for json.
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    // uses php to register users.
                    String url = "http://feewka.kennethpahn.info/register.php?zid=" + zIDinput.getText().toString() + "&zpass=" + zPassinput.getText().toString() + "&name=" + nameInput.getText().toString();
                    URL url2 = new URL(url);
                    InputStream input = (url2).openStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                    String status = readAll(rd);
                    // intent to show successful registration.
                    System.out.println("Status: " + status);
                    if (status != ""){
                        Toast.makeText(getApplicationContext(), "Registration successful!",
                                Toast.LENGTH_LONG).show();
                        Intent a = new Intent(register.this, login.class);
                        a.putExtra("zid", zIDinput.getText().toString());
                        startActivity(a);
                    } else {
                        Toast.makeText(getApplicationContext(), "User already exists or internet connection failed.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        registerBtn.setOnClickListener(loginListener);
    }
}

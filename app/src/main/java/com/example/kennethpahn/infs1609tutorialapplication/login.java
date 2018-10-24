package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        setContentView(R.layout.activity_login2);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        zIDinput = (EditText) findViewById(R.id.zIDinput);
        zPassinput = (EditText) findViewById(R.id.zPassinput);
        View.OnClickListener loginListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String url = "http://feewka.kennethpahn.info/login.php?zid=" + zIDinput.getText().toString() + "&pass=" + zPassinput.getText().toString();
                    URL url2 = new URL(url);
                    InputStream input = (url2).openStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                    String jsonText = readAll(rd);
                    JSONObject json = new JSONObject(jsonText);
                    // load into db
                    if (json.getString("zpass") == zPassinput.getText().toString() && json.getString("zid") == zIDinput.getText().toString()){
                        Intent a = new Intent(login.this, MainActivity.class);
                        a.putExtra("name", json.getString("name"));
                        startActivity(a);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };
        loginBtn.setOnClickListener(loginListener);
    }
}

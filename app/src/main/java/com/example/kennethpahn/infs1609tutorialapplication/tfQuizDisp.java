package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class tfQuizDisp extends AppCompatActivity {
    // get the UI stuff
    private TextView questionTxt;
    private Button trueBtn;
    private Button falseBtn;
    private TextView resultTxt;
    // this is just to hold information for the rest of the program
    private int moduleNo;
    private int counter;
    private boolean add = true; // so this prevents retried questions from being taken as correct.
    private boolean next = false;
    // this is to hold the marks
    private int mark;
    private int total;
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
        final int zid = infoPassed.getInt("zid");
        moduleNo = infoPassed.getInt("moduleNo");
        // now load the correct questions
        final tfQuizContent[] tfQuiz = populateTfQuiz(moduleNo);
        // initialise with the first question
        questionTxt.setText(tfQuiz[counter].getQuestion());
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkAnswer(1, tfQuiz[counter].getSolution(), zid) == 1) {
                        questionTxt.setText(tfQuiz[counter].getQuestion());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    error();
                }
            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkAnswer(0, tfQuiz[counter].getSolution(), zid) == 1) {
                        questionTxt.setText(tfQuiz[counter].getQuestion());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    error();
                }
            }
        });
    }
    // stolen from https://mobilesiri.com/json-parsing-in-android-using-android-studio/
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    private tfQuizContent[] populateTfQuiz(int moduleNo) {
        tfQuizContent[] tfQuiz = new tfQuizContent[10];
        if (moduleNo == 0) {
            tfQuiz[0] = new tfQuizContent(1, 1, 1, "True/False? 1", 1);
            tfQuiz[1] = new tfQuizContent(1, 1, 2, "True/False? 0", 0);
            tfQuiz[2] = new tfQuizContent(1, 1, 3, "True/False? 0", 0);
            tfQuiz[3] = new tfQuizContent(1, 1, 4, "True/False? 1", 1);
            tfQuiz[4] = new tfQuizContent(1, 1, 5, "True/False? 0", 0);
            tfQuiz[5] = new tfQuizContent(1, 1, 6, "True/False? 1", 1);
            tfQuiz[6] = new tfQuizContent(1, 1, 7, "True/False? 0", 0);
            tfQuiz[7] = new tfQuizContent(1, 1, 8, "True/False? 0", 0);
            tfQuiz[8] = new tfQuizContent(1, 1, 9, "True/False? 1", 1);
            tfQuiz[9] = new tfQuizContent(1, 1, 10, "True/False? 1", 1);
        }
        return tfQuiz;
    }
    // this checks the answer for us
    private int checkAnswer(int user, int answer, int zid) throws IOException {
        if (user == answer) {
            if (add == true) {
                //http://feewka.kennethpahn.info/recordtf.php?zid=5114063&module_id=0&module_question=0&answer=1
                // Stolen from https://developer.android.com/reference/android/os/StrictMode
                // Used to allow http to run on main thread for json.
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                // uses php to register users.
                String url = "http://feewka.kennethpahn.info/recordtf.php?zid=" + zid + "&module_id=" + moduleNo + "&module_question=" + counter + "&answer=1";
                URL url2 = new URL(url);
                InputStream input = (url2).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                String status = readAll(rd);
                // intent to show successful registration.
                System.out.println("Status: " + status);
                if (status != ""){
                    Toast.makeText(getApplicationContext(), "Answer saved.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Answer save failed.",
                            Toast.LENGTH_LONG).show();
                }
                mark++;
                total++;
            }
            counter++;
            add = true;
            resultTxt.setText("Result: Correct!\nMark: " + mark + "/" + total);
            return 1;
        } else {
            if (add == true) {
                //http://feewka.kennethpahn.info/recordtf.php?zid=5114063&module_id=0&module_question=0&answer=1
                // Stolen from https://developer.android.com/reference/android/os/StrictMode
                // Used to allow http to run on main thread for json.
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                // uses php to register users.
                String url = "http://feewka.kennethpahn.info/recordtf.php?zid=" + zid + "&module_id=" + moduleNo + "&module_question=" + counter + "&answer=1";
                URL url2 = new URL(url);
                InputStream input = (url2).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                String status = readAll(rd);
                // intent to show successful registration.
                System.out.println("Status: " + status);
                if (status != ""){
                    Toast.makeText(getApplicationContext(), "Answer saved.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Answer save failed.",
                            Toast.LENGTH_LONG).show();
                }
                total++;
            }
            add = false;
            resultTxt.setText("Result: Incorrect. Try again!\nMark: " + mark + "/" + total);
            return 0;
        }
    }
    // this is to handle moving onto the next part of the module.
    private void error() {
        if (next == false) {
            resultTxt.setText("Mark: " + mark + "/" + total + "\nClick Next when you are ready to learn more.");
            trueBtn.setVisibility(View.INVISIBLE);
            falseBtn.setText("Next");
            next = true;
        } else if (next == true) {
            Intent a = new Intent(tfQuizDisp.this, multimediaContentDisp.class);
            a.putExtra("moduleNo", moduleNo);
            startActivity(a);
        }
    }

}

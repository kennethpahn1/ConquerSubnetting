package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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

public class mcqQuizDisp extends AppCompatActivity {
    private TextView questionTxt;
    private RadioButton aOption;
    private RadioButton bOption;
    private RadioButton cOption;
    private RadioButton dOption;
    private Button nextBtn;
    private TextView resultTxt;
    private int solution;
    private int moduleNo;
    private int counter = 0;
    private boolean add = true; // so this prevents retried questions from being taken as correct.
    private boolean next = false;
    int zid;
    // this is to hold the marks
    private int mark;
    private int total;
    public void onBackPressed() {
        // stolen from https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
        Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
        a.putExtra("zid", zid);
        startActivity(a);
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
    private mcqQuizContent[] populateMcqQuiz(int moduleNo){
        mcqQuizContent[] mcqQuiz = new mcqQuizContent[5];
        if (moduleNo == 0){
            mcqQuiz[0] = new mcqQuizContent(1, 1, 2, "Which of the following correctly displays an IPv4 address?", 1, "192.168.001.001", "192.168.15.1", "000.112.42.2", "1000.9033.1288.1199");
            mcqQuiz[1] = new mcqQuizContent(1, 1, 2, "IPv4 addresses provide the following information:", 3, "Physical address (MAC), Host address", "Serial number, Manufacturer number", "Network address, Physical address", "Network address, Host address");
            mcqQuiz[2] = new mcqQuizContent(1, 1, 2, "13.121.44.1 belongs to which network class?", 0, "A", "B", "C", "D");
            mcqQuiz[3] = new mcqQuizContent(1, 1, 2, "IPv4 addresses are ___-bit addresses.", 1, "16", "32", "64", "128");
            mcqQuiz[4] = new mcqQuizContent(1, 1, 2, "195.113.38.3 belongs to which network class?", 2, "A", "B", "C", "D");
        }
        return mcqQuiz;
    }
    // handle checking answers for us
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
                String url = "http://feewka.kennethpahn.info/recordmcq.php?zid=" + zid + "&module_id=" + moduleNo + "&module_question=" + counter + "&answer=" + user;
                URL url2 = new URL(url);
                InputStream input = (url2).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                String status = readAll(rd);
                // intent to show successful registration.
                System.out.println("Status: " + status);
                if (status != ""){
                    Toast.makeText(getApplicationContext(), "Answer saved.",
                            Toast.LENGTH_LONG).show();
                    saveStatus(moduleNo, zid, counter);
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
                String url = "http://feewka.kennethpahn.info/recordmcq.php?zid=" + zid + "&module_id=" + moduleNo + "&module_question=" + counter + "&answer=" + user;
                URL url2 = new URL(url);
                InputStream input = (url2).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                String status = readAll(rd);
                // intent to show successful registration.
                System.out.println("Status: " + status);
                if (status != ""){
                    Toast.makeText(getApplicationContext(), "Answer saved.",
                            Toast.LENGTH_LONG).show();
                    saveStatus(moduleNo, zid, counter);
                } else {
                    Toast.makeText(getApplicationContext(), "Answer save failed.",
                            Toast.LENGTH_LONG).show();
                }
                total++;
            }
            saveStatus(moduleNo, zid, counter);
            add = false;
            resultTxt.setText("Result: Incorrect. Try again!\nMark: " + mark + "/" + total);
            return 0;
        }
    }
    // handles the error for us (as in, moves it back to Main Activity).
    private void error(){
        if (next == false){
            resultTxt.setText("Mark: " + mark + "/" + total + "\nClick Next when you are ready to learn more.");
            next = true;
        } else if (next == true){
            Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
            a.putExtra("moduleNo", moduleNo);
            // stolen from https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
            a.putExtra("zid", zid);
            startActivity(a);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_quiz_disp);
        questionTxt = (TextView) findViewById(R.id.questionTxt);
        aOption = (RadioButton) findViewById(R.id.aOption);
        bOption = (RadioButton) findViewById(R.id.bOption);
        cOption = (RadioButton) findViewById(R.id.cOption);
        dOption = (RadioButton) findViewById(R.id.dOption);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        resultTxt = (TextView) findViewById(R.id.resultTxt);
        // figure out what module they're running
        // get intent details
        Bundle infoPassed = getIntent().getExtras();
        moduleNo = infoPassed.getInt("moduleNo");
        counter = infoPassed.getInt("order");
        final mcqQuizContent[] mcqQuiz = populateMcqQuiz(moduleNo);
        zid = infoPassed.getInt("zid");
        // resume
        try {
            mark = getPastAnswers(moduleNo, zid, mcqQuiz);
            if (mark > 0){
                total = counter;
                resultTxt.setText("Mark: " + mark + "/" + total);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // populate details based on that.
        questionTxt.setText(mcqQuiz[counter].getQuestion());
        String[] answers = mcqQuiz[counter].getAnswers();
        solution = mcqQuiz[counter].getSolution();
        aOption.setText(answers[0]);
        bOption.setText(answers[1]);
        cOption.setText(answers[2]);
        dOption.setText(answers[3]);
        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (aOption.isChecked() == true){
                    try {
                        if (checkAnswer(0, mcqQuiz[counter].getSolution(), zid) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            aOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
                if (bOption.isChecked() == true){
                    try {
                        if (checkAnswer(1, mcqQuiz[counter].getSolution(), zid) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            bOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
                if (cOption.isChecked() == true){
                    try {
                        if (checkAnswer(2, mcqQuiz[counter].getSolution(), zid) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            cOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
                if (dOption.isChecked() == true) {
                    try {
                        if (checkAnswer(3, mcqQuiz[counter].getSolution(), zid) == 1) {
                            // load next questions and answers
                            questionTxt.setText(mcqQuiz[counter].getQuestion());
                            String[] answers = mcqQuiz[counter].getAnswers();
                            aOption.setText(answers[0]);
                            bOption.setText(answers[1]);
                            cOption.setText(answers[2]);
                            dOption.setText(answers[3]);
                            dOption.setChecked(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        error();
                    }
                }
            }
        });
    }
    // save status every time
    private void saveStatus(int moduleNo, int zid, int order) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // uses php to register users.
        String url = "http://feewka.kennethpahn.info/save.php?zid=" + zid + "&module_id=" + moduleNo + "&section=" + 3 + "&order=" + order;
        URL url2 = new URL(url);
        InputStream input = (url2).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        String status = readAll(rd);
    }
    // get past answers
    private int getPastAnswers(int moduleNo, int zid, mcqQuizContent[] mcqQuiz) throws IOException {
        int[] pastanswers = new int[counter];
        for (int i = 0; i < counter; i++){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // uses php to register users.
            String url = "http://feewka.kennethpahn.info/getmcq.php?zid=" + zid + "&module=" + moduleNo + "&q=" + i;
            URL url2 = new URL(url);
            InputStream input = (url2).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            String status = readAll(rd);
            pastanswers[i] = Integer.valueOf(status);
        }
        int score = getScore(pastanswers, mcqQuiz);
        return score;
    }
    // check answers and convert to score
    private int getScore(int[] pastanswers, mcqQuizContent[] mcqQuiz){
        int score = 0;
        for (int i = 0; i < counter; i++){
            if (mcqQuiz[i].getSolution() == pastanswers[i]){
                score++;
            }
        }
        return score;
    }
}

package com.example.kennethpahn.infs1609tutorialapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class marksCheck extends AppCompatActivity {
    private Spinner spinner2;
    private TextView marksTxt;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_check);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        marksTxt = (TextView) findViewById(R.id.marksTxt);
        Bundle infoPassed = getIntent().getExtras();
        final int zid = infoPassed.getInt("zid");
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try{
                    printScore(zid, position);
                } catch (Exception e){
                    marksTxt.setText("You have not completed this module.");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                marksTxt.setText("");
            }

        });
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
    private mcqQuizContent[] populateMcqQuiz(int moduleNo){
        mcqQuizContent[] mcqQuiz = new mcqQuizContent[10];
        if (moduleNo == 0){
            mcqQuiz[0] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 3", 3, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[1] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[2] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 0", 0, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[3] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 2", 2, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[4] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[5] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 3", 3, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[6] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[7] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 0", 0, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[8] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 2", 2, "Good", "Bad", "Very Bad", "Extremely Bad");
            mcqQuiz[9] = new mcqQuizContent(1, 1, 2, "Hello, how are you today? 1", 1, "Good", "Bad", "Very Bad", "Extremely Bad");
        }
        return mcqQuiz;
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
    // get past answers
    private int[] getPastAnswers(int moduleNo, int zid, tfQuizContent[] tfQuiz) throws IOException {
        counter = 0;
        int[] pastanswers = new int[10];
        for (int i = 0; i < 10; i++){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // uses php to register users.
            String url = "http://feewka.kennethpahn.info/gettf.php?zid=" + zid + "&module=" + moduleNo + "&q=" + i;
            URL url2 = new URL(url);
            InputStream input = (url2).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            String status = readAll(rd);
            try{
                pastanswers[i] = Integer.valueOf(status);
                System.out.println(url);
                counter++;
            } catch (Exception e){

            }

        }
        return pastanswers;
    }
    private int[] getPastAnswers(int moduleNo, int zid, mcqQuizContent[] mcqQuiz) throws IOException {
        counter = 0;
        int[] pastanswers = new int[10];
        for (int i = 0; i < 5; i++){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // uses php to register users.
            String url = "http://feewka.kennethpahn.info/getmcq.php?zid=" + zid + "&module=" + moduleNo + "&q=" + i;
            URL url2 = new URL(url);
            InputStream input = (url2).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            String status = readAll(rd);
            try{
                pastanswers[i] = Integer.valueOf(status);
                System.out.println(url);
                counter++;
            } catch (Exception e){

            }

        }
        return pastanswers;
    }
    private void printScore(int zid, int moduleNo){
        // t/f quiz bs
        int[] pastanswerstf = new int[10];
        tfQuizContent[] tfQuiz = populateTfQuiz(moduleNo);
        try {
            pastanswerstf = getPastAnswers(moduleNo, zid, tfQuiz);
            System.out.println("This did try" + pastanswerstf[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int correcttf = 0;
        marksTxt.setText("True/False Results\n\n");
        marksTxt.append("Q ANS SLN\n");
        for (int i = 0; i < counter; i++){
            String user = "";
            String sys = "";
            if (pastanswerstf[i] == 1){
                user = "T";
            } else {
                user = "F";
            }
            if (tfQuiz[i].getSolution() == 1){
                sys = "T";
            } else {
                sys = "F";
            }
            marksTxt.append(i + "    " + user + "    " + sys + "\n");
            if (user == sys){
                correcttf++;
            }
        }
        String gradetf = "FL";
        if (correcttf / 10 >= 0.5) {
            gradetf = "PS";
        }
        if (correcttf / 10 >= 0.65) {
            gradetf = "CR";
        }
        if (correcttf / 10 >= 0.75) {
            gradetf = "DN";
        }
        if (correcttf / 10 >= 0.85) {
            gradetf = "HD";
        }
        marksTxt.append("\nT/F Score: " + correcttf + "/10 " + gradetf);

        // mcq quiz bs
        int[] pastanswersmcq = new int[5];
        mcqQuizContent[] mcqQuiz = populateMcqQuiz(moduleNo);
        try {
            pastanswersmcq = getPastAnswers(moduleNo, zid, mcqQuiz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int correctmcq = 0;
        marksTxt.append("\n\nMCQ Results\n\n");
        marksTxt.append("Q ANS SLN\n");
        for (int i = 0; i < counter; i++){
            String user = "";
            String sys = "";
            if (pastanswersmcq[i] == 0){
                user = "A";
            } else if (pastanswersmcq[i] == 1) {
                user = "B";
            } else if (pastanswersmcq[i] == 2){
                user = "C";
            } else if (pastanswersmcq[i] == 3){
                user = "D";
            }
            if (mcqQuiz[i].getSolution() == 0){
                sys = "A";
            } else if (mcqQuiz[i].getSolution() == 1) {
                sys = "B";
            } else if (mcqQuiz[i].getSolution() == 2){
                sys = "C";
            } else if (mcqQuiz[i].getSolution() == 3){
                sys = "D";
            }
            marksTxt.append(i + "    " + user + "    " + sys + "\n");
            if (user == sys){
                correctmcq++;
            }
        }
        String grademcq = "FL";
        if (correctmcq / 5 >= 0.5) {
            grademcq = "PS";
        }
        if (correctmcq / 5 >= 0.65) {
            grademcq = "CR";
        }
        if (correctmcq / 5 >= 0.75) {
            grademcq = "DN";
        }
        if (correctmcq / 5 >= 0.85) {
            grademcq = "HD";
        }
        marksTxt.append("\nMCQ Score: " + correctmcq + "/5 " + grademcq);
    }
}

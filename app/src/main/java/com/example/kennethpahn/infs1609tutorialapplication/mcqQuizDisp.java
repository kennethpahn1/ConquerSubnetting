package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
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
    // go back to the main activity menu screen
    public void onBackPressed() {
        // stolen from https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
        Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
        a.putExtra("zid", zid);
        startActivity(a);
    }
    // stolen from https://mobilesiri.com/json-parsing-in-android-using-android-studio/
    // use for parsing.
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    // preload everything and populate into arrays. make it easier for later ok?
    private mcqQuizContent[] populateMcqQuiz(int moduleNo){
        mcqQuizContent[] mcqQuiz = new mcqQuizContent[5];
        if (moduleNo == 0){
            mcqQuiz[0] = new mcqQuizContent(1, 1, 2, "Which of the following correctly displays an IPv4 address?", 1, "192.168.001.001", "192.168.15.1", "000.112.42.2", "1000.9033.1288.1199");
            mcqQuiz[1] = new mcqQuizContent(1, 1, 2, "IPv4 addresses provide the following information:", 3, "Physical address (MAC), Host address", "Serial number, Manufacturer number", "Network address, Physical address", "Network address, Host address");
            mcqQuiz[2] = new mcqQuizContent(1, 1, 2, "13.121.44.1 belongs to which network class?", 0, "A", "B", "C", "D");
            mcqQuiz[3] = new mcqQuizContent(1, 1, 2, "IPv4 addresses are ___-bit addresses.", 1, "16", "32", "64", "128");
            mcqQuiz[4] = new mcqQuizContent(1, 1, 2, "195.113.38.3 belongs to which network class?", 2, "A", "B", "C", "D");
        }else if(moduleNo == 1){
            mcqQuiz[0] = new mcqQuizContent(1, 2, 2, "Which of the following is a reason why subnetting is so widely used?", 1, "IPv4 addresses are limited.", "Reduce network congestion and packet collision.", "Because it’s fun!", "Users want to use their own networks.");
            mcqQuiz[1] = new mcqQuizContent(1, 2, 2, "Class A Networks have a natural mask of:", 0, "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.224");
            mcqQuiz[2] = new mcqQuizContent(1, 2, 2, "Class B Networks have a natural mask of:", 1, "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.224");
            mcqQuiz[3] = new mcqQuizContent(1, 2, 2, "Class C Networks have a natural mask of:", 2, "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.224");
            mcqQuiz[4] = new mcqQuizContent(1, 2, 2, "Given the following information, identify the network and host addresses.\n" +
                    "Address: 13.211.93.13\n" +
                    "Subnet Mask: 255.0.0.0\n", 0, "Network: 13,  Host: 211.93.13", "Network: 13.211,  Host: 93.13", "Network: 13.211.93,  Host: 13", "Host: 13.211.93.13");
        }else if(moduleNo == 2){
            mcqQuiz[0] = new mcqQuizContent(1, 3, 2, "Given the following information, identify the network and host addresses.\n" +
                    "Address: 192.168.0.1\n" +
                    "Subnet Mask: 255.255.255.0\n", 2, "Network: 192,  Host: 168.0.1", "Network: 192.168,  Host: 0.1", "Network: 192.168.0,  Host: 1", "Host: 192.168.0.1");
            mcqQuiz[1] = new mcqQuizContent(1, 3, 2, "Given the following information, identify the subnetwork address.\n" +
                    "Address: 172.16.17.30\n" +
                    "Subnet Mask: 255.255.240.0\n", 1, "172.16.17.0", "172.16.16.0", "172.16.0.0", "172.0.0.0");
            mcqQuiz[2] = new mcqQuizContent(1, 3, 2, "Given the following information, identify the subnetwork address.\n" +
                    "Address: 172.16.28.15\n" +
                    "Subnet Mask: 255.255.240.0\n", 2, "172.0.0.0", "172.16.28.0", "172.16.16.0", "172.16.28.0");
            mcqQuiz[3] = new mcqQuizContent(1, 3, 2, "A network needs to be further divided into 5 subnetworks. How many bits should one borrow from the host address?", 2, "1", "2", "3", "4");
            mcqQuiz[4] = new mcqQuizContent(1, 3, 2, "A network administrator has decided to create 16 subnetworks by borrowing bits from the host address. How many usable hosts are in each subnetwork?", 1, "8", "14", "16", "32");
        }else if(moduleNo == 3){
            mcqQuiz[0] = new mcqQuizContent(1, 1, 2, "Which of the following is an IPv6 address?", 0, "2001:0db8:0000:0000:0000:ff00:0042:8329", "192.168.0.1", "12090934894789372890785974320984", "abc974398r5jhcc4ur3u4c09439393nnjoijdf9");
            mcqQuiz[1] = new mcqQuizContent(1, 1, 2, "IPv6 addresses are ___-bit addresses.", 2, "32", "64", "128", "256");
            mcqQuiz[2] = new mcqQuizContent(1, 1, 2, "It is ______ that IPv6 will eventually replace IPv4.", 1, "Impossible", "Inevitable", "Unlikely", "Not anticipated");
            mcqQuiz[3] = new mcqQuizContent(1, 1, 2, "IPv6 provides ______ possible addresses.", 3, "1 billion", "1 trillion", "100 trillion", "340 undecillion");
            mcqQuiz[4] = new mcqQuizContent(1, 1, 2, "IPv4 relies on ______ to provide enough public IP addresses", 0, "NAT", "DHCP", "ARP", "IPv6");

        }


        return mcqQuiz;
    }
    // handle checking answers for us
    // checks, and saves. allows redo, but redone answers don't save.
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
        zid = infoPassed.getInt("zid");
        counter = infoPassed.getInt("order");
        final mcqQuizContent[] mcqQuiz = populateMcqQuiz(moduleNo);
        System.out.println("Counter: " + counter);
        if (counter >= 4){
            // stolen from https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
            // stolen from https://stackoverflow.com/questions/5447092/get-context-inside-onclickdialoginterface-v-int-buttonid#5447125
            // dialog fix stolen from https://stackoverflow.com/questions/27965662/how-can-i-change-default-dialog-button-text-color-in-android-5
            AlertDialog.Builder builder = new AlertDialog.Builder(mcqQuizDisp.this, R.style.Theme_AppCompat_Light_Dialog_Alert);
            builder.setTitle("Module Already Completed");
            builder.setMessage("Would you prefer to reattempt this module?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                .permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        // uses php to register users.
                        String url = "http://feewka.kennethpahn.info/clear.php?zid=" + zid + "&module_id=" + moduleNo;
                        URL url2 = new URL(url);
                        InputStream input = (url2).openStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                        String status = readAll(rd);
                        System.out.println(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                    Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
                    a.putExtra("zid", zid);
                    startActivity(a);
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    Intent a = new Intent(mcqQuizDisp.this, MainActivity.class);
                    a.putExtra("zid", zid);
                    startActivity(a);
                }
            });

            AlertDialog alert = builder.create();

            alert.show();


        } else{
            // resume from last picked up
            try {
                mark = getPastAnswers(moduleNo, zid, mcqQuiz);
                if (mark > 0){
                    total = counter;
                    resultTxt.setText("Mark: " + mark + "/" + total);
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
        }
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
    // save status every time using custom API and then allows it to be loaded later.
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
    // get past answers to calculate the marks gained until reloaded point in time.
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
    // check answers and convert to score for above function.
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

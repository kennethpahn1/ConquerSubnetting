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
    private int counter = 0;
    private boolean add = true; // so this prevents retried questions from being taken as correct.
    private boolean next = false;
    // this is to hold the marks
    private int mark;
    private int total;
    int zid = 0;
    public void onBackPressed() {
        // stolen from https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
        Intent a = new Intent(tfQuizDisp.this, MainActivity.class);
        a.putExtra("zid", zid);
        startActivity(a);
    }
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
        zid = infoPassed.getInt("zid");
        moduleNo = infoPassed.getInt("moduleNo");
        counter = infoPassed.getInt("order");
        // now load the correct questions
        final tfQuizContent[] tfQuiz = populateTfQuiz(moduleNo);
        // now to load any past answers
        try {
            mark = getPastAnswers(moduleNo, zid, tfQuiz);
            if (mark > 0){
                total = counter;
                resultTxt.setText("Mark: " + mark + "/" + total);
            }
            //total = counter + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            tfQuiz[0] = new tfQuizContent(1, 1, 1, "An IPv4 address has both a network and host portion.", 1);
            tfQuiz[1] = new tfQuizContent(1, 1, 2, "IP addressing enables DHCP, NAT and ARP features.", 1);
            tfQuiz[2] = new tfQuizContent(1, 1, 3, "IP addressing is based on the physical address of a device.", 0);
            tfQuiz[3] = new tfQuizContent(1, 1, 4, "All hosts in the same broadcast domain can have different network addresses.", 0);
            tfQuiz[4] = new tfQuizContent(1, 1, 5, "IPv4 addresses are 32-bit addresses.", 1);
            tfQuiz[5] = new tfQuizContent(1, 1, 6, "A IPv4 address uniquely identifies a device on a network.", 1);
            tfQuiz[6] = new tfQuizContent(1, 1, 7, "IP addresses must be configured manually.", 0);
            tfQuiz[7] = new tfQuizContent(1, 1, 8, "IP addresses allow for information flow control.", 1);
            tfQuiz[8] = new tfQuizContent(1, 1, 9, "Networks are typically classified as Class A,B,C, D or E networks.", 0);
            tfQuiz[9] = new tfQuizContent(1, 1, 10, "Physical addresses refer to a device’s MAC address.", 1);
        } else if (moduleNo == 1){
            tfQuiz[0] = new tfQuizContent(1, 2, 1, "You can immediately differentiate the network and host portion of an IP address, without knowing its subnet mask.", 0);
            tfQuiz[1] = new tfQuizContent(1, 2, 2, "Subnet masks are used to differentiate the network and host portion of IP addresses.", 1);
            tfQuiz[2] = new tfQuizContent(1, 2, 3, "Subnet masks are comprised of a string of 1s and 0s, which can be derived by converting the mask into binary form.", 1);
            tfQuiz[3] = new tfQuizContent(1, 2, 4, "The 0s in a subnet mask represent the network portion of the address, while the 1s represent hosts.", 0);
            tfQuiz[4] = new tfQuizContent(1, 2, 5, "It may be easier to conceptualize subnet masks by first converting them into binary.", 1);
            tfQuiz[5] = new tfQuizContent(1, 2, 6, "Different network classes (i.e. A,B,C) have different natural masks.", 1);
            tfQuiz[6] = new tfQuizContent(1, 2, 7, "The subnet mask 255.255.255.0 can be represented as 11111111.11111111.11111111.00000000 in binary.", 1);
            tfQuiz[7] = new tfQuizContent(1, 2, 8, "Address: 192.168.15.1\t \n" +
                    "Subnet mask: 255.255.255.0  \n" +
                    "The network portion of the address above can be represented by “192.168”.\n", 0);
            tfQuiz[8] = new tfQuizContent(1, 2, 9, "The subnet mask 255.255.255.224 can be represented as 11111111.11111111.11111111.11100000 in binary.", 1);
            tfQuiz[9] = new tfQuizContent(1, 2, 10, "Class A: 255.255.0.0\n" +
                    "Class B: 255.255.255.0\n" +
                    "Class C: 255.255.255.224\n" +
                    "The above subnet masks denote the natural masks for network classes A, B and C.\n", 0);
        }else if (moduleNo == 2){
            tfQuiz[0] = new tfQuizContent(1, 3, 1, "Subnetting is widely used due to the impracticality of using a single network", 1);
            tfQuiz[1] = new tfQuizContent(1, 3, 2, "Subnetting is particularly useful in enterprises, where many different business departments might exist.", 1);
            tfQuiz[2] = new tfQuizContent(1, 3, 3, "Breaking down a major network (Class A/B/C) allows for the creation of interconnected subnetworks.", 1);
            tfQuiz[3] = new tfQuizContent(1, 3, 4, "In order to subnet a network, we need to “borrow” bits from the host portion of the address, to create subnetwork addresses.", 1);
            tfQuiz[4] = new tfQuizContent(1, 3, 5, "Borrowing 2 bits to extend a subnet mask will create 2 subnetworks.", 0);
            tfQuiz[5] = new tfQuizContent(1, 3, 6, "Borrowing 3 bits to extend a subnet mask will create 24 subnetworks.", 0);
            tfQuiz[6] = new tfQuizContent(1, 3, 7, "Borrowing 4 bits to extend a subnet mask will create 16 subnetworks.", 1);
            tfQuiz[7] = new tfQuizContent(1, 3, 8, "Borrowing 5 bits to extend a subnet mask will create 56 subnetworks.", 0);
            tfQuiz[8] = new tfQuizContent(1, 3, 9, "Dividing 256 by the number of subnetworks will allow you to calculate the network addresses of the subnetworks.", 1);
            tfQuiz[9] = new tfQuizContent(1, 3, 10, "HOST: \t\t   204.17.5.0            11001100.00010001.00000101.00000000\n" +
                    "SUBNET MASK: 255.255.255.224  11111111.11111111.11111111.11100000\n" +
                    "\n" +
                    "204.17.5.0 255.255.255.224     host address range 1 to 30\n" +
                    "204.17.5.32 255.255.255.224    host address range 33 to 62\n" +
                    "204.17.5.64 255.255.255.224    host address range 65 to 94\n" +
                    "204.17.5.96 255.255.255.224    host address range 97 to 126\n" +
                    "204.17.5.128 255.255.255.224   host address range 129 to 158\n" +
                    "204.17.5.160 255.255.255.224   host address range 161 to 190\n" +
                    "204.17.5.192 255.255.255.224   host address range 193 to 222\n" +
                    "204.17.5.224 255.255.255.224   host address range 225 to 254\n" +
                    "The above information is correct.\n", 1);
        } else if(moduleNo == 3){
            tfQuiz[0] = new tfQuizContent(1, 1, 1, "IPv4 technology is limited due to connectivity speed issues.", 0);
            tfQuiz[1] = new tfQuizContent(1, 1, 2, "IPv4 will die off very soon.", 0);
            tfQuiz[2] = new tfQuizContent(1, 1, 3, "IPv4 addresses are a valuable resource given that there are limits based on the number of devices that are possible to use, versus the number of devices that exist today.", 1);
            tfQuiz[3] = new tfQuizContent(1, 1, 4, "IPv6 aims to address this by introducing a new format of logical addressing, which uses 64-bit addresses as opposed to the 32-bit IPv4 addresses.", 0);
            tfQuiz[4] = new tfQuizContent(1, 1, 5, "IPv6 addresses are structurally identical to IPv4 addresses.", 0);
            tfQuiz[5] = new tfQuizContent(1, 1, 6, "IPv4 relies heavily on NAT (network address translation) to provide enough public IP addresses. ", 1);
            tfQuiz[6] = new tfQuizContent(1, 1, 7, "IPv6 allows for a maximum of 1 billion devices.", 0);
            tfQuiz[7] = new tfQuizContent(1, 1, 8, "In practical terms, every single device can have its own unique IPv6 address.", 1);
            tfQuiz[8] = new tfQuizContent(1, 1, 9, "It is inevitable that businesses will slowly transition into using IPv6 addresses in the not-too-distant future.", 1);
            tfQuiz[9] = new tfQuizContent(1, 1, 10, "IPv4 and IPv6 are directly compatible with each other.", 0);


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
                String url = "http://feewka.kennethpahn.info/recordtf.php?zid=" + zid + "&module_id=" + moduleNo + "&module_question=" + counter + "&answer=" + user;
                URL url2 = new URL(url);
                InputStream input = (url2).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                String status = readAll(rd);
                // intent to show successful registration.
                System.out.println("Status: " + status);
                if (status != ""){
                    Toast.makeText(getApplicationContext(), "Answer saved.",
                            Toast.LENGTH_LONG).show();
                    saveStatus(moduleNo, zid, counter + 1);
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
                String url = "http://feewka.kennethpahn.info/recordtf.php?zid=" + zid + "&module_id=" + moduleNo + "&module_question=" + counter + "&answer=" + user;
                URL url2 = new URL(url);
                InputStream input = (url2).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                String status = readAll(rd);
                // intent to show successful registration.
                System.out.println("Status: " + status);
                if (status != ""){
                    Toast.makeText(getApplicationContext(), "Answer saved.",
                            Toast.LENGTH_LONG).show();
                    saveStatus(moduleNo, zid, counter + 1);
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
            Intent a = new Intent(tfQuizDisp.this, mcqQuizDisp.class);
            a.putExtra("moduleNo", moduleNo);
            a.putExtra("zid", zid);
            startActivity(a);
        }
    }
    // save status every time
    private void saveStatus(int moduleNo, int zid, int order) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // uses php to register users.
        String url = "http://feewka.kennethpahn.info/save.php?zid=" + zid + "&module_id=" + moduleNo + "&section=" + 1 + "&order=" + order;
        URL url2 = new URL(url);
        InputStream input = (url2).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        String status = readAll(rd);
    }
    // get past answers
    private int getPastAnswers(int moduleNo, int zid, tfQuizContent[] tfQuiz) throws IOException {
        int[] pastanswers = new int[counter];
        for (int i = 0; i < counter - 1; i++){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // uses php to register users.
            String url = "http://feewka.kennethpahn.info/gettf.php?zid=" + zid + "&module=" + moduleNo + "&q=" + i;
            URL url2 = new URL(url);
            InputStream input = (url2).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            String status = readAll(rd);
            pastanswers[i] = Integer.valueOf(status);
        }
        int score = getScore(pastanswers, tfQuiz);
        return score;
    }
    // check answers and convert to score
    private int getScore(int[] pastanswers, tfQuizContent[] tfQuiz){
        int score = 0;
        for (int i = 0; i < counter - 1; i++){
            if (tfQuiz[i].getSolution() == pastanswers[i]){
                score++;
            }
        }
        return score;
    }
}

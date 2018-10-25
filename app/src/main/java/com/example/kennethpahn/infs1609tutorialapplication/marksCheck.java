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

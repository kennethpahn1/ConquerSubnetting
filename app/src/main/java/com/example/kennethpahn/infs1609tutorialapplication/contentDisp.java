package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class contentDisp extends AppCompatActivity {
    // This section allows the program to get the UI ready.
    private Button nextBtn;
    private Button prevBtn;
    private TextView moduleNameTxt;
    private TextView moduleContentTxt;
    private int i = 0;
    private int moduleNo;
    // this populates the module content so then the user can read what's available in that module.
    private String[] populateModuleContent(int moduleNo){
        String[] moduleContentArray = new String[4];
        // populate module content
        if (moduleNo == 0){
            moduleContent moduleContent1 = new moduleContent(1, 1, 1, "As opposed to physical addressing (MAC addresses), IP addresses, or Internet Protocol addresses, are a type of logical address that uniquely identifies a device on a network. Each device is assigned an IP address after it joins a network.\n");
            moduleContent moduleContent2 = new moduleContent(2, 1, 2, "There are two main types of IP addresses that are used today. These are IPv4 and IPv6 addresses, of which the former is most widely used. Here, we’ll mainly be covering and using IPv4. Further reading on IPv6 is also available later in this guide.\n");
            moduleContent moduleContent3 = new moduleContent(3, 1, 3, "IPv4 addresses are 32-bit addresses that allows information flow to be controlled and redirected/routed. Some of the features of IP addressing are DHCP, NAT and ARP - all of which play critical functions in networking (which we won’t be covering in this subnetting course).\n");
            moduleContent moduleContent4 = new moduleContent(4, 1, 4, "IP addresses have a network portion and a host portion, which simply distinguishes between whether the portion belongs to a network or host. All devices/hosts in the same broadcast domain must have the same network portion.");
            // index them
            moduleContentArray[0] = moduleContent1.getContent();
            moduleContentArray[1] = moduleContent2.getContent();
            moduleContentArray[2] = moduleContent3.getContent();
            moduleContentArray[3] = moduleContent4.getContent();
        } else if(moduleNo == 1){
            moduleContent moduleContent1 = new moduleContent(1, 1, 1, "It is not immediately obvious which portion of an IP address belongs to the network, and which portion belongs to the host. \n" +
                    "\n" +
                    "In networking, we use subnet masks to differentiate networks and hosts. \n" +
                    "A subnet mask is essentially a string of 1s and 0s which helps us do this. 1s represent the network portion of the address, while 0s represent hosts. \n");
            moduleContent moduleContent2 = new moduleContent(2, 1, 2, "To illustrate an example of this, let’s take a look at the following IP address and subnet mask.\n\n" +
                    "Address: 192.168.15.1\t (in bits; 11000000.10101000.00001111.00000001)\n" +
                    "Subnet mask: 255.255.255.0  (in bits; 11111111.11111111.11111111.00000000)\n\n" +
                    "The above IP address shows the address of a host in the network 192.168.15.0. The subnet mask essentially tells us the first 3 numbers, 192.168.15, is the host portion of the address, as the subnet mask occupies the first 24 bits of the address. \n");
            moduleContent moduleContent3 = new moduleContent(3, 1, 3, "IPv4 addresses are 32-bit addresses that allows information flow to be controlled and redirected/routed. Some of the features of IP addressing are DHCP, NAT and ARP - all of which play critical functions in networking (which we won’t be covering in this subnetting course).\n");
            moduleContent moduleContent4 = new moduleContent(4, 1, 4, "IP addresses have a network portion and a host portion, which simply distinguishes between whether the portion belongs to a network or host. All devices/hosts in the same broadcast domain must have the same network portion.");
            // index them
            moduleContentArray[0] = moduleContent1.getContent();
            moduleContentArray[1] = moduleContent2.getContent();
            moduleContentArray[2] = moduleContent3.getContent();
            moduleContentArray[3] = moduleContent4.getContent();
        } else if(moduleNo == 2){



        }




        return moduleContentArray;
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
    // save status every time
    private void saveStatus(int moduleNo, int zid, int order) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // uses php to register users.
        String url = "http://feewka.kennethpahn.info/save.php?zid=" + zid + "&module_id=" + moduleNo + "&section=" + 0 + "&order=" + i;
        URL url2 = new URL(url);
        InputStream input = (url2).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        String status = readAll(rd);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_disp);
        // get intent details
        Bundle infoPassed = getIntent().getExtras();
        moduleNo = infoPassed.getInt("moduleNo");
        final int zid = infoPassed.getInt("zid");
        String moduleName = infoPassed.getString("moduleName");
        i = infoPassed.getInt("order");
        // load UI elements
        nextBtn = (Button) findViewById(R.id.nextBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);
        moduleNameTxt = (TextView) findViewById(R.id.moduleNameTxt);
        moduleContentTxt = (TextView) findViewById(R.id.moduleContentTxt);
        // figure out what module number we want to use...
        final String[] moduleContentArray = populateModuleContent(moduleNo);
        // load data
        moduleNameTxt.setText(moduleName);
        moduleContentTxt.setText(moduleContentArray[i]);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // basically stop it from crashing if there's less than the usual amount on the array.
                try {
                    // this loads the content text.
                    if (moduleContentArray[i + 1].equals(null)){

                    } else{
                        i++;
                        moduleContentTxt.setText(moduleContentArray[i]);
                        saveStatus(moduleNo, zid, i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // this loads the true/false quiz when the user is done with the module
                    Intent a = new Intent(contentDisp.this, tfQuizDisp.class);
                    a.putExtra("zid", zid);
                    a.putExtra("moduleNo", moduleNo);
                    startActivity(a);
                }

            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // basically stop it from crashing if the number goes <0.
                    if (moduleContentArray[i - 1].equals(null)){

                    } else{
                        i--;
                        moduleContentTxt.setText(moduleContentArray[i]);
                        saveStatus(moduleNo, zid, i);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // this logs out the user on back press on the main screen.
    @Override
    public void onBackPressed() {
        // sourced from https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
        // sourced from https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
        // sourced from https://stackoverflow.com/questions/5447092/get-context-inside-onclickdialoginterface-v-int-buttonid#5447125
        // dialog fix sourced from https://stackoverflow.com/questions/27965662/how-can-i-change-default-dialog-button-text-color-in-android-5
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent a = new Intent(MainActivity.this, login.class);
                startActivity(a);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();

        alert.show();
    }
    // the usual getting the UI and stuff to run...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // populate all the data required
        populateModules();
        Bundle infoPassed = getIntent().getExtras();
        final int zid = infoPassed.getInt("zid");
        System.out.println("zid received: " + zid);

//        moduleListView = (ListView) findViewById(R.id.moduleList);
//        // create Array List for the initial UI
//        ArrayList<String> moduleList = new ArrayList<String>(Arrays.asList(moduleListArray));
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                MainActivity.this, R.layout.list_item, moduleList);
//        moduleListView.setAdapter(arrayAdapter);
//        // intents - allow a selection of a module and move onto the next part...
//        moduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                Intent a = new Intent(MainActivity.this, moduleDetail.class);
//                a.putExtra("moduleNo", position);
//                a.putExtra("moduleName", moduleArray[position].getName());
//                a.putExtra("moduleDesc", moduleArray[position].getDescription());
//                startActivity(a);
//            }
//        });

        ImageButton marksBtn = (ImageButton) findViewById(R.id.marksBtn);
        ImageButton img1 = (ImageButton) findViewById(R.id.imgBtn1);
        ImageButton img2 = (ImageButton) findViewById(R.id.imgBtn2);
        ImageButton img3 = (ImageButton) findViewById(R.id.imgBtn3);
        ImageButton img4 = (ImageButton) findViewById(R.id.imgBtn4);
        ImageButton img5 = (ImageButton) findViewById(R.id.imgBtn5);
        // each of these image on click listeners enable the module to load. more on the functions
        // they call later...
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int section = checkResume(zid, 0);
                    System.out.println("checkResume output: " + section);
                    if (section != -1){
                        resumeMsg(0, zid, section);
                    } else{
                        handoff(0, zid, -1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ask whether to continue where they left off
                try {
                    int section = checkResume(zid, 1);
                    System.out.println("checkResume output: " + section);
                    if (section != -1){
                        resumeMsg(1, zid, section);
                    } else{
                        handoff(1, zid, -1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int section = checkResume(zid, 2);
                    System.out.println("checkResume output: " + section);
                    if (section != -1){
                        resumeMsg(2, zid, section);
                    } else{
                        handoff(2, zid, -1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int section = checkResume(zid, 3);
                    System.out.println("checkResume output: " + section);
                    if (section != -1){
                        resumeMsg(3, zid, section);
                    } else{
                        handoff(3, zid, -1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int section = checkResume(zid, 4);
                    System.out.println("checkResume output: " + section);
                    if (section != -1){
                        resumeMsg(4, zid, section);
                    } else{
                        handoff(4, zid, 0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        marksBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, marksCheck.class);
                a.putExtra("zid", zid);
                startActivity(a);
            }
        });
    }
    // declare all the UI elements
    private ListView moduleListView;
    // these help populate all the content
    private String[] moduleListArray = new String[5];
    private modules[] moduleArray = new modules[5];
    private ImageButton marksBtn;
    // this populates the module directory so users can see them on the listview. this does it
    // by getting them hardcoded, chucked into an array for easy use by the rest of the program.
    public void populateModules(){
        // populate the module directory
        modules module1 = new modules(1, "Overview of IP Addressing", "Introduction to basic IP Addressing concepts.\n\n" + "An Internet Protocol address (IP address) is a numerical label assigned to each device connected to a computer network that uses the Internet Protocol for communication. An IP address serves two principal functions: host or network interface identification and location addressing.");
        modules module2 = new modules(2, "Subnet Masks", "Learn more about Networks and Hosts!");
        modules module3 = new modules(3, "Why Subnetting? The Process Broken Down", "Why do we subnet? How is it done?");
        modules module4 = new modules(4, "IPv6", "Transitioning from IPv4 to IPv6. Why?");
        modules module5 = new modules(5, "Resources", "Extra course resources.");
        // index them
        moduleArray[0] = module1;
        moduleArray[1] = module2;
        moduleArray[2] = module3;
        moduleArray[3] = module4;
        moduleArray[4] = module5;
        // parse and place to get ready for ListView.
        for (int i = 0; i < 5; i++){
            moduleListArray[i] = moduleArray[i].getName();
        }
    }
    // sourced from https://mobilesiri.com/json-parsing-in-android-using-android-studio/
    // again - convert html into text for the app to read our api output
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    // get where the user left off
    // does so using our custom api that connects to a mysql db which takes the order
    // and then uses intent to pass them over and resume from the last known part.
    private int checkResume(int zid, int moduleNo) throws IOException {
        // sourced from https://developer.android.com/reference/android/os/StrictMode
        // Used to allow http to run on main thread for json.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // uses php to register users.
        String url = "http://feewka.kennethpahn.info/resume.php?zid=" + zid + "&module_id=" + moduleNo;
        URL url2 = new URL(url);
        InputStream input = (url2).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        String status = readAll(rd);
        System.out.println("Status: " + status);
        try {
            return Integer.valueOf(status);
        } catch (Exception e){
            return -1;
        }
    }
    // gets the exact part of the module (besides the section) and goes directly to that.
    // uses our custom api for it.
    private int getOrder (int moduleNo, int zid, int section) throws IOException {
        int order = 0;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // uses php to register users.
        String url = "http://feewka.kennethpahn.info/getorder.php?zid=" + zid + "&module_id=" + moduleNo + "&section=" + section;
        URL url2 = new URL(url);
        InputStream input = (url2).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        String status = readAll(rd);
        order = Integer.valueOf(status);
        System.out.println("Order grabbed: " + status);
        return order;
    }
    // use switch statements to pick which type of handoff we use.
    private void handoff(int moduleNo, int zid, int section) throws IOException {
        // let's agree that 0 = from the start, 1 = from the t/f questions, 2 = from the youtube videos, and 3 = from the mcqs.
        // sourced from https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
        switch (section){
            case -1: Intent e = new Intent(MainActivity.this, moduleDetail.class);
                e.putExtra("moduleNo", moduleNo);
                e.putExtra("moduleName", moduleArray[moduleNo].getName());
                e.putExtra("moduleDesc", moduleArray[moduleNo].getDescription());
                //e.putExtra("order", getOrder(moduleNo, zid, section));
                e.putExtra("zid", zid);
                startActivity(e);
                break;
            case 0: Intent a = new Intent(MainActivity.this, contentDisp.class);
            if (moduleNo == 4){
                String url = "https://oc.kennethpahn.info/index.php/s/oiWxwL6zQa8nXaG";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } else{
                a.putExtra("moduleNo", moduleNo);
                a.putExtra("moduleName", moduleArray[moduleNo].getName());
                a.putExtra("moduleDesc", moduleArray[moduleNo].getDescription());
                a.putExtra("order", getOrder(moduleNo, zid, section));
                a.putExtra("zid", zid);
                startActivity(a);
            }

            break;
            case 1: Intent b = new Intent(MainActivity.this, tfQuizDisp.class);
                b.putExtra("moduleNo", moduleNo);
                b.putExtra("moduleName", moduleArray[moduleNo].getName());
                b.putExtra("moduleDesc", moduleArray[moduleNo].getDescription());
                b.putExtra("order", getOrder(moduleNo, zid, section));
                b.putExtra("zid", zid);
                startActivity(b);
                break;
                /*
            case 2: Intent c = new Intent(MainActivity.this, multimediaContentDisp.class);
                c.putExtra("moduleNo", moduleNo);
                c.putExtra("moduleName", moduleArray[moduleNo].getName());
                c.putExtra("moduleDesc", moduleArray[moduleNo].getDescription());
                c.putExtra("order", getOrder(moduleNo, zid, section));
                c.putExtra("zid", zid);
                c.putExtra("lastevent", 123456);
                System.out.println("zid passed: " + zid);
                startActivity(c);
                break;*/
            case 3: Intent d = new Intent(MainActivity.this, mcqQuizDisp.class);
                d.putExtra("moduleNo", moduleNo);
                d.putExtra("moduleName", moduleArray[moduleNo].getName());
                d.putExtra("moduleDesc", moduleArray[moduleNo].getDescription());
                d.putExtra("order", getOrder(moduleNo, zid, section));
                d.putExtra("zid", zid);
                startActivity(d);
                break;
        }
    }
    // asks the user if they'd like to continue where they'd left off using a msgbox
    // and then use our custom api to feed it through.
    private void resumeMsg(final int moduleNo, final int zid, final int section){
        // sourced from https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
        // sourced from https://stackoverflow.com/questions/5447092/get-context-inside-onclickdialoginterface-v-int-buttonid#5447125
        // dialog fix sourced from https://stackoverflow.com/questions/27965662/how-can-i-change-default-dialog-button-text-color-in-android-5
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setTitle("Resume Study");
        builder.setMessage("Would you like to continue from where you last left off?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // get the thing to find out where to resume at...
                try {
                    handoff(moduleNo, zid, section);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
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
                    handoff(moduleNo, zid, -1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();

        alert.show();
    }
}

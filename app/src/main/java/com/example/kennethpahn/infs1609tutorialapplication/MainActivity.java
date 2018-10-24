package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // populate all the data required
        populateModules();
        Bundle infoPassed = getIntent().getExtras();
        final int zid = infoPassed.getInt("zid");
        System.out.println("zid received: " + zid);
//        // link to UI
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
        // get resume information

        ImageButton img1 = (ImageButton) findViewById(R.id.imgBtn1);
        ImageButton img2 = (ImageButton) findViewById(R.id.imgBtn2);
        ImageButton img3 = (ImageButton) findViewById(R.id.imgBtn3);
        ImageButton img4 = (ImageButton) findViewById(R.id.imgBtn4);
        ImageButton img5 = (ImageButton) findViewById(R.id.imgBtn5);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, moduleDetail.class);
                a.putExtra("moduleNo", 0);
                a.putExtra("moduleName", moduleArray[0].getName());
                a.putExtra("moduleDesc", moduleArray[0].getDescription());
                startActivity(a);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ask whether to continue where they left off
                // stolen from https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
                    /*
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent a = new Intent(MainActivity.this, moduleDetail.class);
                                        a.putExtra("moduleNo", 1);
                                        a.putExtra("moduleName", moduleArray[1].getName());
                                        a.putExtra("moduleDesc", moduleArray[1].getDescription());
                                        startActivity(a);
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        Intent b = new Intent(MainActivity.this, moduleDetail.class);
                                        b.putExtra("moduleNo", 2);
                                        b.putExtra("moduleName", moduleArray[1].getName());
                                        b.putExtra("moduleDesc", moduleArray[1].getDescription());
                                        startActivity(b);
                                        break;
                                }
                            }
                        };*/
                    /*
                        if(checkResume(zid, 1) == 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                            builder.setTitle("Confirm");
                            builder.setMessage("Are you sure?");

                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing but close the dialog

                                    dialog.dismiss();
                                }
                            });

                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    // Do nothing
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                        */
                Intent a = new Intent(MainActivity.this, moduleDetail.class);
                a.putExtra("moduleNo", 2);
                a.putExtra("moduleName", moduleArray[2].getName());
                a.putExtra("moduleDesc", moduleArray[2].getDescription());
                startActivity(a);

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, moduleDetail.class);
                a.putExtra("moduleNo", 2);
                a.putExtra("moduleName", moduleArray[2].getName());
                a.putExtra("moduleDesc", moduleArray[2].getDescription());
                startActivity(a);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, moduleDetail.class);
                a.putExtra("moduleNo", 3);
                a.putExtra("moduleName", moduleArray[3].getName());
                a.putExtra("moduleDesc", moduleArray[3].getDescription());
                startActivity(a);
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, moduleDetail.class);
                a.putExtra("moduleNo", 4);
                a.putExtra("moduleName", moduleArray[4].getName());
                a.putExtra("moduleDesc", moduleArray[4].getDescription());
                startActivity(a);
            }
        });
    }
    // declare all the UI elements
    private ListView moduleListView;
    // these help populate all the content
    private String[] moduleListArray = new String[5];
    private modules[] moduleArray = new modules[5];
    // this populates the module directory so users can see them on the listview.
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
    // stolen from https://mobilesiri.com/json-parsing-in-android-using-android-studio/
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    // get where the user left off
    private int checkResume(int zid, int moduleNo) throws IOException {
        // Stolen from https://developer.android.com/reference/android/os/StrictMode
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
        // intent to show successful registration.
        System.out.println("Resume Status: " + status + " & zid: " + zid + " & module: " + moduleNo);
        if (status != "") {
            return -1;
        } else {
            return Integer.valueOf(status);
        }
    }
}

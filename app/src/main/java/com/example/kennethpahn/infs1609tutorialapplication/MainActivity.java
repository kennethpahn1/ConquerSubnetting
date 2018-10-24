package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

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
                Intent a = new Intent(MainActivity.this, moduleDetail.class);
                a.putExtra("moduleNo", 1);
                a.putExtra("moduleName", moduleArray[1].getName());
                a.putExtra("moduleDesc", moduleArray[1].getDescription());
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

}

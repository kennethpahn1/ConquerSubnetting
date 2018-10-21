package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        // link to UI
        moduleListView = (ListView) findViewById(R.id.moduleList);
        // create Array List for the initial UI
        ArrayList<String> moduleList = new ArrayList<String>(Arrays.asList(moduleListArray));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                MainActivity.this, R.layout.list_item, moduleList);
        moduleListView.setAdapter(arrayAdapter);
        // intents - allow a selection of a module and move onto the next part...
        moduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent a = new Intent(MainActivity.this, moduleDetail.class);
                a.putExtra("moduleNo", position);
                a.putExtra("moduleName", moduleArray[position].getName());
                a.putExtra("moduleDesc", moduleArray[position].getDescription());
                startActivity(a);
            }
        });
    }

    // declare all the UI elements
    private ListView moduleListView;
    // these help populate all the content
    private String[] moduleListArray = new String[12];
    private modules[] moduleArray = new modules[12];
    public void populateModules(){
        // populate the module directory
        modules module1 = new modules(1, getResources().getString(R.string.module1), "Helps you write a simple Java program, write output to a console, explain the basic syntax of a Java program, and create/compile/run Java programs.");
        modules module2 = new modules(2, "Elementary Programming 1", "Use Java for simple maths, getting input, and storing some data. Learn about data types here too.");
        modules module3 = new modules(3, "Elementary Programming 2", "Learn some more Java simple maths, scientific notation, and increment/decrements.");
        modules module4 = new modules(4, "Selection", "Learn about booleans, if statements, and switch statements.");
        modules module5 = new modules(5, "Loops", "Get repetitive with some loops - while, do-while, and for loops.");
        modules module6 = new modules(6, "Methods", "Make your own methods with parameters, return values, and making your code reusable.");
        modules module7 = new modules(7, "Single and Multidimensional Arrays", "Declare arrays, initialize them, and more.");
        modules module8 = new modules(8, "Objects and Classes", "Find out more about Object-oriented Programming!");
        modules module9 = new modules(9, "Thinking in Objects", "Apply class abstractions, know the difference between procedural and object-oriented paradigms, and discover relationships between classes!");
        modules module10 = new modules(10, "Thinking in Objects", "A continuation of lecture 9.");
        modules module11 = new modules(11, "Inheritance", "Not about getting your family wealth, but about defining subclasses and superclasses and invoking them.");
        modules module12 = new modules(12, "Polymorphism", "Learn more about polymorphism, dynamic binding, and generic programming.");
        // index them
        moduleArray[0] = module1;
        moduleArray[1] = module2;
        moduleArray[2] = module3;
        moduleArray[3] = module4;
        moduleArray[4] = module5;
        moduleArray[5] = module6;
        moduleArray[6] = module7;
        moduleArray[7] = module8;
        moduleArray[8] = module9;
        moduleArray[9] = module10;
        moduleArray[10] = module11;
        moduleArray[11] = module12;
        // parse and place to get ready for ListView.
        for (int i = 0; i < 12; i++){
            moduleListArray[i] = moduleArray[i].getName();
        }
    }

}

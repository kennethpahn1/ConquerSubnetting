package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class contentDisp extends AppCompatActivity {
    private Button nextBtn;
    private Button prevBtn;
    private TextView moduleNameTxt;
    private TextView moduleContentTxt;
    private int i = 0;
    private String[] module1ContentArray = new String[4];
    private int moduleNo;
    public void populateModuleContent(){
        // populate module content
        moduleContent moduleContent1 = new moduleContent(1, 1, 1, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec varius nisl. Maecenas et hendrerit nibh, vel ullamcorper dui. Curabitur finibus " +
                "tortor pellentesque felis tincidunt, vel elementum sapien pretium. Sed aliquam tellus tortor, sit amet luctus odio sodales id. Phasellus nec ligula nec ipsum efficitur rhoncus a non arcu. Praesent fermentum sed sapien et aliquet. " +
                "Vivamus ut elit nibh. Pellentesque mauris elit, pretium vitae massa quis, pharetra gravida lacus. Cras sed metus ut nibh hendrerit tristique sit amet ac sem. Nullam sollicitudin, urna sit amet laoreet accumsan, lacus ex imperdiet est, at" +
                " ullamcorper mauris tortor et urna. Aenean pretium eros laoreet tortor ultrices, vel ornare mi semper.");
        moduleContent moduleContent2 = new moduleContent(2, 1, 2, "Cras vehicula molestie semper. Ut aliquet ac ante ut faucibus. Quisque maximus malesuada erat non malesuada. Duis non arcu eget nisl condimentum bibendum. Proin at " +
                "convallis lacus. Nulla sed risus eu leo suscipit suscipit id at sem. Curabitur finibus sapien id vestibulum egestas. Maecenas sagittis interdum hendrerit. Sed pretium nisi nibh, vel suscipit magna vestibulum vel. Aenean ultricies molestie luctus. ");
        moduleContent moduleContent3 = new moduleContent(3, 1, 3, "Fusce ut turpis tortor. Fusce nisl arcu, laoreet sed rhoncus vel, consequat sed arcu. Fusce erat diam, porttitor vitae sem quis, ultrices luctus orci. Donec euismod eu metus vel " +
                "ornare. Aenean diam nibh, ultricies quis pellentesque id, hendrerit imperdiet mauris. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam non accumsan est. In ultricies nibh non nibh consequat lacinia. In " +
                "justo ipsum, vulputate et est quis, suscipit pharetra nibh. Etiam sapien purus, molestie vitae mattis id, porttitor eu lectus. Nullam quam ante, dictum vel purus at, venenatis faucibus nisi. Duis eget ultricies felis. ");
        moduleContent moduleContent4 = new moduleContent(4, 1, 4, "Suspendisse potenti. Pellentesque lorem nisl, varius non vulputate ac, scelerisque sit amet leo. Vivamus consequat convallis est sed sollicitudin. Donec dui dui, aliquam sed neque" +
                " malesuada, porta volutpat enim. Aliquam sed fermentum erat, vitae ullamcorper elit. Sed et auctor nisi. Quisque a erat non sem euismod commodo. Quisque pharetra dictum orci, id pulvinar turpis mattis sit amet. Nulla vel iaculis nibh. Pellentesque ultricies eget" +
                " justo nec mattis. Maecenas mattis interdum pretium. ");
        // index them
        module1ContentArray[0] = moduleContent1.getContent();
        module1ContentArray[1] = moduleContent2.getContent();
        module1ContentArray[2] = moduleContent3.getContent();
        module1ContentArray[3] = moduleContent4.getContent();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_disp);
        populateModuleContent();
        // get intent details
        Bundle infoPassed = getIntent().getExtras();
        moduleNo = infoPassed.getInt("moduleNo");
        String moduleName = infoPassed.getString("moduleName");
        // load UI elements
        nextBtn = (Button) findViewById(R.id.nextBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);
        moduleNameTxt = (TextView) findViewById(R.id.moduleNameTxt);
        moduleContentTxt = (TextView) findViewById(R.id.moduleContentTxt);
        // load data
        moduleNameTxt.setText(moduleName);
        moduleContentTxt.setText(module1ContentArray[i]);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // basically stop it from crashing if there's less than the usual amount on the array.
                try {
                    if (module1ContentArray[i + 1].equals(null)){

                    } else{
                        i++;
                        moduleContentTxt.setText(module1ContentArray[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Intent a = new Intent(contentDisp.this, mcqQuizDisp.class);
                    a.putExtra("moduleNo", moduleNo);
                    //a.putExtra("moduleName", moduleArray[position].getName());
                    //a.putExtra("moduleDesc", moduleArray[position].getDescription());
                    //if (position == 0){
                        //a.putExtra("moduleContents", moduleContentArray);
                    //}
                    startActivity(a);
                }

            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (module1ContentArray[i - 1].equals(null)){

                    } else{
                        i--;
                        moduleContentTxt.setText(module1ContentArray[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

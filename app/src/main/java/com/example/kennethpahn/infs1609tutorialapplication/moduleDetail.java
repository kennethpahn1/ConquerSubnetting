package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class moduleDetail extends YouTubeBaseActivity{
    // get the UI elements set
    private TextView moduleNameTxt;
    private TextView moduleDescTxt;
    private Button startBtn;
    private int moduleNo;
    int zid;// = infoPassed.getInt("zid");
    public static final String API_KEY = "AIzaSyCoHPQ88V6gN65zgPNgNoVF6igNAI9kRds";
    @Override
    // bring it back to main activity on back.
    public void onBackPressed() {
        // sourced from https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
        Intent a = new Intent(moduleDetail.this, MainActivity.class);
        a.putExtra("zid", zid);
        startActivity(a);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);

        // get intent and information
        Bundle infoPassed = getIntent().getExtras();
        zid = infoPassed.getInt("zid");
        moduleNo = infoPassed.getInt("moduleNo");
        String moduleName = infoPassed.getString("moduleName");
        String moduleDesc = infoPassed.getString("moduleDesc");
        // load into the UI
        moduleNameTxt = (TextView) findViewById(R.id.moduleNameTxt);
        moduleNameTxt.setText(moduleNo + ": " + moduleName);
        moduleDescTxt = (TextView) findViewById(R.id.moduleDescTxt);
        moduleDescTxt.setText(moduleDesc);
        // link button to the UI
        startBtn = (Button) findViewById(R.id.startBtn);
        // allow it to do stuff
        // use this to move across to the cOnTeNt
        startBtn.setOnClickListener(new View.OnClickListener(){
              @Override
               public void onClick(View v) {
                  Intent a = new Intent(moduleDetail.this, contentDisp.class);
                  a.putExtra("zid", zid);
                  a.putExtra("moduleNo", moduleNo);
                  a.putExtra("moduleName", moduleNameTxt.getText().toString());
                  startActivity(a);
               }
            }
        );
        // youtube video at the bottom.
        YouTubePlayerView youTubePlayerView;
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        YouTubePlayer.OnInitializedListener onInitializedListener;

        onInitializedListener = new YouTubePlayer.OnInitializedListener(){
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                switch(moduleNo){
                    case 0: youTubePlayer.loadVideo("U2IxdEYgoEg");
                    break;
                    case 1: youTubePlayer.loadVideo("_bn0CF5zqvo");
                    break;
                    case 2: youTubePlayer.loadVideo("ZxAwQB8TZsM");
                    break;
                    case 3: youTubePlayer.loadVideo("j3VIgP9aosY");
                    break;
                }

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };

        youTubePlayerView.initialize(API_KEY,onInitializedListener);



    }
}

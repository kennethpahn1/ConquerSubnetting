package com.example.kennethpahn.infs1609tutorialapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.google.android.youtube.player.YouTubePlayer.*;
// sourced from https://android-coffee.com/tutorial-play-youtube-video/
public class multimediaContentDisp extends YouTubeBaseActivity implements OnInitializedListener {
    // has all the links for each module and sends it across
    private multimediaContent[] populateMultimediaContent(int moduleNo){
        multimediaContent[] multimediaModule = new multimediaContent[3];
        if (moduleNo == 0){
            multimediaModule[0] = new multimediaContent(1, 1, 1, "CQuR8LVKhUE");
            multimediaModule[1] = new multimediaContent(1, 1, 2, "lCcwn6bGUtU");
            multimediaModule[2] = new multimediaContent(1, 1, 3, "FTQbiNvZqaY");
        }
        return multimediaModule;
    }
    // API key so then we can get some youtube videos going
    public static final String API_KEY = "AIzaSyCoHPQ88V6gN65zgPNgNoVF6igNAI9kRds";
    public static String VIDEO_ID = "";
    // so then the app can keep track of which video to play next
    private int moduleNo;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia_content_disp);
        /** Initializing YouTube Player View **/
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.videoView);
        youTubePlayerView.initialize(API_KEY, this);
        // grab the moduleNo
        Bundle infoPassed = getIntent().getExtras();
        moduleNo = infoPassed.getInt("moduleNo");
        counter = infoPassed.getInt("counter");
        // populate and play the video
        multimediaContent[] multimediaModule = populateMultimediaContent(moduleNo);
        VIDEO_ID = multimediaModule[counter].getURL();
    }
    // just in case the thing doesn't wanna start up
    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialise!", Toast.LENGTH_LONG).show();
    }
    // this plays the video when the video is initialised
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }
    // just a bunch of things to do when the thing is playing or not, no need for this.
    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };
    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
            // change the videos to the next one in the module and reopen this java intent thingo
            counter++;
            if (counter < 3){
                Intent a = new Intent(multimediaContentDisp.this, multimediaContentDisp.class);
                a.putExtra("moduleNo", moduleNo);
                a.putExtra("counter", counter);
                startActivity(a);
            } else{
                Intent a = new Intent(multimediaContentDisp.this, mcqQuizDisp.class);
                a.putExtra("moduleNo", moduleNo);
                startActivity(a);
            }
        }
        @Override
        public void onVideoStarted() {
        }
    };
}
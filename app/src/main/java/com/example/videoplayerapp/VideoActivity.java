package com.example.videoplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    String videoID;
    String videoURL;


    // Credit for expression variable and getVideoId Function: https://findnerd.com/list/view/How-to-generate-video-id-from-youtube-video-url-in-android/24468/

    private final static String expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
    public static String getVideoId(String videoUrl) {
        if (videoUrl == null || videoUrl.trim().length() <= 0){
            return null;
        }
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(videoUrl);
        try {
            if (matcher.find())
                return matcher.group();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlayerView);

        Intent intent = getIntent();
        videoURL = intent.getStringExtra("video_url");

        videoID = getVideoId(videoURL);


        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youtubePlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youtubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);
            }
        });




    }

}
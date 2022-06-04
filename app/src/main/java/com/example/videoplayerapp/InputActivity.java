package com.example.videoplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.videoplayerapp.data.DatabaseHelper;
import com.example.videoplayerapp.model.Account;
import com.example.videoplayerapp.model.PlaylistVideo;

public class InputActivity extends AppCompatActivity {

    EditText inputEditText;
    Button playButton;
    Button addToPlaylistButton;
    Button myPlaylistButton;
    Account account;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        inputEditText = findViewById(R.id.inputEditText);
        playButton = findViewById(R.id.playButton);
        addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        myPlaylistButton = findViewById(R.id.myPlaylistButton);

        playButton.setBackgroundResource(R.drawable.navbutton);
        account = getIntent().getParcelableExtra("selected_account");

        db = new DatabaseHelper(getApplicationContext());


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "No video URL provided.", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                    intent.putExtra("video_url", inputEditText.getText().toString());

                    startActivity(intent);
                }

            }
        });


        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "No video URL provided.", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    PlaylistVideo playlistVideo = new PlaylistVideo();
                    playlistVideo.setVideo_url(inputEditText.getText().toString());
                    playlistVideo.setAccount_id(account.getAccount_id());

                    long result = db.insertVideo(playlistVideo);

                    Toast.makeText(getApplicationContext(), "Video URL added to Playlist.", Toast.LENGTH_SHORT).show();
                }


            }
        });


        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlaylistActivity.class);
                intent.putExtra("selected_account", (Parcelable) account);
                startActivity(intent);
            }
        });




    }
}
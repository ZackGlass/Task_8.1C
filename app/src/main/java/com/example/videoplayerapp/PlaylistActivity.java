package com.example.videoplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.videoplayerapp.data.DatabaseHelper;
import com.example.videoplayerapp.model.Account;
import com.example.videoplayerapp.model.PlaylistVideo;
import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    ListView playlistListView;
    DatabaseHelper db;
    Account account;
    List<PlaylistVideo> accountPlaylist;
    ArrayList<String> playlistArrayList;
    ArrayAdapter<String> adapter;

    private AdapterView.OnItemClickListener itemClickedHandler = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
            intent.putExtra("video_url", accountPlaylist.get(i).getVideo_url());
            startActivity(intent);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlistListView = findViewById(R.id.playlistListView);

        Intent intent = getIntent();
        account = intent.getParcelableExtra("selected_account");

        playlistArrayList = new ArrayList<>();


        db = new DatabaseHelper(getApplicationContext());

        accountPlaylist = db.fetchPlaylist(account);

        if (!accountPlaylist.isEmpty())
        {
            for (PlaylistVideo playlistVideo : accountPlaylist)
            {
                playlistArrayList.add(playlistVideo.getVideo_url());
            }

        }


        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, playlistArrayList);

        playlistListView.setAdapter(adapter);
        playlistListView.setOnItemClickListener(itemClickedHandler);



    }

}
package com.example.videoplayerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.videoplayerapp.model.Account;
import com.example.videoplayerapp.model.PlaylistVideo;
import com.example.videoplayerapp.util.Util;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + Util.ACCOUNT_TABLE + "(" +
                Util.ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Util.ACC_NAME + " TEXT, " +
                Util.ACC_USERNAME + " TEXT, " +
                Util.ACC_PASSWORD + " TEXT)";


        String CREATE_PLAYLIST_TABLE = "CREATE TABLE " + Util.VIDEO_TABLE + "(" +
                Util.VIDEO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Util.ACC_ID + " INTEGER REFERENCES " + Util.ACCOUNT_TABLE + "(" + Util.ACC_ID + "), " +
                Util.VIDEO_URL + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE);
        sqLiteDatabase.execSQL(CREATE_PLAYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Account> fetchAllAccounts()
    {
        List<Account> accountList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.ACCOUNT_TABLE;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Account account = new Account();
                account.setAccount_id(cursor.getInt(0));
                account.setAccount_name(cursor.getString(1));
                account.setAccount_username(cursor.getString(2));
                account.setAccount_password(cursor.getString(3));

                accountList.add(account);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return accountList;

    }

    public List<PlaylistVideo> fetchPlaylist (Account account)
    {
        List<PlaylistVideo> videoPlaylist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectUserVideos = " SELECT * FROM " + Util.VIDEO_TABLE + " WHERE " + Util.ACC_ID + " = " + account.getAccount_id();
        Cursor cursor = db.rawQuery(selectUserVideos, null);

        if (cursor.moveToFirst())
        {
            do
            {
                PlaylistVideo playlistVideo = new PlaylistVideo();
                playlistVideo.setVideo_id(cursor.getInt(0));
                playlistVideo.setAccount_id(cursor.getInt(1));
                playlistVideo.setVideo_url(cursor.getString(2));

                videoPlaylist.add(playlistVideo);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return videoPlaylist;

    }

    public long insertAccount(Account account)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.ACC_NAME, account.getAccount_name());
        contentValues.put(Util.ACC_USERNAME, account.getAccount_username());
        contentValues.put(Util.ACC_PASSWORD, account.getAccount_password());
        long newRowId = db.insert(Util.ACCOUNT_TABLE, null, contentValues);
        db.close();
        return newRowId;
    }

    public long insertVideo(PlaylistVideo playlistVideo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.ACC_ID, playlistVideo.getAccount_id());
        contentValues.put(Util.VIDEO_URL, playlistVideo.getVideo_url());
        long newRowId = db.insert(Util.VIDEO_TABLE, null, contentValues);
        db.close();
        return newRowId;
    }



}

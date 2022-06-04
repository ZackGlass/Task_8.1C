package com.example.videoplayerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaylistVideo implements Parcelable {

    private int video_id;
    private int account_id;
    private String video_url;

    public PlaylistVideo(String video_url) {
        this.video_url = video_url;
    }

    public PlaylistVideo() {

    }

    protected PlaylistVideo(Parcel in) {
        video_id = in.readInt();
        account_id = in.readInt();
        video_url = in.readString();
    }

    public static final Creator<PlaylistVideo> CREATOR = new Creator<PlaylistVideo>() {
        @Override
        public PlaylistVideo createFromParcel(Parcel in) {
            return new PlaylistVideo(in);
        }

        @Override
        public PlaylistVideo[] newArray(int size) {
            return new PlaylistVideo[size];
        }
    };

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(video_id);
        parcel.writeInt(account_id);
        parcel.writeString(video_url);
    }
}

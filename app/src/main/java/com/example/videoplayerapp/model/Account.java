package com.example.videoplayerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable {

    private int account_id;
    private String account_name;
    private String account_username;
    private String account_password;

    public Account(String account_name, String account_username, String account_password) {
        this.account_name = account_name;
        this.account_username = account_username;
        this.account_password = account_password;
    }

    protected Account(Parcel in) {
        account_id = in.readInt();
        account_name = in.readString();
        account_username = in.readString();
        account_password = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public Account() {

    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_username() {
        return account_username;
    }

    public void setAccount_username(String account_username) {
        this.account_username = account_username;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(account_id);
        parcel.writeString(account_name);
        parcel.writeString(account_username);
        parcel.writeString(account_password);
    }
}

package com.example.videoplayerapp;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button signupButton;
    DatabaseHelper db;
    List<Account> accountList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        db = new DatabaseHelper(getApplicationContext());

        accountList = new ArrayList<>();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accountList = db.fetchAllAccounts();
                Account account = new Account();

                for (int i = 0; i < accountList.size(); i++)
                {
                    if (accountList.get(i).getAccount_username().equals(usernameEditText.getText().toString()))
                    {
                        account = accountList.get(i);
                        break;
                    }

                }


                if (account.getAccount_username() != null)
                {
                    if (account.getAccount_password().equals(passwordEditText.getText().toString()))
                    {
                        Intent intent = new Intent(getApplicationContext(), InputActivity.class);
                        intent.putExtra("selected_account", account);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Username not found.", Toast.LENGTH_SHORT).show();

                }


            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
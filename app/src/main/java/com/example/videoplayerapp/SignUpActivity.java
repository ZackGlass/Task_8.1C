package com.example.videoplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.videoplayerapp.data.DatabaseHelper;
import com.example.videoplayerapp.model.Account;

public class SignUpActivity extends AppCompatActivity {

    EditText fullNameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button createAccountButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        usernameEditText = findViewById(R.id.usernameInputEditText);
        passwordEditText = findViewById(R.id.passwordInputEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        createAccountButton = findViewById(R.id.createAccountButton);

        db = new DatabaseHelper(getApplicationContext());




        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String accountName = fullNameEditText.getText().toString();
                String accountUsername = usernameEditText.getText().toString();
                String accountPassword = passwordEditText.getText().toString();

                if (accountName.isEmpty() || accountUsername.isEmpty() || accountPassword.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fields can't be empty.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!accountPassword.equals(confirmPasswordEditText.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(), "Password fields do not match.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        long result = db.insertAccount(new Account(accountName, accountUsername, accountPassword));
                        finish();
                    }
                }


                Toast.makeText(getApplicationContext(), "Account Created.", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
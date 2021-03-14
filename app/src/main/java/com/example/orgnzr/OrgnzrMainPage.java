package com.example.orgnzr;

import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class OrgnzrMainPage extends AppCompatActivity {

    EditText name, emailAddress, username, userPassword;
    Button createAccount, login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        emailAddress = findViewById(R.id.emailAddress);
        username = findViewById(R.id.username);
        userPassword= findViewById(R.id.userPassword);

        createAccount = findViewById(R.id.btnCreateAccount);
        login = findViewById(R.id.btnSwitchLogin);

        DB = new DBHelper(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String emailAddressTXT = emailAddress.getText().toString();
                String usernameTXT = username.getText().toString();
                String userPasswordTXT = userPassword.getText().toString();

                Boolean checkAccountCreation = DB.createUserAccount(usernameTXT,nameTXT,emailAddressTXT,userPasswordTXT);
                if(checkAccountCreation==true){
                    Toast.makeText(OrgnzrMainPage.this,"Account Successfully Created",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(OrgnzrMainPage.this,"Account Not Created",Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intentLogin = new Intent(OrgnzrMainPage.this, LoginPage.class);
               startActivity(intentLogin);
            }
        });


    }

    }

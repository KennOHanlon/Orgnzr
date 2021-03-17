package com.example.orgnzr;

import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginPage extends AppCompatActivity {

    EditText username, userPassword;
    Button login, createAccount;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.btnLogin);
        createAccount = findViewById(R.id.btnCreateAccountLogin);
        username = findViewById(R.id.usernameLogin);
        userPassword = findViewById(R.id.userPasswordLogin);

        DB = new DBHelper(this);
        //Setup a boolean function here to evaluate whether login was successful or not
        //make sure that if boolean isn't successful a Toast alert pops up
        //upon successful login, redirect to calendar view
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTXT = username.getText().toString();
                String passwordTXT = userPassword.getText().toString();

                Boolean verifyUserLogin = DB.authenticateUsernamePassword(usernameTXT,passwordTXT);
                if(verifyUserLogin){
                    Toast.makeText(LoginPage.this,"Welcome " + usernameTXT + "!",Toast.LENGTH_SHORT).show();
                    Intent intentLoginPage = new Intent(LoginPage.this,Calendar.class);

                    //sends username to calendar page for further use
                    intentLoginPage.putExtra("username", usernameTXT);
                    startActivity(intentLoginPage);
                } else{
                    Toast.makeText(LoginPage.this,"Account not found",Toast.LENGTH_SHORT).show();
                }

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreateAccount = new Intent(LoginPage.this,OrgnzrMainPage.class);
                startActivity(intentCreateAccount);




            }
        });
    }
}
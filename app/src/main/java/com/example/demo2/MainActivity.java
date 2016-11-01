package com.example.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText txtUsername,txtPassword;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new UserSessionManager(getApplicationContext());

        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

        Toast.makeText(getApplicationContext(),"User Login Status: " + sessionManager.isUserLoggedIn(),Toast.LENGTH_LONG).show();

        if(sessionManager.isUserLoggedIn()){
            HashMap<String, String> user = sessionManager.getUserDetails();
            txtUsername.setText(user.get("name"));
        }

        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if(username.trim().length() > 0 && password.trim().length() > 0){
            if(username.equals("admin") && password.equals("admin")){
                sessionManager.createUserLoginSessions(username);

                Intent i = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(i);
                finish();
            }else{

                // username / password doesn't match&
                Toast.makeText(getApplicationContext(),
                        "Username/Password is incorrect",
                        Toast.LENGTH_LONG).show();

            }
        }else {

            // user didn't entered username or password
            Toast.makeText(getApplicationContext(),
                    "Please enter username and password",
                    Toast.LENGTH_LONG).show();

        }
    }
}

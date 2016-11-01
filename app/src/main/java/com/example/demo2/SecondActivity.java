package com.example.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    UserSessionManager sessionManager;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        sessionManager = new UserSessionManager(getApplicationContext());

        TextView lblName = (TextView)findViewById(R.id.lblName);
        Button btnLogout = (Button)findViewById(R.id.btnLogout);

        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManager.isUserLoggedIn(),Toast.LENGTH_LONG).show();

        if(sessionManager.checkLogin())
            finish();

        HashMap<String, String> user = sessionManager.getUserDetails();
        String name = user.get("name");
        lblName.setText(name);

        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        sessionManager.logoutUser();
    }
}

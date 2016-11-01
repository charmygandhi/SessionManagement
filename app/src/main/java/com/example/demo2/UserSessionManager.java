package com.example.demo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Charmy PC on 2016-10-31.
 */

public class UserSessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    /**
     * Constructor
     * @param context
     */
    public UserSessionManager(Context context)
    {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences("My_Preferences",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * Creates login session
     * @param name
     */
    public void createUserLoginSessions(String name)
    {
        //storing login value as True in shared preference
        editor.putBoolean("IsUserLoggedIn",true);

        //storing name in shared preference
        editor.putString("name",name);
        editor.commit();
    }

    /**
     * It will check user login status
     * @return
     */
    public boolean checkLogin()
    {
        //if user is not logged in redirect them to main login page
        if(!this.isUserLoggedIn()){
            Intent i = new Intent(_context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return true;
        }
        return false;
    }

    //HashMap is common way to collect and retrieve data
    public HashMap<String,String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String,String> user = new HashMap<String,String>();

        // user name
        user.put("name", sharedPreferences.getString("name", null));

        // return user
        return user;
    }

    /**
     * It clears session details
     */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    /**
     * It checks for login
     * @return
     */
    public boolean isUserLoggedIn(){
        return sharedPreferences.getBoolean("IsUserLoggedIn", false);
    }
}

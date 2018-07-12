package com.example.jam.pandaroo_convo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ProfileActivity extends AppCompatActivity {

    User user;
    TextView balance;
    EditText editUsername;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get information from the intent
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("EXTRA_USER", 0);

        //create a new user (should query to database)
        user = new User(intValue);

        //Just to prevent the database from crashing
        if(user.getName() == null){
            user.setName("New User");
        }


        setContentView(R.layout.activity_profile);

        balance = (TextView) findViewById(R.id.txtBalance);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editName = (EditText) findViewById(R.id.editName);

        editName.setText(user.getName());
        editUsername.setText(user.getID() + "");
        balance.setText("Current Balance: " + user.getBalance());


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    public void viewProfile(View view) {
        Intent myIntent = new Intent(this, ProfileActivity.class);

        // This should come some previous page
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("EXTRA_USER", 0);

        myIntent.putExtra("EXTRA_USER", user.getID());

        startActivity(myIntent);
    }

    public void saveChanges(View view){
        //Get the changes


        user.setID(Integer.parseInt(editUsername.getText().toString()));
        user.setName(editName.getText().toString());

        //Save user object to database

    }
}

package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class InitialSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);


        //Set the toolbar to have an image
        android.support.v7.widget.Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.account_circle);
    }

    public void completeSurvey(View view) {
        Intent myIntent = new Intent(this, DiscussionActivity.class);
        startActivity(myIntent);
    }
}

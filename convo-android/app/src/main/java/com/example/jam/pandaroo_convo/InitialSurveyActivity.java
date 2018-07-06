package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InitialSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);
    }

    public void completeSurvey(View view) {
        Intent myIntent = new Intent(this, DiscussionActivity.class);
        startActivity(myIntent);
    }
}

package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PostSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_survey);
    }

    public void completePostSurvey(View view) {
        Intent myIntent = new Intent(this, FinishFocusActivity.class);
        startActivity(myIntent);
    }
}

package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Objects;
import java.util.ArrayList;


public class InitialSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        //Set the toolbar to have an image
        android.support.v7.widget.Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.account_circle);

        ArrayList<FocusGroup> FGData = FocusGroupGenerator.generateFocusGroups();

        SurveyAdapter adapter = new SurveyAdapter(FGData.get(1).getInitSurvey().getQuestions());

        RecyclerView recyclerView =
                (RecyclerView)findViewById(R.id.initSurveyView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);


    }

    public void completeSurvey(View view) {
        Intent myIntent = new Intent(this, FindGroupActivity.class);
        startActivity(myIntent);
    }
}

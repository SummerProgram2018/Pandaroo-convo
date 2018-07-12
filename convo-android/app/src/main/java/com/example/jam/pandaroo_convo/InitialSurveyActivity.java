package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jam.pandaroo_convo.discussion.DiscussionActivity;

import java.util.ArrayList;

public class InitialSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        ArrayList<FocusGroup> FGData = FocusGroupGenerator.generateFocusGroups();

        SurveyAdapter adapter = new SurveyAdapter(FGData.get(1).getInitSurvey().getQuestions());

        RecyclerView recyclerView =
                (RecyclerView)findViewById(R.id.initSurveyView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

    }

    public void completeSurvey(View view) {
        Intent myIntent = new Intent(this, DiscussionActivity.class);
        startActivity(myIntent);
    }
}

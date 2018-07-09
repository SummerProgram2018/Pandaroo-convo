package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class PostSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_survey);

        ArrayList<FocusGroup> FGData = FocusGroupGenerator.generateFocusGroups();

        SurveyAdapter adapter = new SurveyAdapter(FGData.get(1).getPostSurvey().getQuestions());

        RecyclerView recyclerView =
                (RecyclerView)findViewById(R.id.initSurveyView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void completePostSurvey(View view) {
        Intent myIntent = new Intent(this, FinishFocusActivity.class);
        startActivity(myIntent);
    }
}

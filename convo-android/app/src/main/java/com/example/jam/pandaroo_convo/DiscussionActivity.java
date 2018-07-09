package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DiscussionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
    }

    public void completeDiscussion(View view) {
        Intent myIntent = new Intent(this, FinishedChatActivity.class);
        startActivity(myIntent);
    }

}

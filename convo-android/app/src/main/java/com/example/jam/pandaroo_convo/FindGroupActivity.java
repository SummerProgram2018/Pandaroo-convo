package com.example.jam.pandaroo_convo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jam.pandaroo_convo.activity.MainActivity;

import static java.lang.Math.round;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FindGroupActivity extends AppCompatActivity {

    Button joinGroup;
    TextView timeRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_group);

        joinGroup = (Button) findViewById(R.id.joinGroup);
        joinGroup.setVisibility(View.GONE);

        timeRemaining = (TextView) findViewById(R.id.timeRemaining);

        //Create a count down of 10 seconds (should probably be longer in future)
        CountDownTimer timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeRemaining.setText(round(l/1000) + " seconds remaining");
            }

            @Override
            public void onFinish() {
                timeRemaining.setText("Countdown finished");
                findViewById(R.id.loading).setVisibility(View.GONE);
                //This is just filler later we can change this to check if a group has been found
                double a = Math.random();
                if(a >= 0.5){
                    //Group found show button
                    joinGroup.setVisibility(View.VISIBLE);
                }else{
                    //Group not found, go back to main activity
                    redirect(joinGroup.getRootView());
                }
            }
        };

        timer.start();

    }



    public void viewProfile(View view) {
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }

    public void startDiscussion(View view) {
        Intent myIntent = new Intent(this, DiscussionActivity.class);
        startActivity(myIntent);
    }

    public void redirect(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("EXTRA_REDIRECT", "redirect");
        startActivity(myIntent);
    }
}

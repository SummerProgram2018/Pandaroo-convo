package com.example.jam.pandaroo_convo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.jam.pandaroo_convo.activity.MainActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PayActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pay);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("EXTRA_USER", 0);

        User user = new User(intValue);

        int currentAmount = user.getBalance();

        int amount = mIntent.getIntExtra("EXTRA_PAY", 0);
        user.incrementBalance(amount);
        int newAmount = user.getBalance();



    }

    public void finish(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);

        startActivity(myIntent);
    }

}

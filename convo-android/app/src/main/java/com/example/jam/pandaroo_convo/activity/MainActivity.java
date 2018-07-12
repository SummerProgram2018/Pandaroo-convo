package com.example.jam.pandaroo_convo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.jam.pandaroo_convo.ExpandableListAdapter;
import com.example.jam.pandaroo_convo.FocusGroupListChangeListener;
import com.example.jam.pandaroo_convo.InitialSurveyActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.jam.pandaroo_convo.ExpandableListAdapter;
import com.example.jam.pandaroo_convo.InitialSurveyActivity;
import com.example.jam.pandaroo_convo.R;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, List<String>> listDataChild;
    ArrayList<String> listDataHeader;
    Integer userID = 1;
    Integer focus_group = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userID = getIntent().getExtras().getInt("userID");
        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
//        actionBar.setIcon(R.drawable.ic_account_box_black_24dp);
        actionBar.setHomeButtonEnabled(true);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
//        prepareListData();

        // setting list adapter
        expListView.setAdapter(listAdapter);
        try {
            DatabaseReference user = FirebaseDatabase.getInstance("https://convo-1522b.firebaseio.com").getReference().child("users").child(Integer.toString(userID));
            System.out.println(user.getKey());
            user.addListenerForSingleValueEvent(new FocusGroupListChangeListener(this, expListView));
//            user.addValueEventListener(new FocusGroupListChangeListener(this, expListView));
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataChild = new HashMap<>();
        listDataHeader = new ArrayList<>();

        // Adding child data
        listDataHeader.add("Smartphone Headphone Jacks");
        listDataHeader.add("Cooking Podcasts");
        listDataHeader.add("Robot Vacuum Cleaners");

        // Adding child data
        List<String> smartphone = new ArrayList<String>();
        smartphone.add("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo " +
                "ligula eget dolor. Aenean massa strong. Cum sociis natoque penatibus et magnis " +
                "dis parturient montes, nascetur ridiculus mus...");

        List<String> cooking = new ArrayList<String>();
        cooking.add("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo " +
                "ligula eget dolor. Aenean massa strong. Cum sociis natoque penatibus et magnis " +
                "dis parturient montes, nascetur ridiculus mus...");

        List<String> robot = new ArrayList<String>();
        robot.add("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo " +
                "ligula eget dolor. Aenean massa strong. Cum sociis natoque penatibus et magnis " +
                "dis parturient montes, nascetur ridiculus mus...");

        listDataChild.put(listDataHeader.get(0), smartphone); // Header, Child data
        listDataChild.put(listDataHeader.get(1), cooking);
        listDataChild.put(listDataHeader.get(2), robot);
    }

    public void joinFocusGroup(View view) {
        Intent myIntent = new Intent(this, InitialSurveyActivity.class);
        myIntent.putExtra("User_ID", userID);
        myIntent.putExtra("focus_group", focus_group);
        startActivity(myIntent);
    }

}

package com.example.jam.pandaroo_convo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.account_circle);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

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
        startActivity(myIntent);
    }
}

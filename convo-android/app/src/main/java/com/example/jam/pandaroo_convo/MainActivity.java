package com.example.jam.pandaroo_convo;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Dialog popup;
    TextView txtToolbar;
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

        txtToolbar =(TextView) mainToolBar.findViewById(R.id.toolbar_box);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //creating a popup for if no group was found

        popup = new Dialog(this);
        String s = getIntent().getStringExtra("EXTRA_REDIRECT");
        if(s!=null){
            createPopup();
        }
    }

    /*
    Creating a popup for if no group was found
     */
    private void createPopup(){
        //Creating the close box
        TextView txtclose;
        popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        popup.setContentView(R.layout.activity_no_group_found_popup);
        txtclose =(TextView) popup.findViewById(R.id.txtclose);
        txtclose.setText("X");
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        Window window = popup.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        popup.show();
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

    public void viewProfile(View view) {
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }
}

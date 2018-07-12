package com.example.jam.pandaroo_convo.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.jam.pandaroo_convo.ExpandableListAdapter;
import com.example.jam.pandaroo_convo.FocusGroupListChangeListener;
import com.example.jam.pandaroo_convo.InitialSurveyActivity;
import com.example.jam.pandaroo_convo.ProfileActivity;
import com.example.jam.pandaroo_convo.User;
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
    Dialog popup;
    TextView txtToolbar;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, List<String>> listData;
    Integer userID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.account_circle);

        txtToolbar =(TextView) mainToolBar.findViewById(R.id.toolbar_box);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listData = new HashMap<>();
        try {
            DatabaseReference user = FirebaseDatabase.getInstance().getReference().child("users").child(Integer.toString(userID));
            user.addListenerForSingleValueEvent(new FocusGroupListChangeListener(listData));
            user.addValueEventListener(new FocusGroupListChangeListener(listData));
        }catch(Exception e){
        };
        listAdapter = new ExpandableListAdapter(this, new ArrayList<>(listData.keySet()), listData);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //creating a popup for if no group was found

        popup = new Dialog(this);
        String s = getIntent().getStringExtra("EXTRA_REDIRECT");
        if(s!=null){
            createPopup();
        }

        listData = new HashMap<>();
        try {
            DatabaseReference user = FirebaseDatabase.getInstance().getReference().child("users").child(Integer.toString(userID));
            user.addListenerForSingleValueEvent(new FocusGroupListChangeListener(listData));
            user.addValueEventListener(new FocusGroupListChangeListener(listData));
        } catch(Exception e) {

        }
        listAdapter = new ExpandableListAdapter(this, new ArrayList<>(listData.keySet()), listData);
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
        listData = new HashMap<>();
    }


    public void joinFocusGroup(View view) {
        Intent myIntent = new Intent(this, InitialSurveyActivity.class);
        startActivity(myIntent);
    }

    public void viewProfile(View view) {
        Intent myIntent = new Intent(this, ProfileActivity.class);

        // This should come some previous page
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("EXTRA_USER", 0);

        User user = new User(4);
        myIntent.putExtra("EXTRA_USER", intValue);

        startActivity(myIntent);
    }
}

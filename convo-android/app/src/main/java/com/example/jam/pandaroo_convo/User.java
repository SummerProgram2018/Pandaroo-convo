package com.example.jam.pandaroo_convo;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class User {
    private int ID;
    private String name;
    private List<Integer> sessions;


    public User(int ID) {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

        // Ping firebase to grab user data. set when received data.
        ref.child("users").child(Integer.toString(ID)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                List<Object> sessions = dataSnapshot.child("focus_group").getValue(new Class<List<Object>>);
                for (int i = 0; i < sessions.size(); i++) {
                    if(sessions.get(i).toString().equals("True")) {
                        sess.add(i);
                    } else {
                        System.out.println(String.format("%s is not True", sessions.get(i).toString()));
                    }
                }
            }
        }
    }


}

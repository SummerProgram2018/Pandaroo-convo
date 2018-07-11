package com.example.jam.pandaroo_convo;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FocusGroupListChangeListener implements ValueEventListener {
    private HashMap<String, List<String>> listing;

    public FocusGroupListChangeListener(HashMap<String, List<String>> listing) {
        this.listing = listing;
        this.listing.clear();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        ArrayList<String> focus_groups = dataSnapshot.child("focus_group").getValue(new GenericTypeIndicator<ArrayList<String>>());
        for(int i = 0; i < focus_groups.size(); i++) {
            dataSnapshot.getRef().getRoot().child(String.format("focus_groups/%d", i)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String description = dataSnapshot.child("description").getValue().toString();
                    Integer price = (Integer) dataSnapshot.child("price").getValue();
                    ArrayList<String> details = new ArrayList<>();
                    details.add(description);
                    details.add(Integer.toString(price));
                    listing.put(name, details);
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }
}


package com.example.jam.pandaroo_convo;

import android.support.annotation.NonNull;
import android.widget.ExpandableListView;

import com.example.jam.pandaroo_convo.activity.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class FocusGroupListChangeListener implements ValueEventListener {
    private final ExpandableListView listView;
    private final MainActivity mainActivity;
    private HashMap<String, List<String>> listing;

    private MainActivityInterface mainActivityInterface;
    public FocusGroupListChangeListener(MainActivity mainActivity, ExpandableListView listing) {
        this.listView = listing;
        this.listing.clear();
        this.mainActivity = mainActivity;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        ArrayList<String> focus_groups = (ArrayList<String>) dataSnapshot.child("focus_groups").getValue();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + focus_groups.toString());
        for(int i = 0; i < focus_groups.size(); i++) {
            dataSnapshot.getRef().getRoot().child(String.format("focus_groups/%d", i)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String description = dataSnapshot.child("description").getValue().toString();
                    Integer price =  Integer.parseInt((String) dataSnapshot.child("price").getValue());
                    ArrayList<String> details = new ArrayList<>();
                    details.add(description);
                    details.add(Integer.toString(price));
                    listing.put(name, details);
                    System.out.println("New adapter set.");
                    listView.setAdapter(new ExpandableListAdapter(mainActivity, new ArrayList<String>(listing.keySet()), listing));
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^" + databaseError.toString());
                }
            });
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^" + databaseError.toString());
    }
}


package com.example.jam.pandaroo_convo;

import android.support.annotation.NonNull;
import android.widget.ExpandableListView;

import com.example.jam.pandaroo_convo.activity.MainActivity;
import com.example.jam.pandaroo_convo.entity.FocusGroupListData;
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
    private HashMap<String, FocusGroupListData> listing;

    private MainActivityInterface mainActivityInterface;
    public FocusGroupListChangeListener(MainActivity mainActivity, ExpandableListView listing) {
        this.listView = listing;
        this.mainActivity = mainActivity;
        this.listing = new HashMap<>();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        ArrayList<String> focus_groups = (ArrayList<String>) dataSnapshot.child("focus_groups").getValue();
        System.out.println(focus_groups.toString());
        for(int i = 0; i < focus_groups.size(); i++) {
            System.out.println(focus_groups.get(i) == null);
            if (focus_groups.get(i) != null) {
                dataSnapshot.getRef().getRoot().child(String.format("focus_groups/%d", i)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("name").getValue().toString();
                        String description = dataSnapshot.child("description").getValue().toString();
                        Integer price = Integer.parseInt((String) dataSnapshot.child("price").getValue());
                        FocusGroupListData entry = new FocusGroupListData(description, price);
                        listing.put(name, entry);
                        listView.setAdapter(new ExpandableListAdapter(mainActivity, new ArrayList<String>(listing.keySet()), listing));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^" + databaseError.toString());
                    }
                });
            }
        }
        listView.setAdapter(new ExpandableListAdapter(mainActivity, new ArrayList<String>(this.listing.keySet()), this.listing));

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^" + databaseError.toString());
    }
}


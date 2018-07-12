package com.example.jam.pandaroo_convo.entity;

import com.example.jam.pandaroo_convo.FocusGroup;

public class FocusGroupListData {
    private String description;
    private Integer price;
    public FocusGroupListData(String description, Integer price) {
        this.description = description;
        this.price = price;
    }

    public Integer getPrice(){
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }
}

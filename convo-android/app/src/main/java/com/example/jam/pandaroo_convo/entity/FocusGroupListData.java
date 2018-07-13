package com.example.jam.pandaroo_convo.entity;

import com.example.jam.pandaroo_convo.FocusGroup;

public class FocusGroupListData {
    private String description;
    private Integer price;
    private Integer FG;
    public FocusGroupListData(String description, Integer price, Integer FG) {
        this.description = description;
        this.price = price;
        this.FG = FG;
    }

    public Integer getPrice(){
        return this.price;
    }

    public Integer getFG(){
        return this.FG;
    }

    public String getDescription() {
        return this.description;
    }
}

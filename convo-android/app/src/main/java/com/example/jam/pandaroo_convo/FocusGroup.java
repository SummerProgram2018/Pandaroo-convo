package com.example.jam.pandaroo_convo;

import java.util.ArrayList;

public class FocusGroup {

    private String name;
    private String id;
    private int groupSize;
    private int noGroups;
    private Survey initSurvey;
    private Survey postSurvey;
    private ArrayList<Topic> topics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getNoGroups() {
        return noGroups;
    }

    public void setNoGroups(int noGroups) {
        this.noGroups = noGroups;
    }

    public Survey getInitSurvey() {
        return initSurvey;
    }

    public void setInitSurvey(Survey initSurvey) {
        this.initSurvey = initSurvey;
    }

    public Survey getPostSurvey() {
        return postSurvey;
    }

    public void setPostSurvey(Survey postSurvey) {
        this.postSurvey = postSurvey;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }
}

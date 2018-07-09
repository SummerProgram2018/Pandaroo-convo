package com.example.jam.pandaroo_convo;

import java.util.ArrayList;

public class FocusGroupGenerator {

    public static ArrayList<FocusGroup> generateFocusGroups() {
        ArrayList<FocusGroup> focusGroups = new ArrayList<>();

        focusGroups.add(new FocusGroup("Smartphone 3.5mm Jacks", "1", 8, 10));
        focusGroups.add(new FocusGroup("Cooking Podcasts", "2", 8, 10));
        focusGroups.add(new FocusGroup("Robot Vacuum Cleaners", "3", 8, 10));

        ArrayList<Question> initQuestions = new ArrayList<>();

        initQuestions.add(new Question("What kind of products are you interested in?","multiline"));
        initQuestions.add(new Question("How open are you to spending money on brand new technology?","multiline"));
        initQuestions.add(new Question("How do you feel about our brand?","multiline"));

        ArrayList<Question> postQuestions = new ArrayList<>();

        postQuestions.add(new Question("Did this discussion change your mind on anything?", "multiline"));
        postQuestions.add(new Question("How did you feel about your Convo experience?", "multiline"));
        postQuestions.add(new Question("How do you now feel about our brand?", "multiline"));

        ArrayList<Topic> topics = new ArrayList<>();

        topics.add(new Topic("Topic 1", "This is a description."));
        topics.add(new Topic("Topic 2", "This is a description."));
        topics.add(new Topic("Topic 3", "This is a description."));

        for (int group = 0; group < focusGroups.size(); group++) {
            focusGroups.get(group).getInitSurvey().setQuestions(initQuestions);
            focusGroups.get(group).getPostSurvey().setQuestions(postQuestions);
            focusGroups.get(group).setTopics(topics);
        }

        return focusGroups;

    }
}

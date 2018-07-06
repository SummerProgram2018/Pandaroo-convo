package com.example.jam.pandaroo_convo;

class Question {

    private String question;
    private String responseType;

    public Question(String question, String responseType) {
        this.question = question;
        this.responseType = responseType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}

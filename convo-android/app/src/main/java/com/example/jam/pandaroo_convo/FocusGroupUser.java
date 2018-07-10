package com.example.jam.pandaroo_convo;

import java.util.Date;

public class FocusGroupUser {
    private String username;
    private int balance;
    private Date dateOfBirth;
    private String gender;


    public void addToBalance(int amount){
        if(amount > 0){
            balance += amount;
        }
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth){
        Date date = new Date();
        if(dateOfBirth.compareTo(date) < 0){
            this.dateOfBirth = dateOfBirth;
        }
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        if(gender.equals("male")||gender.equals("female")||gender.equals("non-binary")){
            this.gender = gender;
        }
    }
}

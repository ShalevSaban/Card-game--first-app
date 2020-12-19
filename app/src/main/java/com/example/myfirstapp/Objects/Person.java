package com.example.myfirstapp.Objects;

import android.location.Address;

import java.util.Map;

public class Person {
    protected String name;
    protected int score;
    protected Card card;
    protected Address location;

    public Person(String name) {
        this.name = name;
        this.score =0;
    }

    public Card getCard() {
        return card;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCardValue() {
        return card.getNumber();
    }

    public String getCardShape(){
        return card.shape.toString();
    }

    public void setCard(Card card) {
        this.card = card;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void updateScore() {
        score++;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return  "Name: "+name+"\nScore: "+score;
    }
}

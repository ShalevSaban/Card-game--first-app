package com.example.myfirstapp;

public class Person {
    protected String name;
    protected int score;
    protected Card card;

    public Person(String name) {
        this.name = name;
        this.score =0;
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
}

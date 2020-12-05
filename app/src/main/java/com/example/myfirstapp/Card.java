package com.example.myfirstapp;

public class Card {
    protected int number;
    protected  Shape shape;

    public Card(int number,Shape shape) {
        this.number = number;
        this.shape=shape;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

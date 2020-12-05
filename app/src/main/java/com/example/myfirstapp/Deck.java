package com.example.myfirstapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    public static final int BIGGEST_CARD_VALUE=13;
    protected   int numOfCards;
    protected ArrayList<Card> cards;
    protected java.util.List<Shape> shapes =
            Collections.unmodifiableList(Arrays.asList(Shape.values()));
    protected String deckName;


    public Deck(String deckName) {
        this.deckName=deckName;
        cards= new ArrayList<>();
        generateDeck();
        numOfCards=getNumOfCards();
    }

    public void generateDeck(){
        for(int i=1;i<=BIGGEST_CARD_VALUE;i++){
            for(int j=0;j<shapes.size();j++){
                Card card=new Card(i,shapes.get(j));
                cards.add(card);
            }
        }
    }

    public int getNumOfCards(){
        return cards.size();
    }

    public Card getRandomCard(){
        Random random = new Random();
        int cardLocation=random.nextInt(getNumOfCards());
        Card randomCard=new Card(cards.get(cardLocation).getNumber(),cards.get(cardLocation).getShape());
        cards.remove(cardLocation);
        return randomCard;
    }


}

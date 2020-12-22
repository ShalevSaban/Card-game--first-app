package com.example.myfirstapp.Objects;

import java.util.Date;
import java.util.Objects;

public class Record {
    private Person person;
    private Date date;

    public Record(Person person, Date date) {
        this.person = person;
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return person+"\nDate: "+date+"\n ________________________________________";
    }

    @Override
    public boolean equals(Object o) {
        Record anotherRecord=(Record)o;
        if(anotherRecord.getPerson().getScore()==person.getScore())
            return true;
        else
            return false;
    }

    public int compareTo(Record another){
        if(another.getPerson().getScore()>this.getPerson().getScore())
            return -1;
        else return 1;
    }



}

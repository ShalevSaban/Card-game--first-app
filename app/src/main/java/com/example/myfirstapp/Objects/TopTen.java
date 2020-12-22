package com.example.myfirstapp.Objects;

import android.renderscript.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopTen {
    protected ArrayList<Record> records=new ArrayList<>();
    protected String name;
    public TopTen(String name) {
        this.name = name;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public TopTen setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

    public Person getPlayer(int num){
        return records.get(num).getPerson();
    }

    public int getNumOfPlayers(){
      return  records.size();
    }


    public void addRecord(Record record){
        records.add(record);
    }

    @Override
    public String toString() {
        return
                records.toString();
    }

    public ArrayList<String> returnToStringList(){
        ArrayList<String>newList=new ArrayList<>();
        for(int i=0;i<records.size();i++){
            newList.add(records.get(i).toString());
        }
        return newList;
    }

    public  TopTen sortArray() {
        for (int j = 1; j < records.size(); j++) {
            Record current = records.get(j);
            int i = j-1;
            while ((i > -1) && ((records.get(i).compareTo(current)) == 1)) {
                records.set(i+1,records.get(i));
                i--;
            }
            records.set(+1, current);
        }
        TopTen returnList=new TopTen("new");
        int numOfRecords=records.size()-1;
        for(int k=numOfRecords;k>numOfRecords-10;k--) {
            if(records.get(k)!=null)
            returnList.addRecord(records.get(k));
        }
        return  returnList;
    }




}


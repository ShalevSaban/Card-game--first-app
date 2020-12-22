package com.example.myfirstapp.Objects;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.util.Log;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MySharedPref {
    private static MySharedPref instance;
    private static final String KEY="topTenList";
    private SharedPreferences prefs;
    protected TopTen topTenList=new TopTen("topList");
    private Gson json=new Gson();

    private MySharedPref(Context context) {
        prefs = context.getSharedPreferences("MY_SP", Context.MODE_PRIVATE);
    }

    public static MySharedPref getInstance() {
        return instance;
    }

    public static void init(Context context){
        if(instance==null)
            instance=new MySharedPref(context);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public void addPlayerName(String name){
        String listStr=getString(KEY,"N/A");
        if(json.fromJson(listStr,TopTen.class)!=null)
        topTenList=json.fromJson(listStr,TopTen.class);
        Person player=new Person(name);
        Record personRecord=new Record(player, Calendar.getInstance().getTime());
        topTenList.addRecord(personRecord);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY,new Gson().toJson(topTenList));
        editor.apply();
    }

    public void updateScore(int scoreLeft,int scoreRight){
        ArrayList<Record>list;
        SharedPreferences.Editor editor = prefs.edit();
        String listStr=getString(KEY,"N/A");
        topTenList=json.fromJson(listStr,TopTen.class);
        list=topTenList.getRecords();
        list.get(list.size()-1).getPerson().setScore(scoreRight);
        list.get(list.size()-2).getPerson().setScore(scoreLeft);
        topTenList.setRecords(list);
        removeKey(KEY);
        editor.putString(KEY,json.toJson(topTenList));
        editor.apply();
    }

    public TopTen getTopTen(){
        SharedPreferences.Editor editor = prefs.edit();
      return json.fromJson(prefs.getString(KEY,"N/A"),TopTen.class);
    }



    public void removeKey(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public void updateLocation(Address address){
        ArrayList<Record>list;
        SharedPreferences.Editor editor = prefs.edit();
        String listStr=getString(KEY,"N/A");
        topTenList=json.fromJson(listStr,TopTen.class);
        list=topTenList.getRecords();
        Log.d("DATA","update loc get list"+list.toString());
        list.get(list.size()-1).getPerson().setLocation(address);
        list.get(list.size()-2).getPerson().setLocation(address);
        Log.d("DATA","get player at location"+list.get(list.size()-1).getPerson().getLocation());
        topTenList.setRecords(list);
        removeKey(KEY);
        editor.putString(KEY,json.toJson(topTenList));
        editor.apply();
    }
}

package com.example.myfirstapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfirstapp.Objects.MySharedPref;
import com.example.myfirstapp.Objects.Record;
import com.example.myfirstapp.Objects.TopTen;
import com.example.myfirstapp.R;

import java.util.ArrayList;

public class TopTenActivity extends AppCompatActivity {
    ListView lv;
    Button menuBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_top_ten);
        lv = (ListView) findViewById(R.id.topTen_LST_listView);
        menuBTN = (Button) findViewById(R.id.topTen_BTN_menu);
        TopTen topTenlist = new TopTen("list");
        topTenlist = MySharedPref.getInstance().getTopTen().sortArray();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topTenlist.returnToStringList()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                return view;
            }
            };

        lv.setAdapter(arrayAdapter);
        menuBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            openMenu();
            }
        });
    }

    private void openMenu() {
        Intent myIntent = new Intent(this, MenuActivity.class);
        startActivity(myIntent);
        finish();
    }
}
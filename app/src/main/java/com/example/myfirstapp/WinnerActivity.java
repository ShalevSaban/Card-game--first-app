package com.example.myfirstapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        ImageView winnerPicture=findViewById(R.id.winner_IMG_picture);

        String WinnerName=getIntent().getStringExtra("nameOfWinner");
        if( WinnerName.equals("man")){
       winnerPicture.setImageResource(R.drawable.man);
        }
        else
            winnerPicture.setImageResource(R.drawable.woman);

    }
}
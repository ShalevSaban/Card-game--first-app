package com.example.myfirstapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;

public class WinnerActivity extends AppCompatActivity {
    Button topTenBTN;
    ImageView winnerPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        winnerPicture = findViewById(R.id.winner_IMG_picture);
        String winnerName = getIntent().getStringExtra("nameOfWinner");
        checkWinner(winnerName);
        topTenBTN = findViewById(R.id.Winner_BTN_TopTen);
        topTenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopTen();
            }
        });
    }

    private void openTopTen() {
        Intent myIntent = new Intent(this, TopTenActivity.class);
        startActivity(myIntent);
        finish();
    }

    public void checkWinner(String intent) {
        if (intent.equals("Man")) {
            winnerPicture.setImageResource(R.drawable.woman_pink);
        } else {
            if (intent.equals("Woman"))
                winnerPicture.setImageResource(R.drawable.man_yellow);
            else
                winnerPicture.setImageResource(R.drawable.question);
        }
    }
}

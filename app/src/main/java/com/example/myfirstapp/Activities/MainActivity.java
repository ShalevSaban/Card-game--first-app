  package com.example.myfirstapp.Activities;

  import android.content.Intent;
  import android.media.MediaPlayer;
  import android.os.Bundle;
  import android.os.Handler;
  import android.util.Log;
  import android.view.View;
  import android.widget.ImageView;
  import android.widget.LinearLayout;
  import android.widget.ProgressBar;
  import android.widget.Spinner;
  import android.widget.TextView;
  import android.widget.Toast;

  import androidx.appcompat.app.AppCompatActivity;

  import com.bumptech.glide.Glide;
  import com.example.myfirstapp.Objects.Deck;
  import com.example.myfirstapp.Objects.MySharedPref;
  import com.example.myfirstapp.Objects.Person;
  import com.example.myfirstapp.Objects.Record;
  import com.example.myfirstapp.R;

  import java.util.Arrays;
  import java.util.Calendar;
  import java.util.Collections;
  import java.util.List;

  public class MainActivity extends AppCompatActivity {
      enum Shape{club,diamond,spade,heart}
      private static final List<Shape> shapes =
              Collections.unmodifiableList(Arrays.asList(Shape.values()));
      private static final int SHAPES_SIZE = shapes.size();
      private  Handler mHandler =new Handler();
      ImageView cardLeft,cardRight,playBTN;
      LinearLayout mainLayout;
      TextView  scoreLeft,scoreRight;
      ProgressBar spinner;
      int progressBarTime=1;




      @Override
         protected void onCreate (Bundle savedInstanceState){
             Log.d("situations","onCreate");
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
             Intent intent=getIntent();
             String[] playersName =intent.getStringArrayExtra("players");
             cardLeft=findViewById(R.id.main_IMG_cardLeft);
             cardRight=findViewById(R.id.main_IMG_cardRight);
             scoreLeft=findViewById(R.id.main_LBL_scoreLeft);
             scoreRight=findViewById(R.id.main_LBL_scoreRight);
             playBTN=findViewById(R.id.main_IMG_play);
             spinner=findViewById(R.id.main_PRG_progressBar);
             mainLayout=findViewById(R.id.Main_layout);
            Glide.with(this).load(R.drawable.play_button_white).into(playBTN);
          final MediaPlayer mp = MediaPlayer.create(this, R.raw.snd_boxing);
             playBTN.setOnClickListener (new View.OnClickListener(){
                 public void onClick(View v) {
                     Log.d("situations","checkClick");
                     mp.start();
                     startRepeating();
                }
             });


         }
         @Override
         protected void onStop() {
             super.onStop();
             mHandler.removeCallbacks(playTimer);
         }

      public void playGame(Deck gameDeck, Person personLeftPlay, Person personRightPlay){
          String cardLeft,cardRight;
          personLeftPlay.setCard(gameDeck.getRandomCard());
          personRightPlay.setCard(gameDeck.getRandomCard());
          if(personLeftPlay.getCardValue()>personRightPlay.getCardValue())
              personLeftPlay.updateScore();
          if(personRightPlay.getCardValue()>personLeftPlay.getCardValue())
              personRightPlay.updateScore();
          if(personRightPlay.getCardValue()==personLeftPlay.getCardValue())
              Toast.makeText(MainActivity.this, "WAR!", Toast.LENGTH_SHORT).show();
      cardLeft=personLeftPlay.getCardShape()+personLeftPlay.getCardValue();
      cardRight=personRightPlay.getCardShape()+personRightPlay.getCardValue();
      updateView(cardLeft,cardRight,personLeftPlay.getScore(),personRightPlay.getScore());
      }


      private void checkTheWinner(Person personLeft,Person personRight) {
          stopRepeating();
             if(personLeft.getScore()==personRight.getScore()) {
                 openSecondActivity("Draw");
             }
             if(personLeft.getScore()>personRight.getScore()) {
                 openSecondActivity("Man");
             }
             else
               openSecondActivity("Woman");

      }

      protected void updateView(String cardLeftView, String cardRightView,int leftScore,int rightScore) {
      int cardLeftResource=getResources().getIdentifier(cardLeftView,"drawable",getPackageName());
      int cardRightResource=getResources().getIdentifier(cardRightView,"drawable",getPackageName());
      scoreLeft.setText(""+leftScore);
      scoreRight.setText(""+rightScore);
      cardLeft.setImageResource(cardLeftResource);
      cardRight.setImageResource(cardRightResource);
      progressBarTime++;
      spinner.setProgress(progressBarTime*4);
      }

      private void openSecondActivity(String nameOfWinner) {
          Intent myIntent=new Intent(this, WinnerActivity.class);
         if (nameOfWinner.equals("Man")){
             myIntent.putExtra("nameOfWinner", "Man");
         }
         else {
             if(nameOfWinner.equals("Woman"))
             myIntent.putExtra("nameOfWinner", "Woman");
             else{
                 myIntent.putExtra("nameOfWinner", "Draw");
             }
         }
          startActivity(myIntent);
          finish();
      }

public void startRepeating(){
 playTimer.run();
}

public void stopRepeating(){
    mHandler.removeCallbacks(playTimer);
}

private Runnable playTimer=new Runnable() {
    Deck gameDeck=new Deck("myDeck");
//    Person personLeft=getPersonLeftFromIntent();
//    Person personRight=getPersonRightFromIntent();
    Person personLeft=new Person("person left");
    Person personRight=new Person("person right");
    @Override
    public void run() {
        playGame(gameDeck, personLeft, personRight);
        if(gameDeck.getNumOfCards()==0) {
            MySharedPref.getInstance().updateScore(personLeft.getScore(),personRight.getScore());
            checkTheWinner(personLeft, personRight);
        }
        mHandler.postDelayed(this,1000);
    }
};








}
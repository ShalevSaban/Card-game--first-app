  package com.example.myfirstapp;

  import android.content.Intent;
  import android.os.Bundle;
  import android.util.Log;
  import android.view.View;
  import android.widget.ImageView;
  import android.widget.TextView;
  import android.widget.Toast;

  import androidx.appcompat.app.AppCompatActivity;

  import java.util.Arrays;
  import java.util.Collections;
  import java.util.List;
  import java.util.Random;

  public class MainActivity extends AppCompatActivity {
      enum Shape{club,diamond,spade,heart}
      private static final List<Shape> shapes =
              Collections.unmodifiableList(Arrays.asList(Shape.values()));
      private static final int SHAPES_SIZE = shapes.size();

ImageView cardLeft,cardRight,playBTN;
TextView  scoreLeft,scoreRight;


         @Override
         protected void onCreate (Bundle savedInstanceState){
             Log.d("situations","onCreate");
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
             cardLeft=findViewById(R.id.main_IMG_cardLeft);
             cardRight=findViewById(R.id.main_IMG_cardRight);
             scoreLeft=findViewById(R.id.main_LBL_scoreLeft);
             scoreRight=findViewById(R.id.main_LBL_scoreRight);
             playBTN=findViewById(R.id.main_IMG_play);

             playBTN.setOnClickListener (new View.OnClickListener(){
                 Deck gameDeck=new Deck("myDeck");
                 Person personLeft=new Person("David");
                 Person personRight=new Person("Moshe");
                 public void onClick(View v) {
                     Log.d("situations","checkClick");
                     if(gameDeck.getNumOfCards()==0)
                         checkTheWinner(personLeft.getScore(),personRight.getScore());
                     else
                      playGame(gameDeck,personLeft,personRight);
                }
             });


         }

      public void playGame(Deck gameDeck,Person personLeftPlay,Person personRightPlay){
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


      private void checkTheWinner(int personLeftScore,int personRightScore) {
             if(personLeftScore==personRightScore) {
                 openSecondActivity("draw");
             }
             if(personLeftScore>personRightScore) {
                 openSecondActivity("man");
             }
             else
               openSecondActivity("Woman");

      }

      private void updateView(String cardLeftView, String cardRightView,int leftScore,int rightScore) {
      int cardLeftResource=getResources().getIdentifier(cardLeftView,"drawable",getPackageName());
      int cardRightResource=getResources().getIdentifier(cardRightView,"drawable",getPackageName());
      scoreLeft.setText(""+leftScore);
      scoreRight.setText(""+rightScore);
      cardLeft.setImageResource(cardLeftResource);
      cardRight.setImageResource(cardRightResource);
      }

      private void openSecondActivity(String nameOfWinner) {
          Intent myIntent=new Intent(this,WinnerActivity.class);
         if (nameOfWinner.equals("man")){
             myIntent.putExtra("nameOfWinner", "man");
         }
         else {
             myIntent.putExtra("nameOfWinner", "woman");
         }
          startActivity(myIntent);
          finish();
      }





}
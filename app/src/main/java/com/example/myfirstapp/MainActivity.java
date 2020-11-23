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
      enum Shape{club,diamond,spade,heart;}
      private static final List<Shape> shapes =
              Collections.unmodifiableList(Arrays.asList(Shape.values()));
      private static final int SHAPES_SIZE = shapes.size();

int countScoreLeft=0,countScoreRight=0 ;
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

             Random random = new Random();
             playBTN.setOnClickListener (new View.OnClickListener(){

                 public void onClick(View v) {
                     Log.d("situations","checkClick");
                     int leftCardNum,shapeLeft,shapeRight,rightCardNum,leftImage,rightImage;
                      rightCardNum= random.nextInt(13)+1;
                      leftCardNum= random.nextInt(13)+1;
                     if (leftCardNum>rightCardNum){
                      countScoreLeft++;
                      scoreLeft.setText(String.valueOf(countScoreLeft));
                     }
                     else{
                         if(rightCardNum>leftCardNum){
                             countScoreRight++;
                             scoreRight.setText(String.valueOf(countScoreRight));
                         }
                         else {
                             Toast.makeText(MainActivity.this, "WAR!", Toast.LENGTH_SHORT).show();
                             countScoreRight++;
                             countScoreLeft++;
                         }
                     }

                     shapeLeft=random.nextInt(SHAPES_SIZE-1)+1;
                     shapeRight=random.nextInt(SHAPES_SIZE-1)+1;
                     leftImage=getResources().getIdentifier(shapes.get(shapeLeft)+""+leftCardNum,"drawable",getPackageName());
                     cardLeft.setImageResource(leftImage);
                     rightImage=getResources().getIdentifier(shapes.get(shapeRight)+""+rightCardNum,"drawable",getPackageName());
                     cardRight.setImageResource(rightImage);


                     if(((countScoreLeft+countScoreRight)>50)&&(countScoreLeft!=countScoreRight)){
                         Log.d("situations","check");
                         if(countScoreRight>countScoreLeft)
                             openSecondActivity("woman");
                         else
                             openSecondActivity("man");
                     }
                 }


             });


         }



      private void openSecondActivity(String nameOfWinner) {
          Intent myIntent=new Intent(this,WinnerActivity.class);
         if (nameOfWinner.equals("man")) {
              myIntent.putExtra("nameOfWinner", "man");
         }
          else {
              myIntent.putExtra("nameOfWinner", "woman");
         }
          startActivity(myIntent);
          finish();
      }





}
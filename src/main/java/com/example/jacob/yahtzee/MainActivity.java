package com.example.jacob.yahtzee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int diceAmount = 5;
    private int diceVal = 0;
    ImageView dice1;
    ImageView dice2;
    ImageView dice3;
    ImageView dice4;
    ImageView dice5;

    Thread[] diceThreads;
    Dice[] diceMoveArray;
    ImageView[] dices;
    Thread outputImages;
    TextView scoreOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);

        scoreOut = findViewById(R.id.score);

        Button roll = findViewById(R.id.rollBtn);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceSetup();
                for(int i = 0; i < diceAmount; i++){
                    diceThreads[i].start();
                }
                //scoreThread.start();
                outputImages.start();
            }
        });


    }

    private void diceSetup(){
        outputImages = new Thread(this.outputImages);
        diceMoveArray = new Dice[diceAmount];
        diceMoveArray[0] = new Dice();
        diceMoveArray[1] = new Dice();
        diceMoveArray[2] = new Dice();
        diceMoveArray[3] = new Dice();
        diceMoveArray[4] = new Dice();

        dices = new ImageView[diceAmount];
        dices[0] = dice1;
        dices[1] = dice2;
        dices[2] = dice3;
        dices[3] = dice4;
        dices[4] = dice5;

        diceThreads = new Thread[diceAmount];
        for(int i = 0; i < diceAmount; i++){
            diceThreads[i] = new Thread(diceMoveArray[i]);
        }
    }

    private void outputImages(){
        try{
            Thread.sleep(100);
        }catch(Exception ex){

        }

        for(int j = 0; j < 35; j++) {
            try{
                Thread.sleep(100);
            }catch(Exception ex) {}
            diceVal = 0;
            for (int i = 0; i < diceAmount; i++) {
                setImage(dices[i], diceMoveArray[i].getDiceVal());
                diceVal += diceMoveArray[i].getDiceVal();
            }
            scoreOut.setText("" + diceVal);
        }
    }

    private void setImage(ImageView img, int faceValue){
        if(faceValue == 1){
            img.setImageResource(R.drawable.Dice1);
        }else if (faceValue == 2){
            img.setImageResource(R.drawable.Dice2);
        }else if (faceValue == 3){
            img.setImageResource(R.drawable.Dice3);
        }else if (faceValue == 4){
            img.setImageResource(R.drawable.Dice4);
        }else if (faceValue == 5){
            img.setImageResource(R.drawable.Dice5);
        }else if (faceValue == 6){
            img.setImageResource(R.drawable.Dice6);
        }else{
            //System.out.println("Error");
        }
    }
}

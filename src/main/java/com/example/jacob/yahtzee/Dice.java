package com.example.jacob.yahtzee;

import java.util.Random;

public class Dice implements Runnable{
    Random rand = new Random();
    int randNum;

    public Dice(){ }

    @Override
    public void run() {
        for(int i = 0; i < 35; i++){
            randNum = rand.nextInt(6) + 1;
            try{
                Thread.sleep(100);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public int getDiceVal(){
        return randNum;
    }
}

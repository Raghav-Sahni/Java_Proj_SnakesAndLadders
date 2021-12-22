package com.example.snakes_and_ladders;

public class Snakes {
    public int checkSnake(Player p, int cir) {
        if(p.getXpos() == 360 && p.getYPos() == 680){
            p.setXPos(600);
            p.setYPos(680);
            cir -= 0;
        }
        if(p.getXpos() == 760 && p.getYPos() == 520){
            p.setXPos(280);
            p.setYPos(760);
            cir -= 3;
        }
        if(p.getXpos() == 520 && p.getYPos() == 440){
            p.setXPos(360);
            p.setYPos(600);
            cir -= 2;
        }
        if(p.getXpos() == 200 && p.getYPos() == 280){
            p.setXPos(40);
            p.setYPos(360);
            cir -= 1;
        }
        if(p.getXpos() == 440 && p.getYPos() == 280){
            p.setXPos(680);
            p.setYPos(360);
            cir -= 1;
        }
        if(p.getXpos() == 280 && p.getYPos() == 40){
            p.setXPos(440);
            p.setYPos(200);
            cir -= 2;
        }
        return cir;
    }
}

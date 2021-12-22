package com.example.snakes_and_ladders;

public class ladders {
    public int checkLadder(Player p, int cir){
        if(p.getXpos() == 200 && p.getYPos() == 760){
            p.setXPos(120);
            p.setYPos(520);
            cir += 3;
        }
        if(p.getXpos() == 760 && p.getYPos() == 760){
            p.setXPos(680);
            p.setYPos(680);
            cir += 1;
        }
        if(p.getXpos() == 520 && p.getYPos() == 600){
            p.setXPos(600);
            p.setYPos(360);
            cir += 3;
        }
        if(p.getXpos() == 40 && p.getYPos() == 280){
            p.setXPos(120);
            p.setYPos(40);
            cir += 3;
        }
        if(p.getXpos() == 360 && p.getYPos() == 360){
            p.setXPos(280);
            p.setYPos(120);
            cir += 3;
        }
        if(p.getXpos() == 680 && p.getYPos() == 200){
            p.setXPos(760);
            p.setYPos(120);
            cir += 1;
        }
        return cir;
    }
}

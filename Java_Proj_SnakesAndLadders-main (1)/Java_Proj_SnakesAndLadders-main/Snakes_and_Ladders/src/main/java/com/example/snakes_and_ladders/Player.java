package com.example.snakes_and_ladders;

public class Player {
    private int XPos;
    private int posCir;
    private int YPos;

    Player (int x, int y) {
        this.XPos = x;
        this.YPos = y;
        this.posCir = 1;
    }

    public int getXpos(){
        return this.XPos;
    }

    public int getYPos(){
        return this.YPos;
    }

    public int getPosCir(){ return this.posCir; }

    public void setPosCir(int p) {
        this.posCir = p;
    }

    public void setXPos(int p) {
        this.XPos = p;
    }

    public void setYPos(int p) {
        this.YPos = p;
    }

    public boolean move(int rand) {
        for (int i = 0; i < rand; i++) {
            if (this.posCir % 2 == 1) {
                this.XPos += 80;
            }
            if (this.posCir % 2 == 0) {
                this.XPos -= 80;
            }
            if (this.XPos > 760) {
                this.YPos -= 80;
                this.XPos -= 80;
                this.posCir++;
            }
            if (this.XPos < 40) {
                this.YPos -= 80;
                this.XPos += 80;
                this.posCir++;
            }
            if (this.XPos < 30 || this.YPos < 30) {
                this.XPos = 40;
                this.YPos = 40;
                return false;
            }
        }
        return true;
    }
}

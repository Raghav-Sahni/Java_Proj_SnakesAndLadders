package com.example.snakes_and_ladders;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

public class Dice  {
    public int roll() {
        return (int)(Math.random()*6+1);
    }
}

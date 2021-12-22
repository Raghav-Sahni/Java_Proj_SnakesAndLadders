package com.example.snakes_and_ladders;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Tile extends Rectangle {

    public Tile(int x, int y) {
        setWidth(x);
        setHeight(y);

        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }
}

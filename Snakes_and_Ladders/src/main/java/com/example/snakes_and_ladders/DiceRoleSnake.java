package com.example.snakes_and_ladders;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class DiceRoleSnake extends Application {

    public int rand;
    public Label randResult;

    public int cirPos[][] = new int [10][10];
    public int ladderPos[][] = new int [6][3];

    public static final int Tile_Size = 80;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int playerPosition1 = 1;
    public int PlayerPosition2 = 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1XPos = 40;
    public static int player1YPos = 760;

    public static int player2XPos = 40;
    public static int player2YPos = 760;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public boolean gameStart = false;
    public Button gameButton;

    private Group tileGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(width*Tile_Size, (height*Tile_Size)+80);
        root.getChildren().addAll(tileGroup);//Adds Tile group stuff in main stack pane

        for(int i =0;i< height;i++){//Setting the tiles i.e the bg rectangles
            for(int j=0;j<width;j++){
                Tile tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j*Tile_Size);
                tile.setTranslateY(i*Tile_Size);
                tileGroup.getChildren().add(tile);

                cirPos[i][j] = j*(Tile_Size - 40);//for x coords
            }
        }

        for (int i=0;i<height;i++){//Print x coords
            for (int j=0;j<width;j++){
                System.out.println(cirPos[i][j]+ " ");
            }
            System.out.println();
        }

        player1 = new Circle(40);
        player1.setId("player1");
        player1.setFill(Color.AQUA);
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(40);
        player2.setId("player2");
        player2.setFill(Color.CHOCOLATE);
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        Button buttonP2 = new Button("Player2");
        buttonP2.setTranslateX(700);
        buttonP2.setTranslateY(820);
        buttonP2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player2Turn) {
                        getDiceVal();
                        randResult.setText(String.valueOf(rand));
                        moveP2();
                        TranslatePlayer(player2XPos, player2YPos, player2);
                        player2Turn = false;
                        player1Turn = true;

                        //Ladders
                        if(player2XPos == 200 && player2YPos == 760){
                            TranslatePlayer(player2XPos = 120, player2YPos = 520, player2);
                            posCir2 += 3;
                        }
                        if(player2XPos == 760 && player2YPos == 760){
                            TranslatePlayer(player2XPos = 680, player2YPos = 680, player2);
                            posCir2 += 1;
                        }
                        if(player2XPos == 520 && player2YPos == 600){
                            TranslatePlayer(player2XPos = 600, player2YPos = 360, player2);
                            posCir2 += 3;
                        }
                        if(player2XPos == 40 && player2YPos == 280){
                            TranslatePlayer(player2XPos = 120, player2YPos = 40, player2);
                            posCir2 += 3;
                        }
                        if(player2XPos == 360 && player2YPos == 360){
                            TranslatePlayer(player2XPos = 280, player2YPos = 120, player2);
                            posCir2 += 3;
                        }
                        if(player2XPos == 680 && player2YPos == 200){
                            TranslatePlayer(player2XPos = 760, player2YPos = 120, player2);
                            posCir2 += 1;
                        }

                        //Snakes
                        if(player2XPos == 360 && player2YPos == 680){
                            TranslatePlayer(player2XPos = 600, player2YPos = 680, player2);
                            posCir2 -= 0;
                        }
                        if(player2XPos == 760 && player2YPos == 520){
                            TranslatePlayer(player2XPos = 280, player2YPos = 760, player2);
                            posCir2 -= 3;
                        }
                        if(player2XPos == 520 && player2YPos == 440){
                            TranslatePlayer(player2XPos = 360, player2YPos = 600, player2);
                            posCir2 -= 2;
                        }
                        if(player2XPos == 200 && player2YPos == 280){
                            TranslatePlayer(player2XPos = 40, player2YPos = 360, player2);
                            posCir2 -= 1;
                        }
                        if(player2XPos == 440 && player2YPos == 280){
                            TranslatePlayer(player2XPos = 680, player2YPos = 360, player2);
                            posCir2 -= 1;
                        }
                        if(player2XPos == 280 && player2YPos == 40){
                            TranslatePlayer(player2XPos = 440, player2YPos = 200, player2);
                            posCir2 -= 2;
                        }
                    }
                }
            }
        });

        Button buttonP1 = new Button("Player1");
        buttonP1.setTranslateX(80);
        buttonP1.setTranslateY(820);
        buttonP1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player1Turn) {
                        getDiceVal();
                        randResult.setText(String.valueOf(rand));
                        moveP1();
                        TranslatePlayer(player1XPos, player1YPos, player1);
                        player1Turn = false;
                        player2Turn = true;

                        //Ladders
                        if(player1XPos == 200 && player1YPos == 760){
                            TranslatePlayer(player1XPos = 120, player1YPos = 520, player1);
                            posCir1 += 3;
                        }
                        if(player1XPos == 760 && player1YPos == 760){
                            TranslatePlayer(player1XPos = 680, player1YPos = 680, player1);
                            posCir1 += 1;
                        }
                        if(player1XPos == 520 && player1YPos == 600){
                            TranslatePlayer(player1XPos = 600, player1YPos = 360, player1);
                            posCir1 += 3;
                        }
                        if(player1XPos == 40 && player1YPos == 280){
                            TranslatePlayer(player1XPos = 120, player1YPos = 40, player1);
                            posCir1 += 3;
                        }
                        if(player1XPos == 360 && player1YPos == 360){
                            TranslatePlayer(player1XPos = 280, player1YPos = 120, player1);
                            posCir1 += 3;
                        }
                        if(player1XPos == 680 && player1YPos == 200){
                            TranslatePlayer(player1XPos = 760, player1YPos = 120, player1);
                            posCir1 += 1;
                        }

                        //Snakes
                        if(player1XPos == 360 && player1YPos == 680){
                            TranslatePlayer(player1XPos = 600, player1YPos = 680, player1);
                            posCir1 -= 0;
                        }
                        if(player1XPos == 760 && player1YPos == 520){
                            TranslatePlayer(player1XPos = 280, player1YPos = 760, player1);
                            posCir1 -= 3;
                        }
                        if(player1XPos == 520 && player1YPos == 440){
                            TranslatePlayer(player1XPos = 360, player1YPos = 600, player1);
                            posCir1 -= 2;
                        }
                        if(player1XPos == 200 && player1YPos == 280){
                            TranslatePlayer(player1XPos = 40, player1YPos = 360, player1);
                            posCir1 -= 1;
                        }
                        if(player1XPos == 440 && player1YPos == 280){
                            TranslatePlayer(player1XPos = 680, player1YPos = 360, player1);
                            posCir1 -= 1;
                        }
                        if(player1XPos == 280 && player1YPos == 40){
                            TranslatePlayer(player1XPos = 440, player1YPos = 200, player1);
                            posCir1 -= 2;
                        }
                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(380);
        gameButton.setTranslateY(820);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Started");
                player1XPos = 40;
                player1YPos = 760;

                player2XPos = 40;
                player2YPos = 760;

                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);

                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                gameStart = true;
            }
        });

        randResult = new Label("0");
        randResult.setTranslateX(300);
        randResult.setTranslateY(820);

        Image img = new Image("file:C:/Users/Raghav Sahni/Desktop/snakebg.jpg");//fix this
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(800);
        bgImage.setFitWidth(800);

        tileGroup.getChildren().addAll(bgImage, player1, player2, buttonP2, buttonP1, gameButton, randResult);
        return root;
    }

    private void getDiceVal() {
        rand = (int)(Math.random()*6+1);
    }

    private  void moveP1() {
        for (int i = 0; i < rand; i++) {
            if (posCir1 % 2 == 1) {
                player1XPos += 80;
            }
            if (posCir1 % 2 == 0) {
                player1XPos -= 80;
            }
            if (player1XPos > 760) {
                player1YPos -= 80;
                player1XPos -= 80;
                posCir1++;
            }
            if (player1XPos < 40) {
                player1YPos -= 80;
                player1XPos += 80;
                posCir1++;
            }

            if (player1XPos < 30 || player1YPos < 30) {
                player1XPos = 40;
                player1YPos = 40;
                randResult.setText("Player 1 won");
                gameStart = false;
                gameButton.setText("Start Again");
            }
        }
    }

    private void moveP2() {
        for (int i = 0; i < rand; i++) {
            if (posCir2 % 2 == 1) {
                player2XPos += 80;
            }
            if (posCir2 % 2 == 0) {
                player2XPos -= 80;
            }
            if (player2XPos > 760) {
                player2YPos -= 80;
                player2XPos -= 80;
                posCir2++;
            }
            if (player2XPos < 40) {
                player2YPos -= 80;
                player2XPos += 80;
                posCir2++;
            }
            if (player2XPos < 30 || player2YPos < 30) {
                player2XPos = 40;
                player2YPos = 40;
                randResult.setText("Player 2 won");
                gameStart = false;
                gameButton.setText("Start Again");
            }
        }
    }

    private void TranslatePlayer(int x, int y, Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snakes and Ladders");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
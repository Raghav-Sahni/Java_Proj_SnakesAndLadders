package com.example.snakes_and_ladders;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.concurrent.TimeUnit;

import java.io.IOException;

public class DiceRoleSnake extends Application {

    private int rand;
    private Label randResult;

    private int cirPos[][] = new int [10][10];

    private static final int Tile_Size = 80;
    private static final int width = 10;
    private static final int height = 10;

    private Circle player1;
    private Circle player2;

    private boolean player1Turn = true;
    private boolean player2Turn = true;

    ladders l = new ladders();
    Snakes s = new Snakes();
    Dice d = new Dice(50,50);


    private boolean gameStart = false;
    private Button gameButton;

    Player p1, p2;

    private Group tileGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(width*Tile_Size, (height*Tile_Size)+80);
        root.getChildren().addAll(tileGroup);//Adds Tile group stuff in main stack pane

        HBox diceContainer = new HBox();
        diceContainer.setPadding(new Insets(10, 0, 0, 10));
        diceContainer.setAlignment(Pos.CENTER);
        diceContainer.setFillHeight(true);

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
                System.out.print(cirPos[i][j]+ " ");
            }
            System.out.println();
        }

        player1 = new Circle(40);
        player1.setId("player1");
        Image im1 = new Image("https://www.clipartmax.com/png/small/21-210215_ludo-piece-scarlett-red-ludo-cone-png.png",false);
        player1.setFill(new ImagePattern(im1));
        player1.setTranslateX(40);
        player1.setTranslateY(760);

        player2 = new Circle(40);
        player2.setId("player2");
        Image im2 = new Image("https://img.favpng.com/17/7/5/chess-piece-ludo-game-clip-art-png-favpng-KXNZcDNRLRFvqqWRm5dP0qhLt.jpg", false);
        player2.setFill(new ImagePattern(im2));
        player2.setTranslateX(40);
        player2.setTranslateY(760);

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
                        p2.move(rand);
                        MovePlayer(p2.getXpos(), p2.getYPos(), player2);
                        player2Turn = false;
                        player1Turn = true;

                        //Ladders
                        p2.setPosCir(l.checkLadder(p2, p2.getPosCir()));
                        MovePlayer(p2.getXpos(), p2.getYPos(), player2);

                        //Snakes
                        p2.setPosCir(s.checkSnake(p2, p2.getPosCir()));
                        MovePlayer(p2.getXpos(), p2.getYPos(), player2);
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
                        p1.move(rand);
                        MovePlayer(p1.getXpos(), p1.getYPos(), player1);
                        player1Turn = false;
                        player2Turn = true;

                        //Ladders
                        p1.setPosCir(l.checkLadder(p1, p1.getPosCir()));
                        MovePlayer(p1.getXpos(), p1.getYPos(), player1);

                        //Snakes
                        p1.setPosCir(s.checkSnake(p1, p1.getPosCir()));
                        MovePlayer(p1.getXpos(), p1.getYPos(), player1);
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
                p1 = new Player(40, 760);
                p2 = new Player(40, 760);

                player1.setTranslateX(p1.getXpos());
                player1.setTranslateY(p1.getYPos());

                player2.setTranslateX(p2.getXpos());
                player2.setTranslateY(p2.getYPos());
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

        diceContainer.getChildren().add(d);
        tileGroup.getChildren().addAll(bgImage, player1, player2, buttonP2, buttonP1, gameButton, randResult, diceContainer);
        return root;
    }

    private void getDiceVal() {
        rand = d.roll();
        d.setShow(true);
        d.update(rand);
    }

    private void MovePlayer(int x, int y, Circle b){
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
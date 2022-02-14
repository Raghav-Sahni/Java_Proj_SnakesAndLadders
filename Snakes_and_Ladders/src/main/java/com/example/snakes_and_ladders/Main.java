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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

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
        diceContainer.setTranslateX(270);
        diceContainer.setTranslateY(790);


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
        Image im1 = new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/redtoken2.png",false);
        player1.setFill(new ImagePattern(im1));
        player1.setTranslateX(40);
        player1.setTranslateY(760);

        player2 = new Circle(40);
        player2.setId("player2");
        Image im2 = new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/greentoken1.png", false);
        player2.setFill(new ImagePattern(im2));
        player2.setTranslateX(40);
        player2.setTranslateY(760);

        Button buttonP2 = new Button("Player2");
        Image image2=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/greentoken.png");
        ImageView iview2=new ImageView(image2);
        buttonP2.setGraphic(iview2);

        buttonP2.setTranslateX(660);
        buttonP2.setTranslateY(808);

        buttonP2.setFont(new Font("Arial", 15));
        buttonP2.setStyle("-fx-background-color: #008000");
        buttonP2.setTextFill(Color.BLACK);

        buttonP2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player2Turn) {
                        getDiceVal();
                        randResult.setText(String.valueOf(rand));
                        gameStart=p2.move(rand);

                        if(!gameStart)
                        {
                            gameButton.setText("PLAYER2 WON , PLAY AGAIN?") ;
                        }

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

        Image image1=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/redtoken1.png");
        ImageView iview1=new ImageView(image1);
        buttonP1.setGraphic(iview1);
        buttonP1.setTranslateX(90);
        buttonP1.setTranslateY(808);
        buttonP1.setFont(new Font("Arial", 15));
        buttonP1.setStyle("-fx-background-color: #ff0000");

        buttonP1.setTextFill(Color.BLACK);
        buttonP1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player1Turn) {
                        getDiceVal();
                        randResult.setText(String.valueOf(rand));
                        gameStart=p1.move(rand);
                        if(!gameStart)
                        {
                            gameButton.setText("PLAYER1 WON , PLAY AGAIN?") ;
                        }
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
        Image image3=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/startbutton2.png");
        ImageView iview3=new ImageView(image3);
        gameButton.setGraphic(iview3);

        gameButton.setFont(new Font("Arial", 15));
        gameButton.setStyle("-fx-background-color: #3399ff");
        gameButton.setTextFill(Color.BLACK);

        gameButton.setTranslateX(380);
        gameButton.setTranslateY(808);
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
        randResult.setTranslateY(810);

        Image img = new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/final_snakebg.jpg");//fix this
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(800);
        bgImage.setFitWidth(800);

        diceContainer.getChildren().add(d);

        Image image4=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/redarrow1.png");
        ImageView iview4=new ImageView(image4);

        TranslateTransition translate1=new TranslateTransition();
        translate1.setNode(iview4);
        translate1.setDuration(Duration.millis(1000));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setByY(10);
        translate1.setFromX(120);
        translate1.setFromY(750);
        translate1.setAutoReverse(true);
        translate1.play();

        Image image5=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/greenarrow1.png");
        ImageView iview5=new ImageView(image5);

        TranslateTransition translate2=new TranslateTransition();
        translate2.setNode(iview5);
        translate2.setDuration(Duration.millis(1000));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByY(10);
        translate2.setFromX(690);
        translate2.setFromY(750);
        translate2.setAutoReverse(true);
        translate2.play();


        tileGroup.getChildren().addAll(bgImage, player1, player2, buttonP2, buttonP1, gameButton, randResult, diceContainer,iview4,iview5);
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

        Scene scene2 = new Scene(createContent());
        stage.setTitle("Snakes and Ladders");
        Pane root1 = new Pane();
        root1.setPrefSize(width*Tile_Size, (height*Tile_Size)+80);
        root1.setStyle("-fx-background-color: "+"#FFFFFF");

        Image plantbg = new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/plantbg.jpg");//fix this
        ImageView iviewplant = new ImageView();
        iviewplant.setImage(plantbg);
        iviewplant.setFitHeight(800);
        iviewplant.setFitWidth(800);



        Scene scene1= new Scene(root1);





        Button button3= new Button("PLAY");
        button3.setTranslateX(350);
        button3.setTranslateY(650);

        button3.setFont(new Font("Arial", 30));
        button3.setStyle("-fx-background-color: #66ff33");

        button3.setTextFill(Color.BLACK);

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene2);

            }
        });

        Image image9=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/logo.jpg");
        ImageView iview9=new ImageView(image9);





        Image image6=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/moving_snake.png");
        ImageView iview6=new ImageView(image6);

        TranslateTransition translate7=new TranslateTransition();
        translate7.setNode(iview6);
        translate7.setDuration(Duration.millis(10000));
        translate7.setCycleCount(TranslateTransition.INDEFINITE);
        translate7.setByX(676);
        translate7.setFromX(0);
        translate7.setFromY(550);

        translate7.setAutoReverse(true);
        translate7.play();


        Image image7=new Image("C:/Users/Manik Arora//IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/ladder1.png");
        ImageView iview7=new ImageView(image7);

        TranslateTransition translate8=new TranslateTransition();
        translate8.setNode(iview7);
        translate8.setDuration(Duration.millis(10000));
        translate8.setCycleCount(TranslateTransition.INDEFINITE);
        translate8.setByY(10);
        translate8.setFromX(30);
        translate8.setFromY(250);

        translate8.setAutoReverse(true);
        translate8.play();


        Image image8=new Image("C:/Users/Manik Arora/IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/ladder2.png");
        ImageView iview8=new ImageView(image8);

        TranslateTransition translate9=new TranslateTransition();
        translate9.setNode(iview8);
        translate9.setDuration(Duration.millis(10000));
        translate9.setCycleCount(TranslateTransition.INDEFINITE);
        translate9.setByY(10);
        translate9.setFromX(670);
        translate9.setFromY(250);

        translate9.setAutoReverse(true);
        translate9.play();


        Image person1 = new Image("C:/Users/Manik Arora//IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/person.png");//fix this
        ImageView p1view = new ImageView();
        p1view.setImage(person1);
        p1view.setY(750);

        Image person2 = new Image("C:/Users/Manik Arora//IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/person1.png");//fix this
        ImageView p2view = new ImageView();
        p2view.setImage(person2);
        p2view.setX(750);
        p2view.setY(750);


        Text text5= new Text();
        text5.setText("Developed By:");
        text5.setX(350);
        text5.setY(750);
        text5.setFont(Font.font("Verdana", 15));
        text5.setFill(Color.BLUEVIOLET);


        Text text6= new Text();
        text6.setText("Raghav Sahni");
        text6.setX(40);
        text6.setY(820);
        text6.setFont(Font.font("Verdana", 15));
        text6.setFill(Color.BLUEVIOLET);

        Text text7= new Text();
        text7.setText("Manik Arora");
        text7.setX(660);
        text7.setY(820);
        text7.setFont(Font.font("Verdana", 15));
        text7.setFill(Color.BLUEVIOLET);













        TranslateTransition translate3=new TranslateTransition();
        translate3.setNode(iview9);
        translate3.setDuration(Duration.millis(1000));
        translate3.setCycleCount(TranslateTransition.INDEFINITE);
        translate3.setByX(10);
        translate3.setFromX(-75);
        translate3.setFromY(150);
        translate3.setAutoReverse(true);
        translate3.play();









        root1.getChildren().addAll(iviewplant,button3,iview9,iview6,iview7,iview8,p1view,p2view,text5,text6,text7);







        stage.setScene(scene1);
        Image icon= new Image("C:/Users/Manik Arora//IdeaProjects/Java_Proj_SnakesAndLadders-main (2)/Java_Proj_SnakesAndLadders-main/Java_Proj_SnakesAndLadders-main (1)/Java_Proj_SnakesAndLadders-main/Snakes_and_Ladders/src/main/java/snake.png");
        stage.getIcons().add(icon);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
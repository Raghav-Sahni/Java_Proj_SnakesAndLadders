module com.example.snakes_and_ladders {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakes_and_ladders to javafx.fxml;
    exports com.example.snakes_and_ladders;
}
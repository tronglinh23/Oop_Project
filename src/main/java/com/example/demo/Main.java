package com.example.demo;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    Scene scene1, scene2;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        //Scene1
        Label label = new Label("Say hello");
        Button button = new Button("Play game");
        button.setOnAction(event -> {
            window.setScene(scene2);
        });
        VBox layout = new VBox();
        layout.getChildren().addAll(label, button);
        scene1 = new Scene(layout, 300, 250);

        //Scene2
        Button button1 = new Button("Back");
        button1.setOnAction(event -> {
            window.setScene(scene1);
        });
        StackPane layout1 = new StackPane();
        layout1.getChildren().add(button1);
        scene2 = new Scene(layout1, 500, 300);

        window.setScene(scene1);
        window.show();
    }
}

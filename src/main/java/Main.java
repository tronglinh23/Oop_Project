import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            viewManager mange_game = new viewManager();
            primaryStage = mange_game.getMainStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
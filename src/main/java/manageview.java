import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class manageview {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final String TitleName = "Bomberman";

    private static final int Menu_Button_X = 100;
    private static final int Menu_Button_Y = 150;

    ArrayList<ButtonRunner> List_Button_Menu;
    public manageview() {
        List_Button_Menu = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setTitle(TitleName);
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void AddMenuButton(ButtonRunner button_name) {
        button_name.setLayoutX(Menu_Button_X);
        button_name.setLayoutY(Menu_Button_Y + List_Button_Menu.size() * 100);
        List_Button_Menu.add(button_name);
        mainPane.getChildren().add(button_name);
    }

    private void createButtons(){
       CreateStartMenu();
       CreateScoresMenu();
       CreateHelpMenu();
       CreateCreditsMenu();
       CreateExitMenu();
    }

    private void CreateScoresMenu() {
        ButtonRunner Score_Button = new ButtonRunner("Scores");
        AddMenuButton(Score_Button);
    }

    private void CreateHelpMenu() {
        ButtonRunner Help_Button = new ButtonRunner("Help");
        AddMenuButton(Help_Button);
    }

    private void CreateCreditsMenu() {
        ButtonRunner Credits_button = new ButtonRunner("Credits");
        AddMenuButton(Credits_button);
    }

    private void CreateExitMenu() {
        ButtonRunner Exit_Button = new ButtonRunner("Exit");
        AddMenuButton(Exit_Button);
    }

    private void CreateStartMenu() {
        ButtonRunner Start_Button = new ButtonRunner("Start");
        AddMenuButton(Start_Button);
    }

    private void createBackground() {
        Image backgroundImage = new Image("boom-mobile-1.jpg",WIDTH,HEIGHT,false,false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
}

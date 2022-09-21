import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class manageview {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final String TitleName = "Bomberman";

    private static final int Menu_Button_X = 100;
    private static final int Menu_Button_Y = 150;


    // Create All SubScene Button on menu Game
    private Subscene creditsSubscene;
    private Subscene HelpSubscene;
    private Subscene ScoresSubscene;
    private Subscene ShowupSubscene;
    private  Subscene shipChooseSubScene;
    ArrayList<ButtonRunner> List_Button_Menu;

    // Create list all type of ships
    ArrayList<ShipPicker> shipsList;
    private SHIP chosenShip;
    public manageview() {
        List_Button_Menu = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setTitle(TitleName);
        mainStage.setScene(mainScene);
        createSubscene();
        createButtons();
        createBackground();
//        createLogo();


    }

    private void createSubscene() {
        creditsSubscene = new Subscene();
        mainPane.getChildren().add(creditsSubscene);

        HelpSubscene = new Subscene();
        mainPane.getChildren().add(HelpSubscene);

        ScoresSubscene = new Subscene();
        mainPane.getChildren().add(ScoresSubscene);

        createShipChooserSubScene();
    }

    public void createShipChooserSubScene() {
        shipChooseSubScene = new Subscene();
        mainPane.getChildren().add(shipChooseSubScene);

        // Label of SubScene when pushing Start Button
        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooseSubScene.getPane().getChildren().add(chooseShipLabel);
        shipChooseSubScene.getPane().getChildren().add(createShipsToChoose());
        shipChooseSubScene.getPane().getChildren().add(createStartButtonSubScene());
    }

    /**
     * Create a list to choose a ship .
     * Showing GUI Ship to pick.
     * 
     * @return a Hbox
     */
    private HBox createShipsToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();

        for(SHIP ship : SHIP.values()) {
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(ShipPicker shipPicked : shipsList) {
                        shipPicked.setIsCircleChosen(false);
                    }
                    shipToPick.setIsCircleChosen(true);
                    chosenShip = shipToPick.getShip();
                }
            });
        }
        box.setLayoutX(300 - (118 * 2));
        box.setLayoutY(100);
        return box;
    }

    private ButtonRunner createStartButtonSubScene() {
        ButtonRunner startButton = new ButtonRunner("Start");
        startButton.setLayoutX(200);
        startButton.setLayoutY(300);
        return startButton;
    }
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * Add each button for menu game.
     * @param button_name name of button
     */
    private void AddMenuButton(ButtonRunner button_name) {
        button_name.setLayoutX(Menu_Button_X);
        button_name.setLayoutY(Menu_Button_Y + List_Button_Menu.size() * 100);
        List_Button_Menu.add(button_name);
        mainPane.getChildren().add(button_name); // add button to main Pain
    }

    private void createButtons(){
       CreateStartMenu();
       CreateScoresMenu();
       CreateHelpMenu();
       CreateCreditsMenu();
       CreateExitMenu();
    }

    private void Show_up_Subscene(Subscene current_Subscene) {
        if(ShowupSubscene != null) {
            ShowupSubscene.moveSubscene();
        }
        if(ShowupSubscene != current_Subscene){
            current_Subscene.moveSubscene();
            ShowupSubscene = current_Subscene;
        }
    }
    private void CreateScoresMenu() {
        ButtonRunner Score_Button = new ButtonRunner("Scores");
        AddMenuButton(Score_Button);
        Score_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Show_up_Subscene(ScoresSubscene);
            }
        });
    }

    private void CreateHelpMenu() {
        ButtonRunner Help_Button = new ButtonRunner("Help");
        AddMenuButton(Help_Button);
        Help_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Show_up_Subscene(HelpSubscene);
            }
        });
    }

    private void CreateCreditsMenu() {
        ButtonRunner Credits_button = new ButtonRunner("Credits");
        AddMenuButton(Credits_button);
        Credits_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Show_up_Subscene(creditsSubscene);
            }
        });
    }

    private void CreateExitMenu() {
        ButtonRunner Exit_Button = new ButtonRunner("Exit");
        AddMenuButton(Exit_Button);
        Exit_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void CreateStartMenu() {
        ButtonRunner Start_Button = new ButtonRunner("Start");
        AddMenuButton(Start_Button);
        Start_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Show_up_Subscene(shipChooseSubScene);
            }
        });
    }

    /**
     * set a background of menu screen.
     */
    private void createBackground() {
        Image backgroundImage = new Image("boom-mobile-1.jpg",WIDTH,HEIGHT,false,false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        final String imageLoadPath = "boom-flash-white_1308-2912.jpg";
        ImageView logo = new ImageView(imageLoadPath);
        logo.setLayoutX(400);
        logo.setLayoutY(50);
        logo.setFitWidth(300);
        logo.setFitHeight(250);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }

}

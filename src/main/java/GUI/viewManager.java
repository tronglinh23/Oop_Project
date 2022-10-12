package GUI;

import Others.*;
import MainGame.GameManager;
import Map.TileMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.sound.sampled.*;
import java.util.ArrayList;
public class viewManager {

    private static int WIDTH_SCREEN = 1024;
    private static int HEIGHT_SCREEN = 768;

    private AnchorPane mainPain;
    private Stage mainStage;
    private Scene mainScene;

    private SubSceneGame startSubScene;
    private SubSceneGame helpSubScene;
    private SubSceneGame currentSubScene;

    private final static String Title_game = "Boom Online";

    ArrayList<ButtonGame> listButtonMenu;
    private static final int Menu_Button_X = 100;
    private static final int Menu_Button_Y = 350;

    private boolean isMusicPlay;
    private final static String BACKGROUND_IMG = "BackgroundGame/boom-mobile-1.jpg";

    Clip menuSongClip;
    Clip mouseClick;

    public viewManager() {
        mainPain = new AnchorPane();
        mainScene = new Scene(mainPain, WIDTH_SCREEN, HEIGHT_SCREEN);
        mainStage = new Stage();
        mainStage.setTitle(Title_game);
        mainStage.setScene(mainScene);
        createBackGround();
        createButtons();
        createStartSubScene();
        createHelpSubScene();

        setMenuSongClip();
    }

    public void createStartSubScene() {
        startSubScene = new SubSceneGame();
        mainPain.getChildren().add(startSubScene);
        startSubScene.getPane().getChildren().add(startButton());
    }

    public void createHelpSubScene() {
        helpSubScene = new SubSceneGame();
        mainPain.getChildren().add(helpSubScene);
    }

    public void setMenuSongClip() {
        menuSongClip = SoundLoad.getSoundVolume("src/main/resources/sounds/Happy Accident Simple Long Loop.wav", -20);
        menuSongClip.loop(30);
        menuSongClip.start();
    }
    public Stage getMainStage() {
        return this.mainStage;
    }

    private void createBackGround() {
        Image IMG = new Image(BACKGROUND_IMG, WIDTH_SCREEN, HEIGHT_SCREEN, false, false);
        BackgroundImage backgroundIMG = new BackgroundImage(IMG,BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPain.setBackground(new Background(backgroundIMG));
    }

    private void addMenuButton(ButtonGame buttonName) {
        buttonName.setLayoutX(Menu_Button_X);
        buttonName.setLayoutY(Menu_Button_Y + listButtonMenu.size() * 100);
        listButtonMenu.add(buttonName);
        mainPain.getChildren().add(buttonName);
    }

    private void createButtons() {
        listButtonMenu = new ArrayList<>();
        createStartMenu();
//        createScoresMenu();
        createHelpMenu();
        createExitMenu();
    }

    private void createStartMenu() {
        ButtonGame startButton = new ButtonGame("Start");
        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mouseClick = SoundLoad.getSoundVolume("src/main/resources/sounds/Mouse-Click-00-m-FesliyanStudios.com.wav", -5);
                mouseClick.start();
                showUpSubScene(startSubScene);

            }
        });
    }

    private void createHelpMenu() {
        ButtonGame helpButton = new ButtonGame("Help");
        addMenuButton(helpButton);
        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseClick = SoundLoad.getSoundVolume("src/main/resources/sounds/Mouse-Click-00-m-FesliyanStudios.com.wav", -5);
                mouseClick.start();
                showUpSubScene(helpSubScene);
            }
        });
    }

    private void createExitMenu() {
        ButtonGame exitButton = new ButtonGame("Exit");
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClick = SoundLoad.getSoundVolume("src/main/resources/sounds/Mouse-Click-00-m-FesliyanStudios.com.wav", -5);
                mouseClick.start();
                mainStage.close();
            }
        });
        addMenuButton(exitButton);
    }


    int countScreen;
    public void showUpSubScene(SubSceneGame current) {
        if (currentSubScene != null) {
            countScreen += 1;
            currentSubScene.moveSubscene();
        }
        if(currentSubScene != current) {
            current.moveSubscene();
            countScreen = 1;
            currentSubScene = current;
        }

        if(countScreen == 2) currentSubScene = null; // Nếu 1 screen được bấm 2 lần rồi thì ta sẽ gán cho nó bằng null
    }

    public ButtonGame startButton() {
        ButtonGame start = new ButtonGame("Start");
        start.setLayoutX(135);
        start.setLayoutY(325);
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menuSongClip.stop();
                Clip start = SoundLoad.getSoundVolume("src/main/resources/sounds/start.wav", 0);
                start.start();
                GameManager gameStage = new GameManager();
                GameManager.level_Game = 1; // khoi tao bien static
                TileMap.levelGame = 0; // khoi tao bien static
                gameStage.createNewGame(mainStage);
            }
        });
        return start;
    }
}

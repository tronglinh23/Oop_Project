package GUI;

import Player.MainPlayer;
import Others.*;
import MainGame.GameManager;
import Map.TileMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.sound.sampled.*;
import java.util.ArrayList;

/**
 * Handle menu game.
 */
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
    private final static String BACKGROUND_IMG = "BackgroundGame/BoomBackground.jpg";
    private final static String LOGO_IMG = "BackgroundGame/TitleBackground.png";

    Clip menuSongClip;
    Clip mouseClick;
    int countScreen;

    private ArrayList<BombPick> bombPickArrayList;

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
        createLogo();
    }

    /**
     * Init sound menu game.
     */
    public void setMenuSongClip() {
        menuSongClip = SoundLoad.getSoundVolume("src/main/resources/sounds/Happy Accident Simple Long Loop.wav", -20);
        menuSongClip.loop(30);
        menuSongClip.start();
    }

    /**
     * Create Background load IMG, screen width, height.
     */
    private void createBackGround() {
        Image IMG = new Image(BACKGROUND_IMG, WIDTH_SCREEN, HEIGHT_SCREEN, false, false);
        BackgroundImage backgroundIMG = new BackgroundImage(IMG,BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPain.setBackground(new Background(backgroundIMG));
    }

    public void createLogo() {
        Image logoIMG = new Image(LOGO_IMG, 468, 100, false, false);
        ImageView img = new ImageView(logoIMG);
        img.setLayoutX(250);
        img.setLayoutY(75);
        mainPain.getChildren().add(img);
    }

    public void createStartSubScene() {
        startSubScene = new SubSceneGame();
        mainPain.getChildren().add(startSubScene);
        startSubScene.getPane().getChildren().add(startButton());
        startSubScene.getPane().getChildren().add(BombToChoose());
    }

    public void createHelpSubScene() {
        helpSubScene = new SubSceneGame();
        mainPain.getChildren().add(helpSubScene);
        ImageView img = new ImageView("BackgroundGame/HELP.png");
        img.setLayoutX(25);
        img.setLayoutY(50);
        helpSubScene.getPane().getChildren().add(img);
    }

    private void addMenuButton(ButtonGame buttonName) {
        buttonName.setLayoutX(Menu_Button_X);
        buttonName.setLayoutY(Menu_Button_Y + listButtonMenu.size() * 100);
        listButtonMenu.add(buttonName);
        mainPain.getChildren().add(buttonName);
    }

    private void createButtons() {
        listButtonMenu = new ArrayList<>();
        createStartButton();
        createHelpButton();
        createExitButton();
    }

    private void createStartButton() {
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

    private void createHelpButton() {
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

    private void createExitButton() {
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

    /**
     * Handle screen.
     * @param current current screen
     */
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

    /**
     * startButton của StartSubScene.
     * check mouseclick to start game.
     * @return button start
     */
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
                GameManager.level_Game = 0; // khoi tao bien static level của game
                TileMap.levelGame = 0; // khoi tao bien static level tilemap
                gameStage.createNewGame(mainStage);
            }
        });
        return start;
    }

    /**
     * Load array Bomb to choose.
     * Design display.
     * @return box
     */
    private HBox BombToChoose() {
        HBox box = new HBox();
        bombPickArrayList = new ArrayList<>();

        box.setSpacing(75);

        for(TypeBomb ship : TypeBomb.values()) {
            BombPick bombToPick = new BombPick(ship);
            bombPickArrayList.add(bombToPick);
            box.getChildren().add(bombToPick);
            bombToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(BombPick bombPicked : bombPickArrayList) {
                        bombPicked.setIsCircleChosen(false);
                    }
                    bombToPick.setIsCircleChosen(true);
                    MainPlayer.bombType = bombToPick.getTypeBomb();
                }
            });
        }

        box.setLayoutX(80);
        box.setLayoutY(100);

        return box;
    }

    public Stage getMainStage() {
        return this.mainStage;
    }
}

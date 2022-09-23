import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private final static int GAME_WIDTH = 600;
    private final static int GAME_HEIGHT = 800;

    private Stage menuStage;
    private ImageView ship;

    private boolean isLeftKeyPressed;

    private boolean isRightKeyPressed;

    private boolean isUpKeyPressed;

    private boolean isDownKeyPressed;
    private AnimationTimer gameTimer;

    private int angle;

    private GridPane gridPane1;
    private GridPane gridPane2;

    private final static String BACKGROUND_IMG = "spaceshooter/Backgrounds/blue.png";

    private final static String obstacle_BrownRock_IMG = "spaceshooter/PNG/Meteors/meteorBrown_med1.png";
    private final static String obstacle_GreyRock_IMG = "spaceshooter/PNG/Meteors/meteorGrey_med1.png";

    private ImageView[] brownRock;
    private ImageView[] greyRock;
    Random randomLocateElements;

    private int player_life = 2;
    private ImageView[] player_ship_life;

    private ImageView Star_bonus_life;
    private final static String STAR_IMG = "spaceshooter/PNG/Power-ups/star_gold.png";
    public GameViewManager() {
        initializeStage();
        createKeyListeners();
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = false;
                }
            }
        });
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setTitle("Bomberman");
        gameStage.setScene(gameScene);

    }

    public void createNewGame(Stage newStage, SHIP chosenShip){
        this.menuStage = newStage;
        this.menuStage.hide();
        createBackGround();
        createElements();
        createShip(chosenShip);
        createGameLoop();
        createInfoLabel_Score(chosenShip);
        gameStage.show();
    }

    private void createShip(SHIP chosenShip) {
        this.ship = new ImageView(chosenShip.getUrl());
        ship.setLayoutX(GAME_WIDTH/2 - 80);
        ship.setLayoutY(GAME_HEIGHT - 90);
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveElement();
                checkElementOutScene();
                moveBackground();
                moveShip();
            }
        };
        gameTimer.start();
    }

    private void moveShip() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > -20) {
                ship.setLayoutX(ship.getLayoutX() - 3);
            }
        }

        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() < 550) {
                ship.setLayoutX(ship.getLayoutX() + 3);
            }
        }

        if (isRightKeyPressed && isLeftKeyPressed) {
            if (angle > 0) {
                angle -= 5;
            } else if (angle < 0) {
                angle += 5;
            }
            ship.setRotate(angle);
        }

        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > 0) {
                angle -= 5;
            } else if (angle < 0) {
                angle += 5;
            }
            ship.setRotate(angle);
        }

        // Handle Up down move

        if (isUpKeyPressed && !isDownKeyPressed) {
            if (ship.getLayoutY() >= 0) {
                ship.setLayoutY(ship.getLayoutY() - 3);
            }
        }

        if (!isUpKeyPressed && isDownKeyPressed) {
            if (ship.getLayoutY() < GAME_HEIGHT - 90) {
                ship.setLayoutY(ship.getLayoutY() + 3);
            }
        }

    }

    private void createBackGround() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        ImageView backgroundImage1 = new ImageView(BACKGROUND_IMG);
        backgroundImage1.setFitHeight(800);
        backgroundImage1.setFitWidth(600);

        ImageView backgroundImage2 = new ImageView(BACKGROUND_IMG);
        backgroundImage2.setFitHeight(800);
        backgroundImage2.setFitWidth(600);

//            GridPane.setConstraints(gridPane1, i % 3, i);
//            GridPane.setConstraints(gridPane2, i % 3, i);
        gridPane1.getChildren().add(backgroundImage1);
        gridPane2.getChildren().add(backgroundImage2);


        gridPane2.setLayoutY(-800);
        gamePane.getChildren().addAll(gridPane1, gridPane2);

    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 2);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 2);

        if(gridPane1.getLayoutY() >= 800) {
            gridPane1.setLayoutY(-800);
        }
        if(gridPane2.getLayoutY() >= 800) {
            gridPane2.setLayoutY(-800);
        }
    }

    // khoi tao cac gia tri obstacle
    // logic
    private void createElements() {
        Star_bonus_life = new ImageView(STAR_IMG);
        setElementLocation(Star_bonus_life);
        gamePane.getChildren().add(Star_bonus_life);

        brownRock = new ImageView[3];
        for(int elements = 0 ; elements < brownRock.length ; elements++) {
            brownRock[elements] = new ImageView(obstacle_BrownRock_IMG);
            setElementLocation(brownRock[elements]);
            gamePane.getChildren().add(brownRock[elements]);
        }

        greyRock = new ImageView[3];
        for(int elements = 0 ; elements < greyRock.length ; elements++) {
            greyRock[elements] = new ImageView(obstacle_GreyRock_IMG);
            setElementLocation(greyRock[elements]);
            gamePane.getChildren().add(greyRock[elements]);
        }
    }

    private void setElementLocation(ImageView object) {
        randomLocateElements = new Random();
        object.setLayoutX(randomLocateElements.nextInt(550));
        object.setLayoutY(-(randomLocateElements.nextInt(2000)));
    }

    private void moveElement() {
        Star_bonus_life.setLayoutY(Star_bonus_life.getLayoutY() + 10);
        Star_bonus_life.setRotate(Star_bonus_life.getRotate() + 3);

        for(ImageView i : brownRock) {
            i.setLayoutY(i.getLayoutY() + 7);
            i.setRotate(i.getRotate() + 4);
        }

        for(ImageView i : greyRock) {
            i.setLayoutY(i.getLayoutY() + 7);
            i.setRotate(i.getRotate() + 4);
        }
    }

    private void checkElementOutScene() {
        if(Star_bonus_life.getLayoutY() > 3000) setElementLocation(Star_bonus_life);
        for(ImageView i : brownRock) {
            if(i.getLayoutY() >= 900) setElementLocation(i);
        }

        for(ImageView i : greyRock) {
            if(i.getLayoutY() >= 900) setElementLocation(i);
        }
    }
    private void createInfoLabel_Score(SHIP chosenship) {
        InfoLabel scores = new InfoLabel("Scores");
        scores.setLayoutY(150);
        scores.setPrefWidth(150);
        scores.setLayoutX(450);
        gamePane.getChildren().add(scores);

        player_ship_life = new ImageView[3];
        for (int i = 0 ; i < player_ship_life.length ; i++) {
            player_ship_life[i] = new ImageView(chosenship.getUrlLife());
            player_ship_life[i].setFitWidth(30);
            player_ship_life[i].setFitHeight(40);
            player_ship_life[i].setLayoutY(210);
            player_ship_life[i].setLayoutX(450 + i * 50);
            gamePane.getChildren().add(player_ship_life[i]);
        }
    }
}

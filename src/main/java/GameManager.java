import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;
import java.io.*;
import java.util.ArrayList;

public class GameManager {
    private static int WIDTH_SCREEN = 765;
    private static int HEIGHT_SCREEN = 675;

    private Stage menuStage;
    private AnchorPane mainPain;
    private Stage mainStage;
    private Scene mainScene;
    private final static String Title_game = "Boom";

    private AnimationTimer gameTimer;

    private ArrayList<TileMap> arrTileMap;
    private ArrayList<Boom> arrBoom;
    private ArrayList<Integer> TimeBombStart;

    private MainPlayer player;
    private Enemy enemy;


    public final ImageView[] MY_IMAGE={
           new ImageView("/images/background.jpg"),
    };
    public GameManager() {
        mainPain = new AnchorPane();
        mainScene = new Scene(mainPain, WIDTH_SCREEN, HEIGHT_SCREEN);
        mainStage = new Stage();
        mainStage.setTitle(Title_game);
        mainStage.setScene(mainScene);
        createKeyListeners();
    }

    private void createKeyListeners() {
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    player.setLeftKeyPressed(true);
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    player.setRightKeyPressed(true);
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    player.setUpKeyPressed(true);
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    player.setDownKeyPressed(true);
                } else if (keyEvent.getCode() == KeyCode.SPACE) {
                    addBombToPlayer(0);
                }
            }
        });

        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    player.setLeftKeyPressed(false);
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    player.setRightKeyPressed(false);
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    player.setUpKeyPressed(false);
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    player.setDownKeyPressed(false);
                }
            }
        });
    }
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.movePlayer(arrTileMap,arrBoom);
                enemy.moveEnemy(arrTileMap);
                checkifoutoftime();
            }
        };
        gameTimer.start();
    }
    public void checkifoutoftime() {
        if(arrBoom.size() > 2) {
            arrBoom.get(0).checkoutoftime(arrBoom);
            arrBoom.remove(0);
        }
    }

    public void addBombToPlayer(int time_start) {
        if(arrBoom.size() < player.getAmountBomb() + 2) {
            Boom boom = player.setupBoom();
            arrBoom.add(boom);
            Clip clip = SoundLoad.getSound(getClass().getResource("sounds/set_boom.wav"));
            clip.start();
            TimeBombStart.add(time_start);
        }
    }
    public void draw() {
        player.movePlayer(arrTileMap,arrBoom);
        enemy.moveEnemy(arrTileMap);
    }
    public Stage getGameStage() {
        return this.mainStage;
    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        arrTileMap = new ArrayList<>();
        arrBoom = new ArrayList<>();
        TimeBombStart = new ArrayList<>();
        player = new MainPlayer(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE, 0, mainPain, mainScene);
        enemy = new Enemy(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE, 0, mainPain, mainScene);
        createBackground();
        readTxtMap();
//        drawStage();
        player.drawMainPlayer();
        enemy.drawEnemy();
        createGameLoop();
        mainStage.show();
    }

    public void createBackground() {
        MY_IMAGE[0].setFitWidth(WIDTH_SCREEN);
        MY_IMAGE[0].setFitHeight(HEIGHT_SCREEN);
        mainPain.getChildren().add(MY_IMAGE[0]);
    }

    public void drawStage() {
        try {
            for(TileMap obstacle : arrTileMap) {
                obstacle.drawImageStage(mainPain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readTxtMap() {
        File file = new File("src/main/resources/map/mapBoom.txt");
        int countLine = 0;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while(line != null) {
                for (int i = 0; i < line.length(); i++) {
                    arrTileMap.add(new TileMap(i*TileMap.SIZE, countLine*TileMap.SIZE,
                            Integer.parseInt(String.valueOf(line.charAt(i)))));
                }
                line = reader.readLine();
                countLine++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

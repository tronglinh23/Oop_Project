import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.*;
import java.time.Duration;
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
    private ArrayList<Long> TimeBombStart;

    private ArrayList<WaveBoom> arrWaveBoom;
    private ArrayList<Long> timeWaveBoom;

    private MainPlayer player;
    private Enemy enemy;

    private final static int time_Bomb = 1;
    private final static int wave_Bomb = 1;


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

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        arrTileMap = new ArrayList<>();
        arrBoom = new ArrayList<>();
        TimeBombStart = new ArrayList<>();
        arrWaveBoom = new ArrayList<>();
        timeWaveBoom = new ArrayList<>();
        player = new MainPlayer(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE, 0, mainPain, mainScene);
        enemy = new Enemy(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE, 0, mainPain, mainScene);
        createBackground();
        readTxtMap();
        drawStage();
        player.drawMainPlayer();
        enemy.drawEnemy();
        createGameLoop();
        mainStage.show();
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
                    addBombToPlayer(System.nanoTime());
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
        long time;
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.movePlayer(arrTileMap,arrBoom);
                enemy.moveEnemy(arrTileMap);
                checkTimeBombExplode();
                bombBangTime();
            }
        };
        gameTimer.start();

    }
    public void checkTimeBombExplode() {
        for(int i = 0 ; i < arrBoom.size() ; i++) {
            double time = (System.nanoTime() - TimeBombStart.get(i)) / Math.pow(10,9);
            if(time >= time_Bomb) {
                WaveBoom waveBoom = arrBoom.get(0).boomBang();
                arrWaveBoom.add(waveBoom);
                timeWaveBoom.add(System.nanoTime());
                waveBoom.draw(mainPain,arrTileMap);
                mainPain.getChildren().remove(arrBoom.get(0).getImageView());
                arrBoom.remove(i);
                TimeBombStart.remove(i);
            }
        }

    }

    public void bombBangTime () {
        for(int i = 0 ; i < timeWaveBoom.size() ; i++) {
            double k = (System.nanoTime() - timeWaveBoom.get(i)) / Math.pow(10,9);
            if(k >= wave_Bomb) {
                arrWaveBoom.get(i).update(mainPain);
                arrWaveBoom.remove(i);
                timeWaveBoom.remove(i);
            }
        }
    }
    public void update() {

    }
    public void addBombToPlayer(long time_start) {
        if(arrBoom.size() < player.getAmountBomb() + 2) {
            if(!MainPlayer.getIscoBomb()) {
                Boom boom = player.setupBoom();
                arrBoom.add(boom);
                Clip clip = SoundLoad.getSound(getClass().getResource("sounds/set_boom.wav"));
                clip.start();
                TimeBombStart.add(time_start);
            }
        }
    }
    public void draw() {
        player.movePlayer(arrTileMap,arrBoom);
        enemy.moveEnemy(arrTileMap);
    }
    public Stage getGameStage() {
        return this.mainStage;
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

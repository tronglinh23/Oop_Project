import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;
import java.io.*;
import java.util.ArrayList;

public class GameManager {
    private static int WIDTH_SCREEN = 765;
    private static int HEIGHT_SCREEN = 675;

    private ArrayList<KeyCode> keyCodes = new ArrayList<>();
    GraphicsContext gContext;
    Canvas canvas;

    private Stage menuStage;
    private Stage mainStage;
    private Scene mainScene;
    private final static String Title_game = "Boom";

    private AnimationTimer gameTimer;

    private ArrayList<TileMap> arrTileMap;
    private ArrayList<Enemy> arrEnemy;
    private ArrayList<Boom> arrBoom;
    private ArrayList<Long> TimeBombStart;

    private ArrayList<WaveBoom> arrWaveBoom;
    private ArrayList<Long> timeWaveBoom;


    private MainPlayer player;
//    private Enemy[] enemy = new Enemy[6];
    private Enemy enemy;

    private ArrayList<Integer> KeyCodeEvent;
    private final static int time_Bomb = 2;
    private final static int wave_Bomb = 1;

    public final Image MY_IMAGE= ImageUtils.loadImage("src/main/resources/images/background.jpg");
    public GameManager() {

    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();


        canvas = new Canvas(WIDTH_SCREEN,HEIGHT_SCREEN);
        gContext = canvas.getGraphicsContext2D();
        Group root = new Group(canvas);

        mainScene = new Scene(root, WIDTH_SCREEN, HEIGHT_SCREEN);
        mainStage = new Stage();
        mainStage.setTitle(Title_game);
        mainStage.setScene(mainScene);

        arrTileMap = new ArrayList<>();
        arrEnemy = new ArrayList<>();
        arrBoom = new ArrayList<>();
        TimeBombStart = new ArrayList<>();
        arrWaveBoom = new ArrayList<>();
        timeWaveBoom = new ArrayList<>();

        KeyCodeEvent = new ArrayList<>();

        player = new MainPlayer(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE);
        enemy = new Enemy(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE, 0 );
        arrEnemy.add(enemy);

        readTxtMap();
        createGameLoop();
        createKeyListeners();
        mainStage.show();
    }
    private void createKeyListeners() {
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
              @Override
              public void handle(KeyEvent keyEvent) {
                  KeyCode code = keyEvent.getCode();
                  if (!keyCodes.contains(code)) keyCodes.add(code);
                  if(code == KeyCode.SPACE) {
                      addBombToPlayer(System.nanoTime());
                  }
              }
        });

        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if (keyCodes.contains(code)) keyCodes.remove(code);
            }
        });
    }

    private void createGameLoop() {
        long time;
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gContext.clearRect(0,0, WIDTH_SCREEN, HEIGHT_SCREEN);
                update();
                renderer();
            }
        };
        gameTimer.start();

    }
    public void checkTimeBombExplode() {
        for(int i = 0 ; i < arrBoom.size() ; i++) {
            double time = (System.nanoTime() - TimeBombStart.get(i)) / Math.pow(10,9);
            if(time >= time_Bomb) {
                WaveBoom waveBoom = arrBoom.get(i).boomBang();
                arrBoom.remove(i);
                TimeBombStart.remove(i);
                arrWaveBoom.add(waveBoom);
                try {
                    waveBoom.checkExplodeBoom_Boom(arrBoom, TimeBombStart);
                    waveBoom.checkExplodeBoom_Enemy(arrEnemy);
                } catch (IndexOutOfBoundsException e) {

                }
                timeWaveBoom.add(System.nanoTime());
            }
        }

    }

    public void bombBangTime () {
        for(int i = 0 ; i < timeWaveBoom.size() ; i++) {
            double k = (System.nanoTime() - timeWaveBoom.get(i)) / Math.pow(10,9);
            if(k >= wave_Bomb) {
                arrWaveBoom.remove(i);
                timeWaveBoom.remove(i);
            }
        }
    }

    /**
     * Update Image, move, time ...
     */
    public void update() {
        player.movePlayer(arrTileMap,arrBoom,keyCodes);
        enemy.moveEnemy(arrTileMap);
        checkTimeBombExplode();
        bombBangTime();
    }

    public void addBombToPlayer(long time_start) {
        if(arrBoom.size() < player.getAmountBomb() + 2) {
            if(player.getIscoBomb(arrBoom)) {
                Boom boom = player.setupBoom();
                arrBoom.add(boom);
                Clip clip = SoundLoad.getSound(getClass().getResource("sounds/set_boom.wav"));
                clip.start();
                TimeBombStart.add(time_start);
            }
        }
    }

    /**
     * Render all image to screen.
     */
    public void renderer() {
        createBackground();

        for (Boom boom : arrBoom) {
            boom.draw(gContext);
        }
        for (WaveBoom waveBoom : arrWaveBoom) {
            waveBoom.draw(arrTileMap, gContext);
        }


        drawTileMap();
        for (Enemy enemy1 : arrEnemy) {
            enemy1.drawEnemy(gContext);
            enemy1.moveEnemy(arrTileMap);
        }
        player.drawMainPlayer(gContext);

    }

    public void createBackground() {
        gContext.drawImage(MY_IMAGE,0,0,WIDTH_SCREEN,HEIGHT_SCREEN);
    }

    /**
     * Draw tileMap.
     */
    public void drawTileMap() {
        int i = 0;
        try {
            for(TileMap obstacle : arrTileMap) {
                obstacle.drawImageStage(gContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read File Map.
     * Lấy từng kí tự đẩy vào arraylist để draw.
     */
    public void readTxtMap() {
        try {
            File file = new File("src/main/resources/map/mapBoom.txt");
            int countLine = 0;
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            while(line != null) {
                for (int chr = 0; chr < line.length(); chr++) {
                    arrTileMap.add(new TileMap(chr*TileMap.SIZE, countLine*TileMap.SIZE,
                            Integer.parseInt(String.valueOf(line.charAt(chr)))));
                }

                line = reader.readLine();
                countLine++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

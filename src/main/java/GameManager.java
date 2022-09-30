import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;
import java.io.*;
import java.util.ArrayList;

public class GameManager {
    private static int WIDTH_SCREEN = 765;
    private static int HEIGHT_SCREEN = 675;

    private final static int time_Bomb = 2;

    private final static int time_Wave_Bomb = 1;

    private final static int time_Bomber_Die = 3;

    private static long time_Start_Game;

    private double time_Die_Player;

    private final static String Title_game = "Boom";

    GraphicsContext gContext;
    Canvas canvas;

    private Stage menuStage;
    private Stage mainStage;
    private Scene mainScene;

    private AnimationTimer gameTimer;

    private ArrayList<TileMap> arrTileMap;
    private ArrayList<Enemy> arrEnemy;
    private ArrayList<Boom> arrBoom;
    private ArrayList<Long> TimeBombStart;

    private ArrayList<WaveBoom> arrWaveBoom;
    private ArrayList<Long> timeWaveBoom;
    private ArrayList<KeyCode> keyCodes = new ArrayList<>();

    private MainPlayer player;
//    private Enemy[] enemy = new Enemy[6];
    private Enemy enemy;

    public final Image MY_IMAGE= ImageUtils.loadImage("src/main/resources/images/background.jpg");
    public GameManager() {

    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.close();

        time_Start_Game = System.nanoTime();

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


        player = new MainPlayer(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE);
        enemy = new Enemy(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE, 0);
        arrEnemy.add(enemy);
        readTxtMap();
        createGameLoop();
        createKeyListeners();
        mainStage.show();
    }

    /**
     * Xử lí các thao tác trên bàn phím , các nút bấm
     * Di chuyển nhân vật
     */
    private void createKeyListeners() {
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
              @Override
              public void handle(KeyEvent keyEvent) {
                  KeyCode code = keyEvent.getCode();
                  if (!keyCodes.contains(code)) keyCodes.add(code); // nếu trong list keycode chưa có thì add
                  if(code == KeyCode.SPACE) {
                      addBombToPlayer(System.nanoTime());
                  }
                  if(code == KeyCode.DIGIT1) {
                      player.setIsDie(false,0); // Item kim làm nổ bóng
                  }
              }
        });

        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if (keyCodes.contains(code)) keyCodes.remove(code); // nếu trong list keycode  có thì remove
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

    public void checkGameOver() {
        if(MainPlayer.gameOver == true) {
            mainStage.close();
            gameTimer.stop();
            menuStage.show();
        }
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
                } catch (IndexOutOfBoundsException e) {

                }

                timeWaveBoom.add(System.nanoTime());
            }
        }

    }

    public void bombBangTime () {
        for(int i = 0 ; i < timeWaveBoom.size() ; i++) {
            double k = (System.nanoTime() - timeWaveBoom.get(i)) / Math.pow(10,9);
            if(k >= time_Wave_Bomb) {
                arrWaveBoom.remove(i);
                timeWaveBoom.remove(i);
            } else {
                for (WaveBoom waveBoom : arrWaveBoom) {
                    waveBoom.checkExplodeBoom_Enemy(arrEnemy);
                    waveBoom.checkBoom_Player(player, System.nanoTime());
                }
            }

        }
    }


    /**
     * Update Image, move, time ...
     */
    public void update() {
        checkGameOver();
        player.movePlayer(arrTileMap,arrBoom,keyCodes);
        enemy.moveEnemy(arrTileMap);
        checkTimeBombExplode();
        bombBangTime();
    }


    public void addBombToPlayer(long time_start) {
        if(arrBoom.size() < player.getAmountBomb()) {
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

        drawPlayer();
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

    public void drawPlayer() {
        if(player.getIsDie()) {
            time_Die_Player = (System.nanoTime() - player.getTimeBomberDie()) / Math.pow(10, 9);
            if (time_Die_Player > time_Bomber_Die) {
                player.drawBomberDie(gContext);
            } else {
                player.drawBomberDie_WaitItem(gContext);
            }
        } else {
            player.drawMainPlayer(gContext);
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

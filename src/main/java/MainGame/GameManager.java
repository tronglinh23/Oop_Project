package MainGame;

import Enemy.*;
import Item_Bomb.*;
import GUI.viewManager;
import Map.TileMap;
import Others.*;
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
import javafx.util.Pair;
import javax.sound.sampled.Clip;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameManager {
    public final static int WIDTH_SCREEN = 945;
    public final static int HEIGHT_SCREEN = 855;

    private final static int time_Bomb = 2;

    private final static int time_Wave_Bomb = 1;

    private final static int time_Bomber_Die = 3;

    private final static int timeImmortality = 2;

    private static int run_1_time ;

    private static long time_Start_Game;

    private int timeOctopus;
    private int timeHanabi;
    private double time_Die_Player;

    private long timeUsedKim;

    private final static String Title_game = "Boom Online";

    // khoi tao scene theo level
    public static int level_Game = 0;
    public static boolean is_check = false;
    private final String[] sound_Game = {
            "src/main/resources/sounds/gameplay1.wav",
            "src/main/resources/sounds/gameplay2.wav"
    };
    private final String[] background_Game = {
            "src/main/resources/map1/background1.png",
            "src/main/resources/map2/background2.png"
    };
    private final String[] map_Game = {
            "src/main/resources/map1/map1.txt",
            "src/main/resources/map2/map2.txt"
    };

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
    private ArrayList<KeyCode> keyCodes;

    private ArrayList<ItemGame> arrItemGame;

    private MainPlayer player;
    private Ghost[] ghost = new Ghost[3];
    private Present present;
    private Octopus octopus;
    private Hanabi hanabi;
    private Fast fast;
    private Find find;

    private Boolean pauseGame;

    ArrayList<Pair<Integer,Integer>> ranDomLocate;
    Clip soundGame;

    public final Image MY_IMAGE= ImageUtils.loadImage(background_Game[level_Game]);
    public GameManager() {

    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.close();
        time_Start_Game = System.nanoTime();
        pauseGame = false;

        run_1_time = 1;
        timeOctopus = 0;
        timeHanabi = 0;
        timeUsedKim = time_Start_Game;

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
        keyCodes = new ArrayList<>();
        arrItemGame = new ArrayList<>();
        ranDomLocate = new ArrayList<>();

        run_1_time = 1; // chay 1s trc khi out ra man hinh menu

        readTxtMap();
        createLocateRanDomItem();
        //Load sound game
        soundGame = SoundLoad.getSoundVolume(sound_Game[level_Game], -10);
        soundGame.loop(10);
        soundGame.start();

        player = new MainPlayer(WIDTH_SCREEN/2 - 20,HEIGHT_SCREEN- 50-TileMap.SIZE);
        createEnemy();
        createGameLoop();
        createKeyListeners();
        mainStage.show();
    }

    public void createEnemy() {
        if (level_Game == 0) {
            ghost[0] = new Ghost(135, 585);
            ghost[1] = new Ghost(675,90);
            ghost[2] = new Ghost(675,720);
            for (int i = 0; i < 3; i++) {
                arrEnemy.add(ghost[i]);
            }

            present = new Present(225, 585);
            arrEnemy.add(present);

            fast = new Fast(270, 630);
            arrEnemy.add(fast);
            fast.setLifeEnemy(2);
        }
        else if (level_Game == 1) {
            octopus = new Octopus(180, 630);
            octopus.setLifeEnemy(3);
            arrEnemy.add(octopus);

            hanabi = new Hanabi(45 * 3, 5 * 45);
            arrEnemy.add(hanabi);

            find = new Find(945 - 45 * 3, 5 * 45);
            find.setLifeEnemy(2);
            arrEnemy.add(find);
        }
    }

    public int autoRandomLocate() {
        Random random = new Random();
        int x = random.nextInt(ranDomLocate.size() - 1);
        return x;

    }

    private void createLocateRanDomItem() {
        // Random vi tri cho items
        int x1 = autoRandomLocate();
        arrItemGame.add(new ItemGame(ranDomLocate.get(x1).getKey(),ranDomLocate.get(x1).getValue(), 0));
        ranDomLocate.remove(x1);

        int x2 = autoRandomLocate();
        arrItemGame.add(new ItemGame(ranDomLocate.get(x2).getKey(),ranDomLocate.get(x2).getValue(),1));
        ranDomLocate.remove(x2);

        int x3 = autoRandomLocate();
        arrItemGame.add(new ItemGame(ranDomLocate.get(x3).getKey(),ranDomLocate.get(x3).getValue(),2));
        ranDomLocate.remove(x3);

        int x4 = autoRandomLocate();
        arrItemGame.add(new ItemGame(ranDomLocate.get(x4).getKey(),ranDomLocate.get(x4).getValue(),3));
        ranDomLocate.remove(x4);

        int x5 = autoRandomLocate();
        arrItemGame.add(new ItemGame(ranDomLocate.get(x5).getKey(),ranDomLocate.get(x5).getValue(),4));
        ranDomLocate.remove(x5);
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
                  if(code == KeyCode.SPACE && !player.getIsDie()) {
                      addBombToPlayer(System.nanoTime());
                  }
                  if(code == KeyCode.DIGIT1) {
                      // kiểm tra nếu player die và số kim lớn hơn 1 thì sau khi player die đc 0.2s thì có thể dùng kim
                      if (player.getIsDie() && player.getKim() > 0) {
                          Clip soundRes = SoundLoad.getSoundVolume("src/main/resources/sounds/resurrection.wav", 0);
                          soundRes.start();
                          timeUsedKim = System.nanoTime();
                          player.setIsDie(false, 0); // Item kim làm nổ bóng
                          player.setKim(-1);
                          System.out.println("Kim : " + player.getKim());
                      }
                  }

                  // xu li am thanh ki muon tat mo
                  if (code == KeyCode.M) {
                      if(soundGame.isRunning()) soundGame.stop();
                      else soundGame.start();
                  }

                  if (code == KeyCode.ESCAPE) {
                      if (!pauseGame) {
                          gameTimer.stop();
                          soundGame.stop();
                          pauseGame = true;
                          Image pauseImg = ImageUtils.loadImage("src/main/resources/BackgroundGame/PausedBackground.png");
                          gContext.drawImage(pauseImg,260,350);
                      } else {
                          gameTimer.start();
                          soundGame.start();
                          pauseGame = false;
                      }
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
                renderer();
                update();
            }
        };
        gameTimer.start();

    }

    public void checkGameOver() {
        if(MainPlayer.gameOver == true) {
            if(level_Game != 0) level_Game = 0;
            soundGame.stop();
            mainStage.close();
            gameTimer.stop();
            viewManager viewManager = new viewManager();
            menuStage = viewManager.getMainStage();
            menuStage.show();
        }
    }

    public void upLevelGame() {
        if(level_Game == 1 && !is_check) {
            soundGame.stop();
            gameTimer.stop();
            try {
                TimeUnit.SECONDS.sleep(1); // sleep chuyen level
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            GameManager gameStage = new GameManager();
            gameStage.createNewGame(mainStage);
            is_check = true;
            TileMap.levelGame++;
        } else if (level_Game == 2) {
            soundGame.stop();
            Clip soundWin = SoundLoad.getSoundVolume("src/main/resources/sounds/winGame.wav", -5);
            soundWin.start();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            MainPlayer.gameOver = true;
            checkGameOver();
        }
    }

    public void checkTimeBombExplode() {
        for(int i = 0 ; i < arrBoom.size() ; i++) {
            double time = (System.nanoTime() - TimeBombStart.get(i)) / Math.pow(10,9);
            if(time >= time_Bomb) {
                Clip clip = SoundLoad.getSoundVolume("src/main/resources/sounds/boom_bang.wav", -15);
                clip.start();
                WaveBoom waveBoom = arrBoom.get(i).boomBang();
                arrBoom.remove(i);
                TimeBombStart.remove(i);
                arrWaveBoom.add(waveBoom);
                timeWaveBoom.add(System.nanoTime());
            }
        }
    }

    public void checkTimeHanabiExplode(long time_start) {
        int time = (int) ((time_start - time_Start_Game) / Math.pow(10,9));
        if (time != timeHanabi) {
            timeHanabi = time;
            if (timeHanabi % 10 == 0) {
                for (int i = 0; i < arrEnemy.size(); i++) {
                    if (arrEnemy.get(i) instanceof Hanabi) {
                        WaveBoom waveBoom = hanabi.boomBang();
                        arrEnemy.remove(i);
                        arrWaveBoom.add(waveBoom);
                        timeWaveBoom.add(System.nanoTime());
                    }
                }
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
                    waveBoom.checkExplodeBoom_Boom(arrBoom, TimeBombStart);
                    waveBoom.checkExplodeBoom_Enemy(arrEnemy, arrItemGame);
                    waveBoom.checkBoom_Player(player, System.nanoTime());
                }
            }

        }
    }

    public void changeSpeedFast(long time_start) {
        int time = (int) ((time_start - time_Start_Game) / Math.pow(10,9));
        if (time % 5 == 0) {
            fast.setSpeed(8);
        } else {
            fast.setSpeed(2);
        }
    }

    public void addBombToPlayer(long time_start) {
        if(arrBoom.size() < player.getAmountBomb()) {
            if(player.getIscoBomb(arrBoom)) {
                Clip clip = SoundLoad.getSoundVolume("src/main/resources/sounds/set_boom.wav", -15);
                clip.start();
                Boom boom = player.setupBoom();
                arrBoom.add(boom);
                TimeBombStart.add(time_start);
            }
        }
    }

    public void OctopusAddBomb(long time_start) {
        if(!octopus.getIsDie()) {
            int time = (int) ((time_start - time_Start_Game) / Math.pow(10,9));
            if (time != timeOctopus) {
                timeOctopus = time;
                if (timeOctopus % 5 == 0) {
                    Clip clip = SoundLoad.getSoundVolume("src/main/resources/sounds/set_boom.wav", -15);
                    clip.start();
                    Boom boom = octopus.setupBoom(player.getX(), player.getY());
                    arrBoom.add(boom);
                    TimeBombStart.add(time_start);
                }
            }
        }
    }

    public void immortalPlayerCheck() {
        if ((System.nanoTime() - timeUsedKim) / Math.pow(10,9) < timeImmortality) {
            player.setIsDie(false, 0);
        }
    }

    /**
     * Update Image, move, time ...
     */
    public void update() {
        checkGameOver();
        upLevelGame();

        player.movePlayer(arrTileMap,arrBoom,keyCodes);


        for (Enemy enemy : arrEnemy) {
            enemy.moveEnemy(player, arrTileMap, arrBoom);
        }

        if (level_Game == 0 && fast != null) {
            changeSpeedFast(System.nanoTime());
        }
        if (level_Game == 1) {
            OctopusAddBomb(System.nanoTime());
        }
        immortalPlayerCheck();

    }

    /**
     * Render all image to screen.
     */
    public void renderer() {
        createBackground();

        for (int itemGame = 0 ; itemGame < arrItemGame.size() ; itemGame++) {
            arrItemGame.get(itemGame).drawItem(gContext);
            if(arrItemGame.get(itemGame).handLeItem(player,arrEnemy)){
                arrItemGame.remove(itemGame);
            }
        }

        drawTileMap();
        checkTimeHanabiExplode(System.nanoTime());

        checkTimeBombExplode();

        for (WaveBoom waveBoom : arrWaveBoom) {
            waveBoom.draw(arrTileMap, gContext);
        }

        for (Boom boom : arrBoom) {
            boom.draw(gContext);
        }

        bombBangTime();

        for (Enemy enemy1 : arrEnemy) {
            enemy1.drawEnemy(gContext);
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
                if(run_1_time > 0) {
                    Clip soundBomberDie = SoundLoad.getSoundVolume("src/main/resources/sounds/bomberdie.wav", 0);
                    soundBomberDie.start();
                    run_1_time--;
                }
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
            File file = new File(map_Game[level_Game]);
            int countLine = 0;
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            while(line != null) {
                for (int chr = 0; chr < line.length(); chr++) {
                    arrTileMap.add(new TileMap(chr*TileMap.SIZE, countLine*TileMap.SIZE,
                            Integer.parseInt(String.valueOf(line.charAt(chr)))));
//                    if(level_Game == 0 && (line.charAt(chr) == '3' || line.charAt(chr) == '4' ||
//                            line.charAt(chr) == '5' || line.charAt(chr) == '6')) {
//                        ranDomLocate.add(new Pair<>(chr * TileMap.SIZE, countLine * TileMap.SIZE));
//                    } else if (level_Game == 1 && (line.charAt(chr) == '1' || line.charAt(chr) == '2')) {
//                        ranDomLocate.add(new Pair<>(chr * TileMap.SIZE, countLine * TileMap.SIZE));
//                    }
                    if (line.charAt(chr) == '0') {
                        ranDomLocate.add(new Pair<>(chr * TileMap.SIZE, countLine * TileMap.SIZE));
                    }
                }

                line = reader.readLine();
                countLine++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

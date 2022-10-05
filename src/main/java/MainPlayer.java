import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class MainPlayer extends BaseObject{
    public static boolean gameOver;

    private int soBoom;
    private double speed;

    private int lengthBomb;

    private int kim;
    private boolean isPlayerRun;

    private final int size_player = 45;

    private int amountBomb;

    private static boolean isCoBombSan;
    
    private int imageCount;
    private int imageDieCount;

    private int event;

    private boolean isDie;

    private Long timeBomberDie;



    public final Image[] IMAGE_BONGMO = {
            ImageUtils.loadImage("src/main/resources/images/BM_1.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_2.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_3.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_4.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_5.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_6.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_7.png"),
    };

    public final Image[] IMG_LUA_EFFECT = {
            ImageUtils.loadImage("src/main/resources/images/lua_1.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_2.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_3.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_4.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_5.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_6.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_7.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_8.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_9.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_10.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_11.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_12.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_13.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_14.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_15.png"),
            ImageUtils.loadImage("src/main/resources/images/lua_16.png"),
    };
    public final Image[] PLAYER_LEFT_IMG = {
            ImageUtils.loadImage("src/main/resources/images/player_left_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_5.png"),
    };

    public final Image[] PLAYER_RIGHT_IMG = {
            ImageUtils.loadImage("src/main/resources/images/player_right_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_5.png"),
    };
    public final Image[] PLAYER_UP_IMG = {
            ImageUtils.loadImage("src/main/resources/images/player_up_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_5.png"),
    };

    public final Image[] PLAYER_DOWN_IMG = {
            ImageUtils.loadImage("src/main/resources/images/player_down_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_5.png"),
    };

    public final Image[] IMG_BOMBER_DIE_TIME = {
            ImageUtils.loadImage("src/main/resources/Bomberdie/bomber_dead_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomberdie/bomber_dead_2.png"),
    };

    public final Image[] IMG_BOMBER_DIE = {
            ImageUtils.loadImage("src/main/resources/Bomberdie/boomber_die_01.png"),
            ImageUtils.loadImage("src/main/resources/Bomberdie/boomber_die_02.png"),
            ImageUtils.loadImage("src/main/resources/Bomberdie/boomber_die_03.png"),
            ImageUtils.loadImage("src/main/resources/Bomberdie/boomber_die_04.png"),
            ImageUtils.loadImage("src/main/resources/Bomberdie/boomber_die_04.png"),
    };

    public MainPlayer(int x, int y) {
        super(x,y);
        gameOver = false;
        soBoom = 2;
        speed = 2;
        lengthBomb = 1;
        kim = 0;
        isPlayerRun = false;
        amountBomb = 2;
        isCoBombSan = false;
        imageCount = 0;
        imageDieCount = 0;
        isDie = false;
        event = 4;
    }
    public void setIsDie(boolean isDie, long time) {
        this.isDie = isDie;
        if(isDie) {
            this.isPlayerRun = false;
            this.timeBomberDie = time;
        }
    }
    public Long getTimeBomberDie() {
        return this.timeBomberDie;
    }
    public boolean getIsDie() {return this.isDie;}

    public int getAmountBomb() { return this.amountBomb;}
    public void setAmountBomb() {this.amountBomb += 1;}

    public int getLengthBomb() {return this.lengthBomb;}
    public void setLengthBomb() {this.lengthBomb += 1;}

    public void setSpeed() {this.speed += 0.5;}

    public void setKim(int k) {this.kim += k;}
    public int getKim() {return this.kim;}

    public Rectangle getRect() {
        Rectangle mainPlayer = new Rectangle(x + 10,y+15, size_player - 10, size_player - 15);
        return mainPlayer;
    }

    public boolean isRun() {
        return isPlayerRun;
    }
    
    public void drawBomberDie(GraphicsContext gc) {
        gc.drawImage(IMG_BOMBER_DIE[imageDieCount / 10 % IMG_BOMBER_DIE.length], x - 5, y - 5);
        if(IMG_BOMBER_DIE[imageDieCount / 10 % IMG_BOMBER_DIE.length] == IMG_BOMBER_DIE[IMG_BOMBER_DIE.length - 1]) {
            gameOver = true;
        }
        imageDieCount++;
    }
    public void drawBomberDie_WaitItem(GraphicsContext gc) {
        gc.drawImage(IMG_BOMBER_DIE_TIME[imageCount / 10 % IMG_BOMBER_DIE_TIME.length], x - 5, y - 5);
        imageCount++;
    }
    public void drawMainPlayer(GraphicsContext gc) {

        switch (event) {
            case 1 :
                if (!isRun()){
                    gc.drawImage(PLAYER_LEFT_IMG[0],x,y,size_player+5,size_player+15);
                } else {
                    imageCount++;
                    gc.drawImage(PLAYER_LEFT_IMG[imageCount / 10 % PLAYER_LEFT_IMG.length], x, y, size_player + 5, size_player + 15);
                    gc.drawImage(IMAGE_BONGMO[imageCount / 10 % IMAGE_BONGMO.length], x + 35, y + 15, size_player, size_player);
                }
                break;
            case 2 :
                if (!isRun()){
                    gc.drawImage(PLAYER_RIGHT_IMG[0],x,y,size_player+5,size_player+15);
                } else {
                    imageCount++;
                    gc.drawImage(PLAYER_RIGHT_IMG[imageCount / 10 % PLAYER_RIGHT_IMG.length], x, y, size_player + 5, size_player + 15);
                    gc.drawImage(IMAGE_BONGMO[imageCount / 10 % IMAGE_BONGMO.length], x - 35, y + 15, size_player, size_player);
                }
                break;
            case 3 :
                if (!isRun()){
                    gc.drawImage(PLAYER_UP_IMG[0],x,y,size_player+5,size_player+15);
                }
                else {
                    imageCount++;
                    gc.drawImage(PLAYER_UP_IMG[imageCount / 10 % PLAYER_UP_IMG.length], x, y,size_player+5,size_player+15);
                    gc.drawImage(IMAGE_BONGMO[imageCount / 10 % IMAGE_BONGMO.length],x,y+25,size_player,size_player);
                }
                break;
            case 4 :
                if (!isRun()){
                    gc.drawImage(PLAYER_DOWN_IMG[0],x,y,size_player+5,size_player+15);
                } else {
                    imageCount++;
                    gc.drawImage(PLAYER_DOWN_IMG[imageCount / 10 % PLAYER_DOWN_IMG.length], x, y,size_player+5,size_player+15);
                    gc.drawImage(IMAGE_BONGMO[imageCount / 10 % IMAGE_BONGMO.length], x,y-30,size_player,size_player);
                }
                break;
        }
        gc.drawImage(IMG_LUA_EFFECT[imageCount/10 % IMG_LUA_EFFECT.length],x-5,y,size_player+20,size_player+20);
        isPlayerRun=false;
        imageCount++;
    }

    public boolean checkEnemy_Player(ArrayList<Enemy> arrEnemy){
        for (int i=0;i<arrEnemy.size();i++){
            if (getRect().getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())){
                return true;
            }
        }
        return false;
    }


    public void movePlayer(ArrayList<TileMap>arrTileMap, ArrayList<Boom> arrBomb, ArrayList<KeyCode> keyCodes) {
        if(!isDie) {
            double xChange = this.x;
            double yChange = this.y;

            if(keyCodes.contains(KeyCode.LEFT)) {
                xChange -= speed;
                event = Move.LEFT.getIndex();
                isPlayerRun = true;
            }

            if (keyCodes.contains(KeyCode.RIGHT)) {
                xChange += speed;
                event = Move.RIGHT.getIndex();
                isPlayerRun = true;
            }

            if (keyCodes.contains(KeyCode.UP)) {
                yChange -= speed;
                event = Move.UP.getIndex();
                isPlayerRun = true;
            }

            if (keyCodes.contains(KeyCode.DOWN)) {
                yChange += speed;
                event = Move.DOWN.getIndex();
                isPlayerRun = true;
            }

            if (!keyCodes.contains(KeyCode.DOWN)
                    && !keyCodes.contains(KeyCode.UP)
                    && !keyCodes.contains(KeyCode.LEFT)
                    && !keyCodes.contains(KeyCode.RIGHT)) {
                isPlayerRun = false;
            }

            double xRaw=x;
            double yRaw=y;
            x=xChange;
            y=yChange;
            boolean collisionMap = checkCollisionMap(arrTileMap);
            boolean collisionBomb = checkCollisionBomb(arrBomb);

            if(collisionMap) {
                x = xRaw;
                y = yRaw;
            }
            if(collisionBomb) {
                x = xRaw;
                y = yRaw;
            }

            if(xChange <= 0 || xChange >= (GameManager.WIDTH_SCREEN - size_player - 10)
                    || yChange <= 0 || yChange >= (GameManager.HEIGHT_SCREEN - size_player - 10)) {
                x = xRaw;
                y = yRaw;
            }
        }
    }

    public boolean checkCollisionMap(ArrayList<TileMap>arrTileMap) {
        for(TileMap tileMap : arrTileMap) {
            if(tileMap.locate_bit == 1 || tileMap.locate_bit == 2 || tileMap.locate_bit == 3 ||
                    tileMap.locate_bit == 4 || tileMap.locate_bit == 5 || tileMap.locate_bit == 6 ||
                    tileMap.locate_bit == 7 || tileMap.locate_bit == 8 || tileMap.locate_bit == 9) {
                if(getRect().getBoundsInParent().intersects(tileMap.getRect().getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Kiểm tra xem ở vị trí đấy đã có bomb chưa, có rồi thì không thêm vào.
     * @param arrBoom array Bombs
     * @return true/false
     */
    public boolean getIscoBomb(ArrayList<Boom> arrBoom) {
        for (Boom boom : arrBoom) {
            if (getRect().getBoundsInParent().intersects(boom.getRect().getBoundsInParent())) {
                return false;
            }
        }
        return  true;
    }
    /**
     * Y tuong check va cham bomb.
     * ban đầu va chạm là lúc đặt bomb, ta sẽ thêm một kiểu int để lưu việc nó đã hết chạm lần đầu chưa
     * Nhân vật vẫn có thể di chuyển khi đặt bomb
     * Nhưng sau khi đi ra khỏi quả bomb thì ta xét biến int = 0, đánh dấu lại
     * Lần sau khi va chạm thì sẽ k đi qua được
     * @param arrBoom true/false
     */

    public void setMoveBomb(ArrayList<Boom> arrBoom) {
        for (Boom boom : arrBoom) {
            if(!getRect().getBoundsInParent().intersects(boom.getRect().getBoundsInParent())) {
                boom.setIsCheckBomb(0);
            }
        }
    }
    public boolean checkCollisionBomb(ArrayList<Boom> arrBoom) {
        setMoveBomb(arrBoom);
        for (Boom boom : arrBoom) {
            if(getRect().getBoundsInParent().intersects(boom.getRect().getBoundsInParent()) && boom.getIsCheckBomb() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Đặt bomb chỗ nhân vật.
     * Đặt gần nhất với các ô vuông.
     * Size = 50.
     * Thuật toán ví dụ trên x, ta sẽ kiểm tra xem x đang nằm nghiêng về bên phải hay bên trái hơn.
     * nếu nghiêng về bên trái hơn thì ta cộng vào Size/2 thì nó vẫn bé hơn => bomb sẽ được đặt vào ô trái.
     * ngược lại nếu nghiêng về phải hơn thì bomb sẽ đc đặt sang bên phải.
     */
    public Boom setupBoom() {
        int xRaw = (int)this.x + Boom.Size/2;
        int yRaw = (int)this.y + Boom.Size/2;
        int locateX = xRaw - xRaw % Boom.Size;
        int locateY = yRaw - yRaw % Boom.Size;
        Boom bomb = new Boom(locateX, locateY, lengthBomb);
        return bomb;
    }
}

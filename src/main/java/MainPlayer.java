import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;


public class MainPlayer {
    private int x;
    private int y;

    private int soBoom = 2;
    private int speed = 2;
    private boolean isPlayerRun = false;

    private final int LEFT=1;
    private final int RIGHT=2;
    private final int UP=3;
    private final int DOWN=4;
    private final int size_player = 45;

    private int amountBomb = 2;

    private static boolean isCoBombSan = false;
    private int imageIndex = 0;
    private int event = 4;


    public final Image[] IMAGE_BONGMO = {
            ImageUtils.loadImage("src/main/resources/images/BM_1.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_2.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_3.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_4.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_5.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_6.png"),
            ImageUtils.loadImage("src/main/resources/images/BM_7.png"),
    };

    public final Image[] IMAGES_HIEUUNG = {
            ImageUtils.loadImage("src/main/resources/images/hieuUng_11.png"),
            ImageUtils.loadImage("src/main/resources/images/hieuUng_12.png"),
            ImageUtils.loadImage("src/main/resources/images/hieuUng_13.png"),
            ImageUtils.loadImage("src/main/resources/images/hieuUng_14.png"),
            ImageUtils.loadImage("src/main/resources/images/hieuUng_15.png"),
            ImageUtils.loadImage("src/main/resources/images/hieuUng_16.png"),
            ImageUtils.loadImage("src/main/resources/images/hieuUng_17.png"),
            ImageUtils.loadImage("src/main/resources/images/hieuUng_18.png"),
    };
    public final Image[] IMAGES_PLAYER_LEFT= {
            ImageUtils.loadImage("src/main/resources/images/player_left_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_left_5.png"),
    };

    public final Image[] IMAGES_PLAYER_RIGHT= {
            ImageUtils.loadImage("src/main/resources/images/player_right_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_right_5.png"),
    };
    public final Image[] IMAGES_PLAYER_UP= {
            ImageUtils.loadImage("src/main/resources/images/player_up_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_up_5.png"),
    };

    public final Image[] IMAGES_PLAYER_DOWN= {
            ImageUtils.loadImage("src/main/resources/images/player_down_1.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_2.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_3.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_4.png"),
            ImageUtils.loadImage("src/main/resources/images/player_down_5.png"),
    };

    public MainPlayer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }
    public int getX() {
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getY() {
        return this.y;
    }


    public int getAmountBomb() { return this.amountBomb;}
    public void setAmountBomb(int k) {this.amountBomb += k+1;}

    public Rectangle getRect() {
        Rectangle mainPlayer = new Rectangle(x + 10,y+25, size_player - 10, size_player - 15);
        return mainPlayer;
    }

    public boolean isRun() {
        return isPlayerRun;
    }
    public void drawMainPlayer(GraphicsContext gc) {

        switch (event) {
            case 1 :
                if (!isRun()){
                    gc.drawImage(IMAGES_PLAYER_LEFT[0],x,y,size_player+5,size_player+15);
                } else {
                    imageIndex++;
                    gc.drawImage(IMAGES_PLAYER_LEFT[imageIndex / 10 % IMAGES_PLAYER_LEFT.length], x, y, size_player + 5, size_player + 15);
                    gc.drawImage(IMAGE_BONGMO[imageIndex / 10 % IMAGE_BONGMO.length], x + 35, y + 15, size_player, size_player);
                }
                break;
            case 2 :
                if (!isRun()){
                    gc.drawImage(IMAGES_PLAYER_RIGHT[0],x,y,size_player+5,size_player+15);
                } else {
                    imageIndex++;
                    gc.drawImage(IMAGES_PLAYER_RIGHT[imageIndex / 10 % IMAGES_PLAYER_RIGHT.length], x, y, size_player + 5, size_player + 15);
                    gc.drawImage(IMAGE_BONGMO[imageIndex/10 % IMAGE_BONGMO.length], x - 35, y + 15, size_player, size_player);
                }
                break;
            case 3 :
                if (!isRun()){
                    gc.drawImage(IMAGES_PLAYER_UP[0],x,y,size_player+5,size_player+15);
                }
                else {
                    imageIndex++;
                    gc.drawImage(IMAGES_PLAYER_UP[imageIndex / 10 % IMAGES_PLAYER_UP.length], x, y,size_player+5,size_player+15);
                    gc.drawImage(IMAGE_BONGMO[imageIndex/10 % IMAGE_BONGMO.length],x,y+25,size_player,size_player);
                }
                break;
            case 4 :
                if (!isRun()){
                    gc.drawImage(IMAGES_PLAYER_DOWN[0],x,y,size_player+5,size_player+15);
                } else {
                    imageIndex++;
                    gc.drawImage(IMAGES_PLAYER_DOWN[imageIndex / 10 % IMAGES_PLAYER_LEFT.length], x, y,size_player+5,size_player+15);
                    gc.drawImage(IMAGE_BONGMO[imageIndex/10 % IMAGE_BONGMO.length],x,y-30,size_player,size_player);
                }
                break;
        }
        isPlayerRun=false;
        imageIndex++;
        gc.drawImage(IMAGES_HIEUUNG[imageIndex/10 % IMAGES_HIEUUNG.length],x-5,y,size_player+20,size_player+20);
    }

    public void movePlayer(ArrayList<TileMap>arrTileMap, ArrayList<Boom> arrBomb, ArrayList<KeyCode> keyCodes) {

        int xChange = this.x;
        int yChange = this.y;

        if(keyCodes.contains(KeyCode.LEFT)) {
            xChange -= speed;
            event = LEFT;
            isPlayerRun = true;
        }

        if (keyCodes.contains(KeyCode.RIGHT)) {
            xChange += speed;
            event = RIGHT;
            isPlayerRun = true;
        }

        if (keyCodes.contains(KeyCode.UP)) {
            yChange -= speed;
            event = UP;
            isPlayerRun = true;
        }

        if (keyCodes.contains(KeyCode.DOWN)) {
            yChange += speed;
            event = DOWN;
            isPlayerRun = true;
        }

        if (!keyCodes.contains(KeyCode.DOWN)
                && !keyCodes.contains(KeyCode.UP)
                && !keyCodes.contains(KeyCode.LEFT)
                && !keyCodes.contains(KeyCode.RIGHT)) {
            isPlayerRun = false;
        }

        int xRaw=x;
        int yRaw=y;
        x=xChange;
        y=yChange;
        boolean checkMapMove = checkMoveMap(arrTileMap);
        boolean checkMoveBomb = checkMoveBomb(arrBomb);
        if(checkMapMove) {
            x = xRaw;
            y = yRaw;
        }
        if(checkMoveBomb) {
            x = xRaw;
            y = yRaw;
        }
    }

    public boolean checkMoveMap(ArrayList<TileMap>arrTileMap) {
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
    public boolean checkMoveBomb(ArrayList<Boom> arrBoom) {
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
     * Size = 45.
     * Thuật toán ví dụ trên x, ta sẽ kiểm tra xem x đang nằm nghiêng về bên phải hay bên trái hơn.
     * nếu nghiêng về bên trái hơn thì ta cộng vào Size/2 thì nó vẫn bé hơn => bomb sẽ được đặt vào ô trái.
     * ngược lại nếu nghiêng về phải hơn thì bomb sẽ đc đặt sang bên phải.
     */
    public Boom setupBoom() {
        int xRaw = this.x + Boom.Size/2;
        int yRaw = this.y + Boom.Size/2;
        int locateX = xRaw - xRaw % Boom.Size;
        int locateY = yRaw - yRaw % Boom.Size;
        Boom bomb = new Boom(locateX, locateY, 1);
        return bomb;
    }
}

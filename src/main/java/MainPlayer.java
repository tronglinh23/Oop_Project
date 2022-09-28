import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;


public class MainPlayer {
    private int x;
    private int y;
    private int soBoom = 2;
    private int speed = 3;
    private int timeMove;
    private boolean isPlayerRun = false;

    private int orient;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    private ImageView player;
    private final int size_player = 50;

    private int amountBomb = 2;

    private static boolean isCoBombSan = false;
    private AnchorPane playerPane;
    private Scene playerScene;
    private Stage playerStage;
    private int imageIndex;

//
//    public final Image[] IMAGE_BONGMO = {
//            new ImageIcon(getClass().getResource("/images/BM_1.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/BM_2.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/BM_3.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/BM_4.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/BM_5.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/BM_6.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/BM_7.png")).getImage(),
//    };
//
//    public final Image[] IMAGES_HIEUUNG = {
//            new ImageIcon(getClass().getResource("/images/hieuUng_11.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/hieuUng_12.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/hieuUng_13.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/hieuUng_14.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/hieuUng_15.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/hieuUng_16.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/hieuUng_17.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/hieuUng_18.png")).getImage(),
//    };
//
//    public final Image[] IMAGES_PLAYER_RIGHT= {
//            new ImageIcon(getClass().getResource("/images/player_right_1.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_right_2.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_right_3.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_right_4.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_right_5.png")).getImage(),
//    };
//    public final Image[] IMAGES_PLAYER_UP= {
//            new ImageIcon(getClass().getResource("/images/player_up_1.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_up_2.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_up_3.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_up_4.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_up_5.png")).getImage(),
//    };
//
//    public final Image[] IMAGES_PLAYER_DOWN= {
//            new ImageIcon(getClass().getResource("/images/player_down_1.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_down_2.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_down_3.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_down_4.png")).getImage(),
//            new ImageIcon(getClass().getResource("/images/player_down_5.png")).getImage(),
//    };

    public MainPlayer(int x, int y, int orient,AnchorPane gamePane, Scene gameScene) {

        this.x = x;
        this.y = y;
        this.orient = orient;
        this.playerPane = gamePane;
        this.playerScene = gameScene;

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

    public void setOrient(int orient){
        this.orient = orient;
    }
    public int getOrient() {
        return this.orient;
    }

    public int getAmountBomb() { return this.amountBomb;}
    public void setAmountBomb(int k) {this.amountBomb += k+1;}

    public Rectangle getRect() {
        Rectangle mainPlayer = new Rectangle(x,y+25, size_player - 10, size_player - 10);
        return mainPlayer;
    }

    public boolean isRun() {
        return isPlayerRun;
    }
    public void drawMainPlayer() {
        player = new ImageView("/images/player_down_1.png");
        player.setLayoutX(x);
        player.setLayoutY(y);
        player.setFitWidth(size_player);
        player.setFitHeight(size_player);
        playerPane.getChildren().add(player);
    }
    public void setLeftKeyPressed(boolean check) {
        this.isLeftKeyPressed = check;
        isPlayerRun = true;
    }

    public void setRightKeyPressed(boolean check) {
        this.isRightKeyPressed = check;
        isPlayerRun = true;
    }
    public void setUpKeyPressed(boolean check) {
        this.isUpKeyPressed = check;
        isPlayerRun = true;
    }
    public void setDownKeyPressed(boolean check) {
        this.isDownKeyPressed = check;
        isPlayerRun = true;
    }
    public void movePlayer(ArrayList<TileMap>arrTileMap, ArrayList<Boom> arrBomb) {
        int xChange = this.x;
        int yChange = this.y;

        if (isLeftKeyPressed && !isRightKeyPressed) {
            xChange -= speed;
        }

        if (isRightKeyPressed && !isLeftKeyPressed) {
            xChange += speed;
        }

        if (isUpKeyPressed && !isDownKeyPressed) {
            yChange -= speed;
        }

        if (isDownKeyPressed && !isUpKeyPressed) {
            yChange += speed;
        }

        int xRaw = x;
        int yRaw = y;
        x = xChange;
        y = yChange;
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
        player.setLayoutX(x);
        player.setLayoutY(y);
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
        Boom bomb = new Boom(locateX, locateY, 4 ,playerPane);
        return bomb;
    }






}

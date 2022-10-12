package Enemy;
import Others.*;
import Base.BaseObject;
import Item_Bomb.Boom;
import Map.TileMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends BaseObject{
    public int speed = 2;
    private int orient;
    private Image enemy;
    private Random random = new Random();
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public final int size_enemy = 45;

    private boolean isDie;

    public final Image[] MY_ENEMY={
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_left.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_right.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_up.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_down.png")
    };
    public Enemy(int x, int y, int orient) {
        super(x,y);
        this.orient = orient;
        enemy = MY_ENEMY[0];
        isDie = false;
    }

    public Rectangle getRect() {
        Rectangle theEnemy = new Rectangle(x + 10,y+15, size_enemy - 10, size_enemy - 10);
        return theEnemy;
    }

    /**
     * tạo ra 1 orient mới để có thể chuyển hướng khi gặp chướng ngại vật.
     * set 1 biến random kiểu int có 4 giá trị.
     * mỗi giá trí ứng với 1 hướng di chuyển của enemy.
     */
    public void createOrient() {
        int rnd = random.nextInt(20);
        if (rnd > 15) {
            int newOrient = random.nextInt(4);
            setOrient(newOrient);
            enemy = MY_ENEMY[newOrient];
        }
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getOrient() {
        return this.orient;
    }

    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(enemy, x, y, size_enemy + 10, size_enemy + 10);
    }

    public void moveEnemy(ArrayList<TileMap> arrTileMap, ArrayList<Boom> arrBoom) {
        double xChange = x;
        double yChange = y;
        switch (orient) {
            case LEFT:
                xChange -= (double) speed / 4;
                break;
            case RIGHT:
                xChange += (double) speed / 4;
                break;
            case UP:
                yChange -= (double) speed / 4;
                break;
            case DOWN:
                yChange +=  (double) speed / 4;
            default:
        }
        double xRaw = x;
        double yRaw = y;
        x = xChange;
        y = yChange;

        boolean checkEnemyMap = checkCollisionMap(arrTileMap);
        boolean checkEnemyBomb = checkCollisionBomb(arrBoom);
        boolean checkEnemyFrame = checkCollisionFrame(xChange,yChange,size_enemy);
        if (checkEnemyMap || checkEnemyBomb || checkEnemyFrame) {
            x = xRaw;
            y = yRaw;
            createOrient();
        }
    }

    public void setIsDie(boolean isDie) {
        this.isDie = isDie;
    }
    public boolean getIsDie() {return this.isDie;}
}


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends BaseObject{
    private int speed = 1;
    private int orient;
    private final int size_enemy = 45;
    private Image enemy;
    private Random random = new Random();
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public final Image[] HAITAC_ENEMY_LEFT_IMG= {
            ImageUtils.loadImage("src/main/resources/Enemy/LEFT.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/LEFT_1.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/LEFT_2.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/LEFT_3.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/LEFT_4.png")
    };

    public final Image[] HAITAC_ENEMY_RIGHT_IMG= {
            ImageUtils.loadImage("src/main/resources/Enemy/RIGHT.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_1.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_2.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_3.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_4.png"),
    };

    public final Image[] HAITAC_ENEMY_UP_IMG= {
            ImageUtils.loadImage("src/main/resources/Enemy/UP.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/UP_1.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/UP_2.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/UP_3.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/UP_4.png"),
    };

    public final Image[] HAITAC_ENEMY_DOWN_IMG= {
            ImageUtils.loadImage("src/main/resources/Enemy/DOWN.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/DOWN_1.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/DOWN_2.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/DOWN_3.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/DOWN_4.png"),
    };
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
    }

    public Rectangle getRect() {
        Rectangle theEnemy = new Rectangle(x + 10,y+15, size_enemy - 10, size_enemy - 15);
        return theEnemy;
    }

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

        boolean checkEnemyMove = checkMoveMap(arrTileMap);
        boolean checkEnemyBomb = checkMoveEnemy_boom(arrBoom);

        if (checkEnemyMove) {
            x = xRaw;
            y = yRaw;
            createOrient();
        }

        if (checkEnemyBomb) {
            x = xRaw;
            y = yRaw;
            createOrient();
        }
    }

    public boolean checkMoveEnemy_boom(ArrayList<Boom> arrBoom) {
        for (Boom boom : arrBoom) {
            if(getRect().getBoundsInParent().intersects(boom.getRect().getBoundsInParent()) && boom.getIsCheckBomb() == 0) {
                return true;
            }
        }
        return false;
    }
    public boolean checkMoveMap(ArrayList<TileMap> arrTileMap) {
        for (TileMap tileMap : arrTileMap) {
            if (tileMap.locate_bit == 1 || tileMap.locate_bit == 2 || tileMap.locate_bit == 3 ||
                tileMap.locate_bit == 4 || tileMap.locate_bit == 5 || tileMap.locate_bit == 6 ||
                tileMap.locate_bit == 7 || tileMap.locate_bit == 8 || tileMap.locate_bit == 9 ) {
                if(getRect().getBoundsInParent().intersects(tileMap.getRect().getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }
}


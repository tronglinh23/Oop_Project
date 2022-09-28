import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private double x;
    private double y;
    private int speed = 1;
    private int orient;
    private final int size_enemy = 50;
    private Image enemy;
    private int locate;

    private int imageIndex;

    private Random random= new Random();

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public Enemy(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }

    public Enemy() {

    }

    Rectangle getRect() {
        Rectangle theEnemy = new Rectangle(x,y + 25, size_enemy - 20, size_enemy - 20);
        return theEnemy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return this.x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getOrient() {
        return this.orient;
    }

    public void drawEnemy(GraphicsContext gc) {
        enemy = ImageUtils.loadImage("src/main/resources/images/boss_ donw_1.png");
        gc.drawImage(enemy, x, y, size_enemy, size_enemy);
    }

    public void moveEnemy(ArrayList<TileMap> arrTileMap) {
        double xRaw = x;
        double yRaw = y;
        switch (orient) {
            case LEFT:
                xRaw -= (double) speed / 5;
                break;
            case RIGHT:
                xRaw += (double) speed / 5;
                break;
            case UP:
                yRaw -= (double) speed / 5;
                break;
            case DOWN:
                yRaw += (double) speed / 5;
            default:
        }
        double xChange = x;
        double yChange = y;
        x = xRaw;
        y = yRaw;

        boolean checkEnemyMove = checkMoveMap(arrTileMap);

        if (checkEnemyMove) {
            if (orient == LEFT) {
                orient = RIGHT;
            } else if (orient == RIGHT) {
                orient = UP;
            } else if (orient == UP) {
                orient = DOWN;
            } else {
                orient = LEFT;
            }
        }

    }

    public boolean checkMoveMap(ArrayList<TileMap> arrtileMap) {
        for (TileMap tileMap : arrtileMap) {
            if (tileMap.locate_bit == 5 || tileMap.locate_bit == 1 || tileMap.locate_bit == 2 ||
                tileMap.locate_bit == 3 || tileMap.locate_bit == 4 || tileMap.locate_bit == 6 ||
                tileMap.locate_bit == 7 || tileMap.locate_bit == 8 || tileMap.locate_bit == 9) {
                if(getRect().getBoundsInParent().intersects(tileMap.getRect().getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }
}


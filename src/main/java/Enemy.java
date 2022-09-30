import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Enemy {
    private double x;
    private double y;
    private int speed = 1;
    private int orient;
    private final int size_enemy = 45;
    private Image enemy;
    private Random random = new Random();
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public final Image[] MY_ENEMY={
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_left.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_right.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_up.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_down.png")
    };
    public Enemy(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        enemy = MY_ENEMY[0];
    }

    Rectangle getRect() {
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
        gc.drawImage(enemy, x, y, size_enemy, size_enemy);
    }

    public void moveEnemy(ArrayList<TileMap> arrTileMap) {
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
                yChange += (double) speed / 4;
            default:
        }
        double xRaw = x;
        double yRaw = y;
        x = xChange;
        y = yChange;

        boolean checkEnemyMove = checkMoveMap(arrTileMap);

        if (checkEnemyMove) {
            x = xRaw;
            y = yRaw;
            createOrient();
        }
    }



    public boolean checkMoveMap(ArrayList<TileMap> arrTileMap) {
        for (TileMap tileMap : arrTileMap) {
            if (tileMap.locate_bit == 1 || tileMap.locate_bit == 2 || tileMap.locate_bit == 3 ||
                tileMap.locate_bit == 4 || tileMap.locate_bit == 5 || tileMap.locate_bit == 6 ||
                tileMap.locate_bit == 7 || tileMap.locate_bit == 8 || tileMap.locate_bit == 9) {
                if(getRect().getBoundsInParent().intersects(tileMap.getRect().getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }
}


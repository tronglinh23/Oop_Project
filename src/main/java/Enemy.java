import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private int x;
    private int y;
    private int speed = 3;
    private int orient;
    private AnchorPane enemyPane;
    private ImageView enemy;
    private Scene enemyScene;
    private Stage enemyStage;
    private int imageIndex;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public void Enemy(int x, int y, int orient, AnchorPane enemyPane, Scene enemyScene) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        this.enemyPane = enemyPane;
        this.enemyScene = enemyScene;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
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

    public void moveEnemy(ArrayList<TileMap> arrTileMap, ArrayList<Boom> arrBoom, int t) {
        int speed = 2;
        int xRaw = x;
        int yRaw = y;
        switch (orient) {
            case LEFT:
                xRaw -= speed;
                break;
            case RIGHT:
                xRaw += speed;
                break;
            case UP:
                yRaw -= speed;
                break;
            case DOWN:
                yRaw += speed;
            default:
        }
}

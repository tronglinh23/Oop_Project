import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private int x;
    private int y;
    private int speed = 3;
    private int orient;
    private final int size_enemy = 50;
    private Image enemy;

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

    Rectangle getRect() {
        Rectangle theEnemy = new Rectangle(x,y + 25, size_enemy - 10, size_enemy - 10);
        return theEnemy;
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
        int speed = 3;
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
        int xChange = x;
        int yChange = y;
        x = xRaw;
        y = yRaw;

        boolean checkEnemyMove = checkMoveMap(arrTileMap);

    }

    public boolean checkMoveMap(ArrayList<TileMap> arrtileMap) {
        for (TileMap tileMap : arrtileMap) {
            if (tileMap.locate_bit == 5 || tileMap.locate_bit == 1 || tileMap.locate_bit == 2 ||
                tileMap.locate_bit == 3 || tileMap.locate_bit == 4 || tileMap.locate_bit == 6 ||
                tileMap.locate_bit == 7 || tileMap.locate_bit == 8 || tileMap.locate_bit == 9) {
                if (getRect().getBoundsInParent().intersects(tileMap.getRect().getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }


}


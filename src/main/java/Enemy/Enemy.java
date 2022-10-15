package Enemy;
import MainGame.MainPlayer;
import Others.*;
import Base.BaseObject;
import Item_Bomb.Boom;
import Map.TileMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends BaseObject{
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

    private int lifeEnemy;

    public final static double timeImmortality = 1.5;
    private long timeDie;

    public Enemy(int x, int y) {
        super(x,y);
        this.orient = 0;
        isDie = false;
        lifeEnemy = 1;
        timeDie = (long)0;
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
        }
    }

    public void setLifeEnemy(int lifeEnemy) {
        this.lifeEnemy = lifeEnemy;
    }

    public int getLifeEnemy() {
        return lifeEnemy;
    }

    public void decreaseLife() {
        this.lifeEnemy--;
    }

    public void setTimeDie(long timeDie) {
        this.timeDie = timeDie;
    }

    public long getTimeDie() {
        return timeDie;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getOrient() {
        return this.orient;
    }

    public abstract void drawEnemy(GraphicsContext gc);

    public void moveEnemy(MainPlayer player, ArrayList<TileMap> arrTileMap, ArrayList<Boom> arrBoom) {
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
        boolean checkEnemyPlayer = checkEnemy_Player(player);

        if (checkEnemyMap || checkEnemyBomb || checkEnemyFrame) {
            x = xRaw;
            y = yRaw;
            createOrient();
        }

        if (checkEnemyPlayer) {
            if(player.getIsDie() == false) {
                player.setIsDie(true, System.nanoTime());
            }
        }
    }
    public boolean checkEnemy_Player(MainPlayer player){
        if (getRect().getBoundsInParent().intersects(player.getRect().getBoundsInParent())){
            return true;
        }
        return false;
    }
    public void setIsDie(boolean isDie) {
        this.isDie = isDie;
    }
    public boolean getIsDie() {return this.isDie;}
}


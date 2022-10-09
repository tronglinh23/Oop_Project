import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Find extends Enemy{

    private Image find;

    private final int size_find = 45;

    private Random random = new Random();

    public Find(int x, int y, int orient) {
        super(x, y, orient);
        find = MY_FIND[0];
    }

    public final Image[] MY_FIND={
            ImageUtils.loadImage("src/main/resources/Enemy/LEFT.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/RIGHT.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/UP.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/DOWN.png")
    };

    public void createOrient() {
        int rnd = random.nextInt(20);
        if (rnd > 15) {
            int newOrient = random.nextInt(4);
            setOrient(newOrient);
            find = MY_FIND[newOrient];
        }
    }

    public Rectangle getRect() {
        Rectangle theFind = new Rectangle(x + 10,y+15, size_find - 10, size_find - 15);
        return theFind;
    }

    public void drawFind(GraphicsContext gc) {
        gc.drawImage(find, x, y, size_find + 10, size_find + 10);
    }

//    public void moveFind(ArrayList<TileMap> arrTileMap, MainPlayer player) {
//        boolean checkMove = checkMoveMap(arrTileMap);
//        double xChange = x;
//        double yChange = y;
//        switch (super.getOrient()) {
//            case LEFT:
//                xChange -= (double) speed / 4;
//                break;
//            case RIGHT:
//                xChange += (double) speed / 4;
//                break;
//            case UP:
//                yChange -= (double) speed / 4;
//                break;
//            case DOWN:
//                yChange +=  (double) speed / 4;
//            default:
//        }
//        double xRaw = x;
//        double yRaw = y;
//        x = xChange;
//        y = yChange;
//
//        if (checkMove) {
//            x = xRaw;
//            y = yRaw;
//            createOrient();
//        }
//    }

    public void moveFind(ArrayList<TileMap> arrTileMap, ArrayList<Boom> arrBoom, MainPlayer player) {
        double xChange = x;
        double yChange = y;
        switch (super.getOrient()) {
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
        } else {
            if (player.getX() == this.getX() || player.getY() == this.getY()) {
                setOrient(checkMove(player));
            }
        }

        if (checkEnemyBomb) {
            x = xRaw;
            y = yRaw;
            createOrient();
        }
    }

    @Override
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

    protected int calculateColDirection(MainPlayer player) {
        if(player.getX() < this.getX())
            return 0;
        else if(player.getX() > this.getX())
            return 1;
        return -1;
    }

    protected int calculateRowDirection(MainPlayer player) {
        if(player.getY() < this.getY())
            return 2;
        else if(player.getY() > this.getY())
            return 3;
        return -1;
    }

    public int checkMove(MainPlayer player) {
        int rnd = random.nextInt(2);
        if (rnd == 1) {
            int col = calculateColDirection(player);
            if (col != -1) {
                return col;
            }
            else {
                return calculateRowDirection(player);
            }
        }
        else {
            int row = calculateRowDirection(player);
            if (row != -1) {
                return row;
            }
            else {
                return calculateColDirection(player);
            }
        }
    }
}
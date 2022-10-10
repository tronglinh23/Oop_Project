import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Find extends Enemy{

    private Image find;

    private final int size_find = 45;

    private Random random = new Random();

    private double moveHori;
    private double moveVerti;
    private boolean canMoveR;
    private boolean canMoveL;
    private boolean canMoveU;
    private boolean canMoveD;

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

    public void moveFind(MainPlayer player, ArrayList<TileMap> arrTileMap) {
        int j = (int) ((x + size_enemy / 2) / size_enemy);
        int i = (int) ((y + size_enemy / 2) / size_enemy);
        int jp = (int) (player.getX() + size_enemy / 2) / size_enemy;
        int ip = (int) (player.getY() + size_enemy / 2) / size_enemy;
        if ((j * size_enemy == x && i * size_enemy == y)) {
            moveHori = 0;
            moveVerti = 0;
            canMoveR = false;
            canMoveL = false;
            canMoveU = false;
            canMoveD = false;
            checkNext(arrTileMap, j * size_enemy, i * size_enemy);
            if (jp == j) {
                moveHori = 0;
            } else if (jp < j && canMoveL) {
                moveHori = - speed;
            } else if (jp > j && canMoveR) {
                moveHori = speed;
            }
            if (ip == i) {
                moveVerti = 0;
            } else if (ip < i && canMoveU) {
                moveVerti = - speed;
            } else if (ip > i && canMoveD) {
                moveVerti = speed;
            }

            if (moveVerti != 0 && moveHori != 0) {
                if (Math.abs(ip - i) > Math.abs(jp - j)) {
                    moveHori = 0;
                } else {
                    moveVerti = 0;
                }
            }
        }
        x += moveHori;
        y += moveVerti;
        x = Math.round(x * 10) / 10;
        y = Math.round(y * 10) / 10;
    }

    public void checkNext(ArrayList<TileMap> arrTileMap, ArrayList<Boom> arrBoom, int xNext, int yNext){
        for (TileMap tileMap : arrTileMap) {
            if (tileMap.locate_bit == 0) {
                if (tileMap.getX() == xNext + TileMap.SIZE && tileMap.getY() == yNext) {
                    canMoveR = true;
                    System.out.println(1);
                }

                if (tileMap.getX() == xNext - TileMap.SIZE && tileMap.getY() == yNext) {
                    canMoveL = true;
                    System.out.println(2);
                }

                if (tileMap.getX() == xNext && tileMap.getY() == yNext - TileMap.SIZE) {
                    canMoveU = true;
                    System.out.println(3);
                }

                if (tileMap.getX() == xNext && tileMap.getY() == yNext + TileMap.SIZE) {
                    canMoveD = true;
                    System.out.println(4);
                }
            }
        }

        for (Boom boom : arrBoom) {
            if (boom)
        }
    }
}
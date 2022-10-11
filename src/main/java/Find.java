import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Find extends Enemy{

    private Image find;

    private final int size_find = 45;
    private final double speedHaiTac = 1;

    private Random random = new Random();

    private double moveHorizontal;
    private double moveVertical;
    private boolean canMoveR;
    private boolean canMoveL;
    private boolean canMoveU;
    private boolean canMoveD;

    private int indexIMG;
    private int setImage;
    public final Image[][] MY_FIND={
            { ImageUtils.loadImage("src/main/resources/Enemy/LEFT.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_1.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_2.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_3.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_4.png")},
            { ImageUtils.loadImage("src/main/resources/Enemy/RIGHT.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_1.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_2.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_3.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/RIGHT_4.png"),},
            {ImageUtils.loadImage("src/main/resources/Enemy/UP.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/UP_1.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/UP_2.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/UP_3.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/UP_4.png"),},
            {ImageUtils.loadImage("src/main/resources/Enemy/DOWN.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/DOWN_1.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/DOWN_2.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/DOWN_3.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/DOWN_4.png"),},
    };

    public Find(int x, int y, int orient) {
        super(x, y, orient);
        setImage = 3;
        indexIMG = 0;
    }


    public Rectangle getRect() {
        Rectangle theFind = new Rectangle(x + 10,y+15, size_find - 10, size_find - 15);
        return theFind;
    }

    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(MY_FIND[setImage][indexIMG / 10 % MY_FIND[setImage].length],
                x, y, size_find + 10, size_find + 10);
        indexIMG++;
    }

    public void moveFind(MainPlayer player,ArrayList<TileMap> arrTileMap, ArrayList<Boom> arrBoom) {
        int xEnemy = (int) ((x + size_enemy / 2) / size_enemy);
        int yEnemy = (int) ((y + size_enemy / 2) / size_enemy);
        int xPlayer = (int) ((player.getX() + size_enemy / 2) / size_enemy);
        int yPlayer = (int) ((player.getY() + size_enemy / 2) / size_enemy);
        if ((xEnemy * size_enemy == x && yEnemy * size_enemy == y)) {
            moveHorizontal = 0;
            moveVertical = 0;
            canMoveR = false;
            canMoveL = false;
            canMoveU = false;
            canMoveD = false;
            checkNext(arrTileMap,xEnemy * size_enemy, yEnemy * size_enemy);
            checkBomb(arrBoom,xEnemy * size_enemy, yEnemy * size_enemy);

            if (xPlayer == xEnemy) {
                moveHorizontal = 0;
            } else if (xPlayer < xEnemy && canMoveL) {
                moveHorizontal = -speedHaiTac;
            } else if (xPlayer > xEnemy && canMoveR) {
                moveHorizontal = speedHaiTac;
            }
            if (yPlayer == yEnemy) {
                moveVertical = 0;
            } else if (yPlayer < yEnemy && canMoveU) {
                moveVertical = -speedHaiTac;
            } else if (yPlayer > yEnemy && canMoveD) {
                moveVertical = speedHaiTac;
            }

            if (moveVertical != 0 && moveHorizontal != 0) {
                if (Math.abs(yPlayer - yEnemy) > Math.abs(xPlayer - xEnemy)) {
                    moveHorizontal = 0;
                } else {
                    moveVertical = 0;
                }
            }
        }
        if(moveHorizontal < 0) setImage = 0;
        else if(moveHorizontal > 0) setImage = 1;
        else if(moveVertical < 0) setImage = 2;
        else if(moveHorizontal > 0) setImage = 3;
        else setImage = 3;

        x += moveHorizontal;
        y += moveVertical;
        if(checkCollisionFrame(x,y,size_enemy)) {
            x -= moveHorizontal;
            y -= moveVertical;
        }
    }

    public void checkBomb(ArrayList<Boom> arrBoom, int xNext, int yNext) {
        for (Boom boom : arrBoom) {
            if (boom.getX() == xNext + TileMap.SIZE && boom.getY() == yNext) {
                canMoveR = false;
            }

            if (boom.getX() == xNext - TileMap.SIZE && boom.getY() == yNext) {
                canMoveL = false;
            }

            if (boom.getX() == xNext && boom.getY() == yNext - TileMap.SIZE) {
                canMoveU = false;
            }

            if (boom.getX() == xNext && boom.getY() == yNext + TileMap.SIZE) {
                canMoveD = false;
            }
        }
    }
    public void checkNext(ArrayList<TileMap> arrTileMap, int xNext, int yNext){
        for (TileMap tileMap : arrTileMap) {
            if (tileMap.locate_bit == 0 || tileMap.locate_bit == 7) {
                if (tileMap.getX() == xNext + TileMap.SIZE && tileMap.getY() == yNext) {
                    canMoveR = true;
                }

                if (tileMap.getX() == xNext - TileMap.SIZE && tileMap.getY() == yNext) {
                    canMoveL = true;
                }

                if (tileMap.getX() == xNext && tileMap.getY() == yNext - TileMap.SIZE) {
                    canMoveU = true;
                }

                if (tileMap.getX() == xNext && tileMap.getY() == yNext + TileMap.SIZE) {
                    canMoveD = true;
                }
            }
        }
    }
}
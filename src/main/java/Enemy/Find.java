package Enemy;

import Item_Bomb.Boom;
import MainGame.MainPlayer;
import Map.TileMap;
import Others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import jdk.jfr.Percentage;

import java.util.ArrayList;
import java.util.Random;

/**
 * tạo ra 1 loại enemy mới có thể tìm đường đến chỗ của player.
 * được kế thừa từ Enemy nhưng sẽ viết lại hàm move vì cần thuật toán tìm kiếm
 * thuật toán di chuyển sẽ nếu vị trí của player đang không trùng với enemy thì
 * sẽ thay đôi vị trí enemy cho đến khi gặp.
 */
public class Find extends Enemy {

    private final int size_find = 45;
    private final double speedHaiTac = 3;

    private double moveHorizontal;
    private double moveVertical;
    private boolean canMoveR;
    private boolean canMoveL;
    private boolean canMoveU;
    private boolean canMoveD;

    protected int indexIMG;
    protected int setImage;
    public final Image[][] MY_FIND = {
            {ImageUtils.loadImage("src/main/resources/Enemy/LEFT.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_1.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_2.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_3.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/LEFT_4.png")},
            {ImageUtils.loadImage("src/main/resources/Enemy/RIGHT.png"),
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

    public Find(int x, int y) {
        super(x, y);
        setImage = 3;
        indexIMG = 0;
    }

    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(MY_FIND[setImage][indexIMG / 10 % MY_FIND[setImage].length],
                x, y, size_find + 5, size_find + 15);
        indexIMG++;
    }

    @Override
    public void moveEnemy(MainPlayer player, ArrayList<TileMap> arrTileMap, ArrayList<Boom> arrBoom) {
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
            checkNext(arrTileMap, xEnemy * size_enemy, yEnemy * size_enemy);
            checkBomb(arrBoom, xEnemy * size_enemy, yEnemy * size_enemy);

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
        if (moveHorizontal < 0) setImage = 0;
        else if (moveHorizontal > 0) setImage = 1;
        else if (moveVertical < 0) setImage = 2;
        else if (moveHorizontal > 0) setImage = 3;
        else setImage = 3;

        x += moveHorizontal;
        y += moveVertical;

        if (checkEnemy_Player(player)) {
            if(player.getIsDie() == false) {
                player.setIsDie(true, System.nanoTime());
            }
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

    public void checkNext(ArrayList<TileMap> arrTileMap, int xNext, int yNext) {
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
package Enemy;

import Item_Bomb.ItemGame;
import Others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Present extends Enemy {
    private final int size_present = 45;
    private int imageIndex;
    public final Image[][] MY_ITEM_ENEMY = {
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Left_000.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Left_001.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Right_000.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Right_001.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Up_000.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Up_001.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Down_000.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Down_001.png")},
    };

    public static final Image[] MY_ITEM_ENEMY_DIE = {
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_000.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_001.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_002.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_003.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_003.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_003.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_003.png"),
    };


    public Present(int x, int y) {
        super(x, y);
        imageIndex = 0;
    }

    @Override
    public void drawEnemy(GraphicsContext gc) {
        imageIndex++;
        gc.drawImage(MY_ITEM_ENEMY[getOrient()][imageIndex / 10 % MY_ITEM_ENEMY[getOrient()].length]
                , x, y, size_present + 10, size_present + 10);
    }
}

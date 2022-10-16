package Enemy;

import Map.TileMap;
import Others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * tạo ra 1 loại enemy mới có thể thay đổi tốc độ.
 * được thừa kế từ lớp enemy để có thể dùng các hàm như moveEnemy, ...
 * thuật toán di chuyển cũng tương tự lớp enemy: gặp các chướng ngại vật sẽ chuyển hướng.
 */
public class Fast extends Enemy {
    private Image fast;
    private final int size_fast = 45;
    private Random random = new Random();
    private int imageIndex = 0;
    public final Image[][] MY_FAST = {
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile009.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile010.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile011.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile003.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile004.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile005.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile000.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile001.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile002.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile006.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile007.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile008.png")},
    };

    public static final Image[] MY_FAST_DIE = {
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_000.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_001.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_002.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_003.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_004.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_005.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/enemy_item_Die_003.png"),
    };

    public Fast(int x, int y) {
        super(x, y);
    }

    @Override
    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(MY_FAST[this.getOrient()][imageIndex / 10 % MY_FAST[getOrient()].length],
                x, y, size_fast + 10, size_fast + 10);
        imageIndex++;
    }
}

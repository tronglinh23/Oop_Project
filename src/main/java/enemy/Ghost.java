package enemy;

import others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Enemy cơ bản, không có chức năng gì đặc biệt.
 */
public class Ghost extends Enemy {
    private final int size_ghost = 45;
    public final Image[] MY_GHOST={
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_left.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_right.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_up.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_down.png")
    };
    public Ghost(int x, int y) {
        super(x, y);
    }

    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(MY_GHOST[getOrient()], x, y, size_ghost + 10, size_ghost + 10);
    }
}

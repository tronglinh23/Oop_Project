package Enemy;

import Others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ghost extends Enemy {
    private Image ghost;
    private final int size_ghost = 45;
    private Random random = new Random();
    public final Image[] MY_GHOST={
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_left.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_right.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_up.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_down.png")
    };
    public Ghost(int x, int y) {
        super(x, y);
    }

    public void createOrient() {
        int rnd = random.nextInt(20);
        if (rnd > 15) {
            int newOrient = random.nextInt(4);
            setOrient(newOrient);
            ghost = MY_GHOST[newOrient];
        }
    }


    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(ghost, x, y, size_ghost + 10, size_ghost + 10);
    }
}

package Enemy;

import Item_Bomb.ItemGame;
import Others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Present extends Enemy {

    private Image present;
    private final int size_present = 45;
    private Random random = new Random();
    public final Image[] MY_GHOST={
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_left.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_right.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_up.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_down.png")
    };

    public Present(int x, int y) {
        super(x, y);
    }

    public void createOrient() {
        int rnd = random.nextInt(20);
        if (rnd > 15) {
            int newOrient = random.nextInt(4);
            setOrient(newOrient);
            present = MY_GHOST[newOrient];
        }
    }
    @Override
    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(present, x, y, size_present + 10, size_present + 10);
    }
}

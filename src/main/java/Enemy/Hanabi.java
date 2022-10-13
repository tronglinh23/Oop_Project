package Enemy;

import Item_Bomb.WaveBoom;
import Others.ImageUtils;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Hanabi extends Find {
    private Image hanabi;
    private final int size_hanabi = 45;
    private final int speedhanabi = 1;
    private Random random = new Random();
    private int length_boom = 1;

    public final Image[] MY_HANABI={
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_left.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_right.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_up.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/ghost_down.png")
    };

    public void createOrient() {
        int rnd = random.nextInt(20);
        if (rnd > 15) {
            int newOrient = random.nextInt(4);
            setOrient(newOrient);
            hanabi = MY_HANABI[newOrient];
        }
    }
    public Hanabi(int x, int y, int orient) {
        super(x, y, orient);
        hanabi = MY_HANABI[0];
    }

    public Rectangle getRect() {
        Rectangle theHanabi = new Rectangle(x + 10, y + 15, size_hanabi - 10, size_hanabi - 15);
        return theHanabi;
    }

    public WaveBoom boomBang() {
        int locateX = (int)x - 5;
        int locateY = (int)y - 5;
        WaveBoom waveBoom = new WaveBoom(locateX, locateY, length_boom);
        return waveBoom;
    };
}

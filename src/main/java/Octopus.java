import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * tạo ra 1 loại enemy mới có thể đặt boom.
 * Đặt boom tại vị trí của nhân vật.
 * được thừa kế từ lớp enemy để có thể dùng các hàm như moveEnemy, ...
 * thuật toán di chuyển cũng tương tự lớp enemy: gặp các chướng ngại vật sẽ chuyển hướng.
 */
public class Octopus extends Enemy{
    private final int lengthBomb = 2;

    private final int size_octopus = 90;
    private Random random = new Random();

    private Image octopus;

    public final Image[] MY_OCTOPUS={
            ImageUtils.loadImage("src/main/resources/Enemy/boss_left.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/boss_right.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/boss_up.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/boss_down.png")
    };

    public Octopus(int x, int y, int orient) {
        super(x, y, orient);
        octopus = MY_OCTOPUS[0];
    }

    public void createOrient() {
        int rnd = random.nextInt(20);
        if (rnd > 15) {
            int newOrient = random.nextInt(4);
            setOrient(newOrient);
            octopus = MY_OCTOPUS[newOrient];
        }
    }

    public Rectangle getRect() {
        Rectangle theOctopus = new Rectangle(x + 10,y+15, size_octopus - 10, size_octopus - 15);
        return theOctopus;
    }

    @Override
    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(octopus, x, y, size_octopus + 10, size_octopus + 10);
    }

    /**
     * setup boom tại vị trí của main player.
     */
    public Boom setupBoom(double xPlayer, double yPlayer) {
        int xRaw = (int)xPlayer + Boom.Size/2;
        int yRaw = (int)yPlayer + Boom.Size/2;
        int xBoom = xRaw - xRaw % Boom.Size;
        int yBoom = yRaw - yRaw % Boom.Size;
        Boom bomb = new Boom(xBoom, yBoom, lengthBomb, 1);
        return bomb;
    }
}

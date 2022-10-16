package Enemy;

import Item_Bomb.WaveBoom;
import Others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * tạo ra 1 loại enemy mới có thể nổ sau 1 khoảng thời gian .
 * được thừa kế từ lớp find
 * cũng có các chức năng di chuyển tìm kiếm player để tăng độ khó khi tránh nó sao cho không chết.
 */
public class Hanabi extends Find {
    private final int size_hanabi = 45;
    private int length_boom = 1;

    public final Image[][] MY_HANABI={
            {ImageUtils.loadImage("src/main/resources/Enemy/TNT_LEFT_1.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/TNT_LEFT_2.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/TNT_LEFT_3.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/TNT_LEFT_4.png")},
            {ImageUtils.loadImage("src/main/resources/Enemy/TNT_RIGHT_1.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/TNT_RIGHT_2.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/TNT_RIGHT_3.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/TNT_RIGHT_4.png")},
            {ImageUtils.loadImage("src/main/resources/Enemy/TNT_DOWN.png")},
            {ImageUtils.loadImage("src/main/resources/Enemy/TNT_DOWN.png")},
    };

    public Hanabi(int x, int y) {
        super(x, y);
    }
    @Override
    public void drawEnemy(GraphicsContext gc) {
        gc.drawImage(MY_HANABI[setImage][indexIMG / 10 % MY_HANABI[setImage].length],
                x, y - 10, size_hanabi + 5, size_hanabi + 20);
        indexIMG++;
    }

    public WaveBoom boomBang() {
        int locateX = (int)x - 5;
        int locateY = (int)y - 5;
        WaveBoom waveBoom = new WaveBoom(locateX, locateY, length_boom);
        return waveBoom;
    };
}

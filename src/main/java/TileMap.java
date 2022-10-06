import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class TileMap extends BaseObject{

    public static int levelGame;
    private final static Image[][] IMG_MAP_VIEW = {
            {
                    ImageUtils.loadImage("src/main/resources/map1/pic_1.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_2.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_3.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_4.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_5.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_6.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_7.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_8.png"),
                    ImageUtils.loadImage("src/main/resources/map1/pic_9.png")
            },
            {
                    ImageUtils.loadImage("src/main/resources/map2/pic_1.png"),
                    ImageUtils.loadImage("src/main/resources/map2/pic_2.png"),
                    ImageUtils.loadImage("src/main/resources/map2/pic_3.png"),
                    ImageUtils.loadImage("src/main/resources/map2/pic_4.png"),
                    ImageUtils.loadImage("src/main/resources/map2/pic_5.png")
            }
    };
    public int locate_bit;
    public static final int SIZE = 45;

    public TileMap(int x ,int y, int locate_bit) {
        super(x,y);
        this.locate_bit = locate_bit;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x + 5, y + 5, SIZE - 10, SIZE - 10);
        return  rectangle;
    }

    public void drawImageStage(GraphicsContext gc) {
        if(locate_bit != 0) {
            gc.drawImage(IMG_MAP_VIEW[levelGame][locate_bit - 1],x,y,SIZE+2,SIZE+2);
        }
    }
}

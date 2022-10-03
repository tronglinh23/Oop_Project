import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class TileMap extends BaseObject{
//    private final static Image[] IMG_MAP_VIEW = {
//            ImageUtils.loadImage("src/main/resources/TileMapBox/goccay1.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/cay1.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/da1.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/namdo1.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/namxanh1.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/vienDuoi.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/vienTren.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/vienPhai.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/vienTrai.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/gocTrenTrai.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/gocTrenPhai.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/gocDuoiTrai.png"),
//            ImageUtils.loadImage("src/main/resources/TileMapBox/gocDuoiPhai.png"),
//    };

    private final static Image[] IMG_MAP_VIEW = {
            ImageUtils.loadImage("src/main/resources/map2/pic_1.png"),
            ImageUtils.loadImage("src/main/resources/map2/pic_2.png"),
            ImageUtils.loadImage("src/main/resources/map2/pic_3.png"),
            ImageUtils.loadImage("src/main/resources/map2/pic_4.png"),
            ImageUtils.loadImage("src/main/resources/map2/pic_5.png"),
    };

    private final static Image[] IMG_MAP_VIEW_2 = {
            ImageUtils.loadImage("src/main/resources/map1/pic_1.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_2.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_3.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_4.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_5.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_6.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_7.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_8.png"),
            ImageUtils.loadImage("src/main/resources/map1/pic_9.png"),
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
    
//    public void drawImageStage(GraphicsContext gc) {
//        if(x == 0 && y == 0){
//            gc.drawImage(IMG_MAP_VIEW[9],x,y,SIZE,SIZE);
//        } else if(x == 0 && y == 14 * SIZE) {
//            gc.drawImage(IMG_MAP_VIEW[11],x,y,SIZE,SIZE);
//        } else if(x == 16 * SIZE && y == 0) {
//            gc.drawImage(IMG_MAP_VIEW[10],x,y,SIZE,SIZE);
//        } else if(x == 16 * SIZE && y == 14 * SIZE) {
//            gc.drawImage(IMG_MAP_VIEW[12],x,y,SIZE,SIZE);
//        } else if(locate_bit != 0) {
//            gc.drawImage(IMG_MAP_VIEW[locate_bit - 1],x,y, SIZE + 2, SIZE + 2);
//        }
//    }
    public void drawImageStage(GraphicsContext gc) {
        if(locate_bit != 0) {
            gc.drawImage(IMG_MAP_VIEW[locate_bit - 1],x,y,SIZE+2,SIZE+2);
        }
    }
}

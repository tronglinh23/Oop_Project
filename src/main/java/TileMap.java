import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TileMap {
    private final static String[] IMG_MAP_VIEW = {
           "/images/goccay1.png",
           "/images/cay1.png",
           "/images/da1.png",
           "/images/namdo1.png",
           "/images/namxanh1.png",
           "/images/vienDuoi.png",
           "/images/vienTren.png",
           "/images/vienPhai.png",
           "/images/vienTrai.png",
           "/images/gocTrenTrai.png",
           "/images/gocTrenPhai.png",
           "/images/gocDuoiTrai.png",
           "/images/gocDuoiPhai.png",
    };

    private int x;
    private int y;
    public int locate_bit;
    public static final int SIZE = 45;

    public TileMap(int x ,int y, int locate_bit) {
        this.x = x;
        this.y = y;
        this.locate_bit = locate_bit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x + 5, y + 20, SIZE - 10, SIZE - 10);
        return  rectangle;
    }
    
    public void drawImageStage(AnchorPane mainPain) {
        ImageView IMG = new ImageView("/images/background.jpg");
        if(x == 0 && y == 0){
            IMG = new ImageView(IMG_MAP_VIEW[9]);
        } else if(x == 0 && y == 14 * SIZE) {
            IMG = new ImageView(IMG_MAP_VIEW[11]);
        } else if(x == 16 * SIZE && y == 0) {
           IMG = new ImageView(IMG_MAP_VIEW[10]);
        } else if(x == 16 * SIZE && y == 14 * SIZE) {
           IMG = new ImageView(IMG_MAP_VIEW[12]);
        } else if(locate_bit != 0) {
            IMG = new ImageView(IMG_MAP_VIEW[locate_bit - 1]);
            IMG.setFitWidth(SIZE + 2);
            IMG.setFitHeight(SIZE + 2);
        }

        IMG.setLayoutX(x);
        IMG.setLayoutY(y);
        mainPain.getChildren().add(IMG);
    }
}

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class Boom {
    AnchorPane boomPane;
    private int x;
    private int y;
    private int length_boom;
    public static final int Size = 45;
    private Image boomIMG;

    private int isCheckBomb = 1;
    private final static String BOOM_IMG = "Bomb/boom1.png";

    public Boom(int x, int y, int length_boom) {
        this.x = x;
        this.y = y;
        this.length_boom = length_boom;
        this.boomIMG = ImageUtils.loadImage("src/main/resources/Bomb/boom1.png");
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX() {
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getY() {
        return this.y;
    }

    public void setIsCheckBomb(int k) {this.isCheckBomb = k;}
    public int getIsCheckBomb() { return this.isCheckBomb;}
    public void setLength_boom(int length_boom) { this.length_boom = length_boom; }
    public int getLength_boom() { return this.length_boom;}

    public Rectangle getRect() {
        Rectangle rec = new Rectangle(x + 5,y + 15,Size - 10, Size - 15);
        return rec;
    }
    public void draw(GraphicsContext gc){
//        imageIndex++;
//        image = IMAGE_BOOM[imageIndex/5 %IMAGE_BOOM.length];
        gc.drawImage(boomIMG,x,y,Size,Size);
    }

    public Image getImageView() {return boomIMG;}

    public WaveBoom boomBang() {
        int locateX = x - 5;
        int locateY = y - 5;
        WaveBoom waveBoom = new WaveBoom(locateX, locateY, length_boom);
        return waveBoom;
    };
}

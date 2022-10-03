import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class Boom extends BaseObject{
    private int length_boom;
    public static final int Size = 45;
    private Image boomIMG;

    private int isCheckBomb = 1;
    private final static Image[] BOOM_IMG = {
            ImageUtils.loadImage("src/main/resources/Bomb/BigBoomBongMay_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/BigBoomBongMay_2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/BigBoomBongMay_3.png"),
    };

    private final static Image[] BOOM_IMG_2 = {
            ImageUtils.loadImage("src/main/resources/Bomb/boom_D_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom_D_2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom_D_3.png"),
    };

    private final static Image[] BOOM_IMG_3 = {
            ImageUtils.loadImage("src/main/resources/Bomb/boom1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom3.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom4.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom5.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom6.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom7.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom8.png"),
    };

    private int imageAnimationStt = 0;

    public Boom(int x, int y, int length_boom) {
        super(x,y);
        this.length_boom = length_boom;
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
        imageAnimationStt++;
        boomIMG = BOOM_IMG_2[imageAnimationStt/10 % BOOM_IMG.length];
        gc.drawImage(boomIMG, x, y, Size + 3, Size);
    }

    public Image getImageView() {return boomIMG;}

    public WaveBoom boomBang() {
        int locateX = x - 5;
        int locateY = y - 5;
        WaveBoom waveBoom = new WaveBoom(locateX, locateY, length_boom);
        return waveBoom;
    };
}

package item_Game;
import base.BaseObject;
import others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Class boom game to init a bomb.
 * Extends BaseObject
 */
public class Boom extends BaseObject{
    private int length_boom;
    public static final int Size = 45;
    private Image boomIMG;
    private int type;
    private int isCheckBomb = 1;
    private final static Image[] BOOM_ENEMY_IMG = {
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/blur_boom_2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom_Enemy_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom_Enemy_1.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom_Enemy_2.png"),
            ImageUtils.loadImage("src/main/resources/Bomb/boom_Enemy_2.png"),

    };
    private final static Image[] BOOM_IMG_1 = {
            ImageUtils.loadImage("src/main/resources/Bomb/BigBoomBongMay_1.png"),
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

    public Boom(int x, int y, int length_boom, int type) {
        super(x,y);
        this.length_boom = length_boom;
        this.type = type;
    }

    public void setIsCheckBomb(int k) {this.isCheckBomb = k;}
    public int getIsCheckBomb() { return this.isCheckBomb;}

    public Rectangle getRect() {
        Rectangle rec = new Rectangle(x + 5,y + 15,Size - 10, Size - 15);
        return rec;
    }

    public void draw(GraphicsContext gc){
        imageAnimationStt++;
        if (type == 0){
            boomIMG = BOOM_ENEMY_IMG[imageAnimationStt/10 % BOOM_ENEMY_IMG.length];
        } else if (type == 1) {
            boomIMG = BOOM_IMG_1[imageAnimationStt/10 % BOOM_IMG_1.length];
        } else if (type == 2) {
            boomIMG = BOOM_IMG_2[imageAnimationStt/10 % BOOM_IMG_2.length];
        } else {
            boomIMG = BOOM_IMG_3[imageAnimationStt/10 % BOOM_IMG_3.length];
        }
        gc.drawImage(boomIMG, x, y, Size + 3, Size);
    }

    public Image getImageView() {return boomIMG;}

    /**
     * Init wave bomb when bomb bang.
     * @return wave bomb
     */
    public WaveBoom boomBang() {
        int locateX = (int)x - 5;
        int locateY = (int)y - 5;
        WaveBoom waveBoom = new WaveBoom(locateX, locateY, length_boom);
        return waveBoom;
    };

}

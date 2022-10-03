import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class ItemGame extends BaseObject{

    private int count;
    private int pickItem;

    private static Image[][] items_IMG = {
            {ImageUtils.loadImage("src/main/resources/Item/item_bomb.png"),
                    ImageUtils.loadImage("src/main/resources/Item/nothing_renderer.png")},

            {ImageUtils.loadImage("src/main/resources/Item/item_bombsize.png"),
                    ImageUtils.loadImage("src/main/resources/Item/nothing_renderer.png")},

            {ImageUtils.loadImage("src/main/resources/Item/item_shoe.png"),
                    ImageUtils.loadImage("src/main/resources/Item/nothing_renderer.png")},

            {ImageUtils.loadImage("src/main/resources/Item/item_kim.png"),
                    ImageUtils.loadImage("src/main/resources/Item/nothing_renderer.png")}
    };

    public ItemGame(int x, int y, int pickItem) {
        super(x,y);
        this.pickItem = pickItem;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x + 5, y + 5, TileMap.SIZE - 10, TileMap.SIZE - 10);
        return  rectangle;
    }

    public void drawItem(GraphicsContext gc) {
        gc.drawImage(items_IMG[pickItem][count / 25 % 2],x + 2,y+2);
        count++;
    }

    public int getPickItem() {return this.pickItem;}

    public boolean handLeItem(MainPlayer player) {
        if(getRect().getBoundsInParent().intersects(player.getRect().getBoundsInParent())) {
            if (pickItem == 0) {
                player.setAmountBomb();
                return true;
            } else if (pickItem == 1) {
                player.setLengthBomb();;
                return true;
            } else if (pickItem == 2) {
                player.setSpeed();
                return true;
            } else {
                player.setKim(1);
                System.out.println(player.getKim());
                return true;
            }
        }
        return false;
    }
}

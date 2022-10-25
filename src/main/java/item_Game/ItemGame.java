package item_Game;

import base.BaseObject;
import enemy.Enemy;
import maingame.GameManager;
import player.MainPlayer;
import map.TileMap;
import others.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Init class Item game.
 */
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
                    ImageUtils.loadImage("src/main/resources/Item/nothing_renderer.png")},

            {ImageUtils.loadImage("src/main/resources/Door/Basic_Door_Pixel.png"),
                    ImageUtils.loadImage("src/main/resources/Door/Basic_Door_Opening_Pixel.png") }

    };

    public ItemGame(int x, int y, int pickItem) {
        super(x,y);
        this.pickItem = pickItem;
        count = 0;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x + 5, y + 5, TileMap.SIZE - 10, TileMap.SIZE - 10);
        return  rectangle;
    }

    public void drawItem(GraphicsContext gc) {
        gc.drawImage(items_IMG[pickItem][count / 25 % items_IMG[pickItem].length],x + 2,y+2);
        count++;
    }

    public int getPickItem() {return this.pickItem;}

    /**
     * Xử lí va chạm với item.
     * item 1 : thêm bomb
     * item 2 : tăng độ dài wave bomb
     * item 3 : tăng speed
     * item 4 : hồi sinh
     *
     * @param player player
     * @param arrEnemy arrayEnemy
     * @return true/false
     */
    public boolean handLeItem(MainPlayer player, ArrayList<Enemy> arrEnemy) {
        if(getRect().getBoundsInParent().intersects(player.getRect().getBoundsInParent())) {
            if (pickItem == 0) {
                player.setAmountBomb();
                System.out.println("\nBomb : " + player.getAmountBomb());
                return true;
            } else if (pickItem == 1) {
                player.setLengthBomb();;
                System.out.println("\nLength WaveBomb : " + player.getLengthBomb());
                return true;
            } else if (pickItem == 2) {
                player.setSpeed();
                System.out.println("\nSpeed : " + player.getSpeed());
                return true;
            } else if(pickItem == 3) {
                player.setKim(1);
                System.out.println("\nKim : " + player.getKim());
                return true;
            } else {
                // phai giet het dc enemy moi qua man
                if(arrEnemy.size() == 0) {
                    GameManager.level_Game++;
                    return true;
                }
            }
        }
        return false;
    }
}

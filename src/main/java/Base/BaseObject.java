package Base;

import Item_Bomb.Boom;
import MainGame.GameManager;
import Map.TileMap;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public abstract class BaseObject {
    protected double x;
    protected double y;
    public BaseObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }
    public double getX() {
        return this.x;
    }

    public void setY(double y){
        this.y = y;
    }
    public double getY() {
        return this.y;
    }

    public enum Move{
        LEFT(0),
        RIGHT(1),
        UP(2),
        DOWN(3),
        ;

        int index;

        Move(int i) {
            index = i;
        }

        public final int getIndex() {
            return index;
        }
    }

    public abstract Rectangle getRect();

    /**
     * Check va cham giua map va cac object.
     * @param arrTileMap array tiled map
     * @return true or false
     */
    public boolean checkCollisionMap(ArrayList<TileMap> arrTileMap) {
        for(TileMap tileMap : arrTileMap) {
            if(tileMap.locate_bit == 1 || tileMap.locate_bit == 2 || tileMap.locate_bit == 3 ||
                    tileMap.locate_bit == 4 || tileMap.locate_bit == 5 || tileMap.locate_bit == 6 ||
                    tileMap.locate_bit == 8 || tileMap.locate_bit == 9) {
                if(getRect().getBoundsInParent().intersects(tileMap.getRect().getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check va cham giua Bomb voi cac vat the trong game
     * @param arrBoom
     * @return
     */
    public boolean checkCollisionBomb(ArrayList<Boom> arrBoom) {
        for (Boom boom : arrBoom) {
            if(getRect().getBoundsInParent().intersects(boom.getRect().getBoundsInParent()) && boom.getIsCheckBomb() == 0) {
                return true;
            }
        }
        return false;
    }
    public boolean checkCollisionFrame(double xChange, double yChange, double SIZE) {
        if(xChange <= 0 || xChange > (GameManager.WIDTH_SCREEN - SIZE)
                || yChange <= 0 || yChange > (GameManager.HEIGHT_SCREEN - SIZE)) {
            return true;
        }
        return false;
    }
}

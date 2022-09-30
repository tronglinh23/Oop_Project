import javafx.scene.shape.Rectangle;

public abstract class BaseObject {
    protected int x;
    protected int y;
    public BaseObject(int x, int y) {
        this.x = x;
        this.y = y;
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

    public enum Move{
        LEFT(1),
        RIGHT(2),
        UP(3),
        DOWN(4),
        ;

        int index;

        Move(int i) {
            index = i;
        }

        public int getIndex() {
            return index;
        }
    }

    public abstract Rectangle getRect();
}

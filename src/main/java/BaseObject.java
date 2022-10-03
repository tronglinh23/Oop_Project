import javafx.scene.shape.Rectangle;

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

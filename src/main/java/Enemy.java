import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class Enemy {
    private int x;
    private int y;
    private int speed = 3;
    private AnchorPane enemyPane;
    private ImageView enemy;
    private Scene enemyScene;
    private Stage enemyStage;
    private int imageIndex;

    public void Enemy(int x, int y, AnchorPane enemyPane, Scene enemyScene) {
        this.x = x;
        this.y = y;
        this.enemyPane = enemyPane;
        this.enemyScene = enemyScene;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }



}

package GUI;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Create BombPick to Menu to pick array bomb.
 * Extends Vbox to load Circle at the same time
 */
public class BombPick extends VBox{

    private ImageView circleImage;
    private ImageView shipImage;

    private  String circleNotChosen = "Button_img/grey_box.png";
    private  String circleChosen = "Button_img/green_boxCheckmark.png";

    private TypeBomb bomb;

    private  boolean isCircleChosen;

    public BombPick(TypeBomb bomb) {
        circleImage = new ImageView(circleNotChosen);
        shipImage = new ImageView(bomb.getPath());
        shipImage.setFitWidth(60);
        shipImage.setFitHeight(60);
        this.bomb = bomb;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(30);
        this.getChildren().add(shipImage);
        this.getChildren().add(circleImage);
    }

    public int getTypeBomb() {
        return this.bomb.getType();
    }

    public boolean getIsCircleChosen() {
        return this.isCircleChosen;
    }

    /**
     * load chosenCircle or not chosenCircle.
     * @param isCircleChosen isCircleChosen
     */
    public void setIsCircleChosen(boolean isCircleChosen) {
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }
}

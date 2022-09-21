import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Create ShipPicker class to pick one ship each time.
 * Extends Vbox to arrange locate of imageview.
 * Vbox and Hbox can use setSpacing, setAlignment ...
 */
public class ShipPicker extends VBox{

    private ImageView circleImage;
    private ImageView shipImage;

    private  String circleNotChosen = "uipack_fixed/PNG/grey_circle.png";
    private  String circleChosen = "uipack_fixed/PNG/green_boxTick.png";

    private SHIP ship;

    private  boolean isCircleChosen;

    public ShipPicker(SHIP ship) {
        circleImage = new ImageView(circleNotChosen);
        shipImage = new ImageView(ship.getUrl());
        this.ship = ship;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(shipImage);
        this.getChildren().add(circleImage);
    }

    public SHIP getShip() {
        return this.ship;
    }

    public boolean getIsCircleChosen() {
        return this.isCircleChosen;
    }

    public void setIsCircleChosen(boolean isCircleChosen) {
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }
}

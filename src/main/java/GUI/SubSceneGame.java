package GUI;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 * Load other screen when clicking on button.
 * SetSize, effect, animation ...
 */
public class SubSceneGame extends SubScene {
    private final static String BACKGROUND_IMG_SUBSCENE = "Button_img/green_panel.png";

    private boolean isHidden = true;
    public SubSceneGame() {
        super(new AnchorPane(), 500, 400);

        BackgroundImage IMAGE = new BackgroundImage(new Image(BACKGROUND_IMG_SUBSCENE, 500, 400, false, true)
                , BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(IMAGE));

        setLayoutX(1024);
        setLayoutY(200);

    }

    public void moveSubscene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);
        if(isHidden) {
            transition.setToX(-550);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}

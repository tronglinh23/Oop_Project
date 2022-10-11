import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ButtonGame extends Button {

    private int WIDTH = 233;
    private int HEIGHT = 55;
    private final String Font_Path = "src/main/resources/font/XBall.ttf";

    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;-fx-background-image: url('Button_img/greenButton.png');";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;-fx-background-image: url('Button_img/greenButtonPressed.png');";

    public void setSizeButton(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }
    public ButtonGame(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(Font_Path), 25));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(HEIGHT);
        setLayoutY(getLayoutY() + 5);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(HEIGHT);
        setLayoutY(getLayoutY() - 5);

    }

    private void initializeButtonListeners() {

        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }

            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }

            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(null);

            }
        });

    }
}

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Create a label for Scene.
 * With also Background with text inside.
 */
public class InfoLabel extends Label {
    public final static String FONT_PATH = "src/main/resources/kenvector_future.ttf";

    public final static String BACKGROUND_LABEL = "uipack_fixed/PNG/yellow_button13.png";
    public InfoLabel(String text) {
        setPrefWidth(380);
        setPrefHeight(49);
//        setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_LABEL, 380, 49, false, true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setBackground(new Background(backgroundImage));


    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        } catch (FileNotFoundException e) {
            System.out.println("Error to load Font");
            setFont(Font.font("Verdana",23));
        }

    }
}

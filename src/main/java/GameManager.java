import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.security.spec.ECField;
import java.util.ArrayList;

public class GameManager {
    private static int WIDTH_SCREEN = 765;
    private static int HEIGHT_SCREEN = 675;

    private AnchorPane mainPain;
    private Stage mainStage;
    private Scene mainScene;
    private final static String Title_game = "Boom";

    private ArrayList<TileMap> arrTileMap;

    public final ImageView[] MY_IMAGE={
           new ImageView("/images/background.jpg"),
    };
    public GameManager() {
        mainPain = new AnchorPane();
        mainScene = new Scene(mainPain, WIDTH_SCREEN, HEIGHT_SCREEN);
        mainStage = new Stage();
        mainStage.setTitle(Title_game);
        mainStage.setScene(mainScene);
        arrTileMap = new ArrayList<>();
    }

    public Stage getGameStage() {
        return this.mainStage;
    }

    public void createNewGame(Stage menuStage) {
        menuStage.hide();
        createBackground();
        readTxtMap();
        drawStage();
        mainStage.show();
    }

    public void createBackground() {
        MY_IMAGE[0].setFitWidth(WIDTH_SCREEN);
        MY_IMAGE[0].setFitHeight(HEIGHT_SCREEN);
        mainPain.getChildren().add(MY_IMAGE[0]);
    }

    public void drawStage() {
        for(TileMap obstacle : arrTileMap) {
            obstacle.drawImageStage(mainPain);
        }
    }
    public void readTxtMap() {
        File file = new File("src/main/resources/map/mapBoom.txt");
        int countLine = 0;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while(line != null) {
                for (int i = 0; i < line.length(); i++) {
                    arrTileMap.add(new TileMap(i*TileMap.SIZE, countLine*TileMap.SIZE,
                            Integer.parseInt(String.valueOf(line.charAt(i)))));
                }
                line = reader.readLine();
                countLine++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

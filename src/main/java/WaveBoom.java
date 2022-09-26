import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class WaveBoom {
    private int x;
    private int y;
    private int lengthWave;

    private ImageView[] LEFT_IMG;
    private ImageView[] RIGHT_IMG;
    private ImageView[] UP_IMG;
    private ImageView[] DOWN_IMG;
    private ImageView MID_IMG;



    private final static String[] WAVE_IMG = {"images/bombbang_left_1.png", "images/bombbang_left_2.png",
                                            "images/bombbang_right_1.png", "images/bombbang_right_2.png",
                                            "images/bombbang_mid_2.png",
                                            "images/bombbang_down_1.png", "images/bombbang_down_2.png",
                                            "images/bombbang_up_1.png", "images/bombbang_up_2.png" };

    public WaveBoom(int x, int y, int lengthWave) {
        this.x = x;
        this.y = y;
        this.lengthWave =  lengthWave;
        LEFT_IMG = new ImageView[lengthWave];
        RIGHT_IMG = new ImageView[lengthWave];
        UP_IMG = new ImageView[lengthWave];
        DOWN_IMG = new ImageView[lengthWave];
    }

    public Rectangle getRect(int x, int y) {
        Rectangle rec = new Rectangle(x + 5, y + 5, Boom.Size - 5, Boom.Size - 5);
        return rec;
    }

    public void draw(AnchorPane pane, ArrayList<TileMap> arrTileMap) {
        drawLeftWave(pane,arrTileMap);
        drawRightWave(pane,arrTileMap);
        drawMidWave(pane,arrTileMap);
        drawDownWave(pane,arrTileMap);
        drawUpWave(pane,arrTileMap);
    }

    private void drawLeftWave(AnchorPane pane, ArrayList<TileMap> arrTileMap) {

        for(int stt = 1 ; stt <= lengthWave ; stt++) {
            int xLocate = x - stt * Boom.Size + 5;
            int yLocate = y + 5;
            if(stt == lengthWave) {
                LEFT_IMG[stt-1] = new ImageView(WAVE_IMG[1]);
            } else {
                LEFT_IMG[stt-1] = new ImageView(WAVE_IMG[0]);
            }
            LEFT_IMG[stt-1].setLayoutX(xLocate);
            LEFT_IMG[stt-1].setLayoutY(yLocate);
            pane.getChildren().add(LEFT_IMG[stt-1]);
        }
    }

    private void drawRightWave(AnchorPane pane, ArrayList<TileMap> arrTileMap) {
        for(int stt = 1 ; stt <= lengthWave ; stt++) {
            int xLocate = x + stt * Boom.Size - 5;
            int yLocate = y + 5;
            if(stt == lengthWave) {
                RIGHT_IMG[stt - 1] = new ImageView(WAVE_IMG[3]);
            } else {
                RIGHT_IMG[stt - 1] = new ImageView(WAVE_IMG[2]);
            }
            RIGHT_IMG[stt - 1].setLayoutX(xLocate);
            RIGHT_IMG[stt - 1].setLayoutY(yLocate);
            pane.getChildren().add(RIGHT_IMG[stt - 1]);
        }
    }


    private void drawMidWave(AnchorPane pane, ArrayList<TileMap> arrTileMap) {
        MID_IMG  = new ImageView(WAVE_IMG[4]);
        MID_IMG.setLayoutX(x);
        MID_IMG.setLayoutY(y);
        pane.getChildren().add(MID_IMG);
    }

    private void drawDownWave(AnchorPane pane, ArrayList<TileMap> arrTileMap) {
        for(int stt = 1 ; stt <= lengthWave ; stt++) {
            int xLocate = x ;
            int yLocate = y + stt * Boom.Size - 5;
            if(stt == lengthWave) {
                DOWN_IMG[stt-1] = new ImageView(WAVE_IMG[6]);
            } else {
                DOWN_IMG[stt-1] = new ImageView(WAVE_IMG[5]);
            }
            DOWN_IMG[stt-1].setLayoutX(xLocate);
            DOWN_IMG[stt-1].setLayoutY(yLocate);
            pane.getChildren().add(DOWN_IMG[stt-1]);
        }
    }

    private void drawUpWave(AnchorPane pane, ArrayList<TileMap> arrTileMap) {
        for(int stt = 1 ; stt <= lengthWave ; stt++) {
            int xLocate = x ;
            int yLocate = y - stt * Boom.Size + 5;
            if(stt == lengthWave) {
                UP_IMG[stt-1] = new ImageView(WAVE_IMG[8]);
            } else {
                UP_IMG[stt-1] = new ImageView(WAVE_IMG[7]);
            }
            UP_IMG[stt-1].setLayoutX(xLocate);
            UP_IMG[stt-1].setLayoutY(yLocate);
            pane.getChildren().add(UP_IMG[stt-1]);
        }
    }

    public void update(AnchorPane pane) {
        for(int i = 0 ; i < lengthWave ; i++) {
            pane.getChildren().remove(LEFT_IMG[i]);
            pane.getChildren().remove(RIGHT_IMG[i]);
            pane.getChildren().remove(UP_IMG[i]);
            pane.getChildren().remove(DOWN_IMG[i]);
        }
        pane.getChildren().remove(MID_IMG);
    }
}

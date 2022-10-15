package Enemy;

import Others.ImageUtils;
import javafx.scene.image.Image;

public class SPEED {
    public final Image[][] MY_SPEED = {
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile009.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile010.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile011.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile003.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile004.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile005.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile000.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile001.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile002.png")},
            {
                    ImageUtils.loadImage("src/main/resources/Enemy/tile006.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile007.png"),
                    ImageUtils.loadImage("src/main/resources/Enemy/tile008.png")},
    };

    public final Image[] MY_SPEED_DIE = {
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_000.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_001.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_002.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_003.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_004.png"),
            ImageUtils.loadImage("src/main/resources/Enemy/tile_die_005.png"),
    };

}

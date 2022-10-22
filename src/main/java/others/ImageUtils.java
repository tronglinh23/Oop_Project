package others;
import java.io.File;

import javafx.scene.image.Image;

/**
 * Load image to screen.
 */
public class ImageUtils {
    public static Image loadImage(String path) {
        File file = new File(path);
        String imagePath = file.getAbsolutePath();
        imagePath="file:"+imagePath;
        return new Image(imagePath);
    }
}
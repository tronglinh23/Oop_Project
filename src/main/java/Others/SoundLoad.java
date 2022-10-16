package Others;
import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;

/**
 * Load sound, chỉnh sửa volume .
 */
public class SoundLoad {
    private Clip clip_game;
    public static Clip getSoundVolume(String fileName, float minusVolume) {
        try {
            File file = new File(fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(minusVolume);

            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

import javax.sound.sampled.*;
import java.net.URL;

public class SoundLoad {
    public static Clip getSoundVolume(URL url, float minusVolume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
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

package audiorecorder.assets;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 *
 * @author monamoe
 */
public class PlayerClass {

    public void playMusic(String location) {
        try {
            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                JOptionPane.showMessageDialog(null, "Press ok to stop playing");
                //time position
                long clipTimePos = clip.getMicrosecondPosition();
                clip.stop();

                //play again at the ended time position
                clip.setMicrosecondPosition(clipTimePos);

            } else {
                System.out.println("Error, file doesnt exist");
            }

        } catch (Exception e) {
        }
    }

}

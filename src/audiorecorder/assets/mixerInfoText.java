package audiorecorder.assets;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author monamoe Getting info on audio input devices. getMixerInfo
 *
 */
public class mixerInfoText {

    public static void main(String[] args) {

        Line.Info targetDataType = new Line.Info(TargetDataLine.class);

        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();

        for (Mixer.Info mi : mixerInfos) {
            // May throw SecurityException
            Mixer mixer = AudioSystem.getMixer(mi);
            // No use if cannot record from it
            if (!mixer.isLineSupported(targetDataType)) {
                continue;
            }

            System.out.println("\nname= " + mi.getName());
            System.out.println("desc= " + mi.getDescription());
            System.out.println("vers= " + mi.getVersion());
            System.out.println("vend= " + mi.getVendor());
        }
    }

}

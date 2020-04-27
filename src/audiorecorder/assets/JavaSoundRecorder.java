package audiorecorder.assets;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author monamoe Audio recording works fine, cant change audio input to
 * desktop
 * https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Mixer.Info.html
 *
 */
public class JavaSoundRecorder {

    public static void main(String[] args) throws LineUnavailableException, InterruptedException {

        AudioFormat format = new AudioFormat(16000, 8, 2, true, true);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format is an AudioFormat object

        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();

        for (Mixer.Info mi : mixerInfos) {
            // May throw SecurityException
            Mixer mixer = AudioSystem.getMixer(mi);
            // No use if cannot record from it
            if (!mixer.isLineSupported(info)) {
                continue;
            }

            System.out.println("\nname= " + mi.getName());
            System.out.println("desc= " + mi.getDescription());
            System.out.println("vers= " + mi.getVersion());
            System.out.println("vend= " + mi.getVendor());
        }
        //recording
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line is not supported!");
        } else {

            //old records microphone)
            final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            //new
            // Gets the mixer infos
            mixerInfos = AudioSystem.getMixerInfo();
            //mixerInfo[0] is the desktop audio input device 
            Mixer mixer = AudioSystem.getMixer(mixerInfos[0]);

            /**
             * Cant seem to set the TargetDataLine to the mixer object set input
             * desktop input device
             */
//            final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getMixer();
            //the rest of this works fine
            targetDataLine.open();
            System.out.println("starting recording");

            targetDataLine.start();
            Thread stopper = new Thread(new Runnable() {
                @Override
                public void run() {
                    AudioInputStream audioStream = new AudioInputStream(targetDataLine);

                    File recording = new File("AudioRecorder.wav");

                    if (!recording.exists()) {
                        try {
                            recording.createNewFile();
                        } catch (IOException ex) {
                            Logger.getLogger(JavaSoundRecorder.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    try {
                        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, recording);
                    } catch (IOException ex) {
                        Logger.getLogger(JavaSoundRecorder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            stopper.start();
            Thread.sleep(5000);
            targetDataLine.close();
            System.out.println("recording finished");
        }
    }
}

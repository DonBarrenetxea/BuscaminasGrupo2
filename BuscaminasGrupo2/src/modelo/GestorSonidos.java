package modelo;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GestorSonidos {
    private boolean isPlaying = true;
    
    public void playLoop(String filename, float volume) {
        new Thread(() -> {
            while (isPlaying) {
                try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filename))) {
                    AudioFormat audioFormat = audioStream.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

                    try (SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info)) {
                        sourceLine.open(audioFormat);
                        adjustVolume(sourceLine, volume);
                        sourceLine.start();

                        byte[] buffer = new byte[128000];
                        int bytesRead;

                        while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1 && isPlaying) {
                            sourceLine.write(buffer, 0, bytesRead);
                        }

                        sourceLine.drain();
                    }
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void playOnce(String filename, float volume) {
        new Thread(() -> {
            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filename))) {
                AudioFormat audioFormat = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

                try (SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info)) {
                    sourceLine.open(audioFormat);
                    adjustVolume(sourceLine, volume);
                    sourceLine.start();

                    byte[] buffer = new byte[128000];
                    int bytesRead;

                    while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
                        sourceLine.write(buffer, 0, bytesRead);
                    }

                    sourceLine.drain();
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void adjustVolume(SourceDataLine sourceLine, float volume) {
        if (sourceLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl volumeControl = (FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }

    public void stopLoop() {
        isPlaying = false;
    }
}
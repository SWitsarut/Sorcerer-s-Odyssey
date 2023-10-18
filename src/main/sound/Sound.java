package main.sound;

import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    protected URL url;
    protected Clip clip;
    protected FloatControl volumeControl;

    public Sound(String audioPath) {
        this.url = getClass().getResource("/res/asset/sound/" + audioPath);
        loadAudio();
        // System.out.println("/res/asset/sound/" + audioPath);
        System.out.println(url + " loaded");
    }

    private void loadAudio() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVolume(float percent) {
        if (volumeControl != null) {
            float desiredVolume = calVolumn(volumeControl, percent);
            // Set the volume
            volumeControl.setValue(desiredVolume);
        }
    }

    public static float calVolumn(FloatControl volumeControl, float percent) {
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        float range = max - min;

        // Calculate the desired volume level within the control's range
        float desiredVolume = (float) (min + (range * percent / 100));

        // Set the volume

        return desiredVolume;
    }

    public void toggleSound() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            } else {
                this.play();
            }
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
}

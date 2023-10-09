package main;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.*;

public class Sound {
    URL url;
    Clip clip;

    public Sound(String audioPath) {
        this.url = getClass().getResource("/res/asset/sound/" + audioPath);
        loadAudio();
        System.out.println(url +" loaded");
    }

    private void loadAudio() {
        try {
            // File file = new File(url);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0); // Rewind to the beginning
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
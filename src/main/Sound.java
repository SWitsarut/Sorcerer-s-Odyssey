package main;

import javax.sound.sampled.*;

public class Sound {
    String audioPath;
    Clip clip;

    public Sound(String audioPath) {
        this.audioPath = audioPath;
        loadAudio();
        System.out.println("res/asset/sound/" + audioPath);
    }

    private void loadAudio() {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(Sound.class.getResourceAsStream("/res/asset/sound/" + audioPath));
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
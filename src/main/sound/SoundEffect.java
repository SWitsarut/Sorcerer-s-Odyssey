package main.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;

public class SoundEffect extends Sound {

    public SoundEffect(String audioPath) {
        super(audioPath);
    }

    @Override
    public void play() {
        if (url != null) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

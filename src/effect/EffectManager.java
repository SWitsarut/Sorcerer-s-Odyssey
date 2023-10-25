package effect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.sound.SoundEffect;
import util.LoadSave;

public class EffectManager {

    ArrayList<EffectPlayer> eff = new ArrayList<>();
    BufferedImage attacked[];
    BufferedImage died[];
    SoundEffect hitSound;
    SoundEffect diedSound;

    public EffectManager() {
        attacked = LoadSave.LinearAnimationLoader("asset/effect/attacked.png", 512);
        died = LoadSave.LinearAnimationLoader("asset/effect/died.png", 512);
        hitSound = new SoundEffect("attack and died/hit.wav", 80);
        diedSound = new SoundEffect("attack and died/died.wav", 80);
    }

    private void updateMember() {
        for (int i = 0; i < eff.size(); i++) {
            if (eff.get(i).getEnded()) {
                eff.remove(i);
                i--;
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        updateMember();
        for (int i = 0; i < eff.size(); i++) {
            eff.get(i).draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void update() {
        for (int i = 0; i < eff.size(); i++) {
            eff.get(i).update();
        }
    }

    public void playAttacked(int x, int y) {
        eff.add(new EffectPlayer(attacked, x, y, 0.3));
        hitSound.play();
    }

    public void playDied(int x, int y) {
        eff.add(new EffectPlayer(died, x, y, 1));
        diedSound.play();

    }
}

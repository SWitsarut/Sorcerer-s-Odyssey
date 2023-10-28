package Magic;

import java.awt.Color;
import java.awt.Graphics;

import entities.Player;
import helperClass.Coordinate;
import main.sound.SoundEffect;
import util.Constants.Config;

// Fantasy_UI (29).wav
public class Regeneration extends Buff {

    public static double cost = 50;

    Player player;

    SoundEffect castSound;
    SoundEffect endSound;
    // int scale = 3;

    public Regeneration(Player player) {
        super(5);
        this.player = player;
        castSound = new SoundEffect("Fantasy/Fantasy_UI (51).wav", 60);
        endSound = new SoundEffect("Fantasy/Fantasy_UI (52).wav", 60);
        castSound.play();
        start();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        Coordinate playercenter = player.getPlayerCenter();
        g.setColor(new Color(225, 234, 61, 20));
        g.fillOval(playercenter.x - xLvlOffset - Config.CHAR_SIZE,
                playercenter.y - yLvlOffset - Config.CHAR_SIZE, Config.CHAR_SIZE * 2,
                Config.CHAR_SIZE * 2);
    }

    @Override
    public void onUpdate() {
        player.hpRegenMul = 2;
    }

    @Override
    public void onExpired() {
        player.hpRegenMul = player.hpRegenMulDefault;
        endSound.play();
    }

    @Override
    public void onActive() {
    }

}

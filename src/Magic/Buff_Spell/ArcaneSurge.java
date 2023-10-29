package Magic.Buff_Spell;

import java.awt.Color;
import java.awt.Graphics;

import entities.Player;
import helperClass.Coordinate;
import main.sound.SoundEffect;
import util.Constants.Config;

// Fantasy_UI (29).wav
public class ArcaneSurge extends Buff {

    public static double cost = 75;

    Player player;

    SoundEffect castSound;
    SoundEffect endSound;
    // int scale = 3;

    public ArcaneSurge(Player player) {
        super(8);
        this.player = player;
        castSound = new SoundEffect("Fantasy/Fantasy_UI (29).wav", 60);
        endSound = new SoundEffect("Fantasy/Fantasy_UI (30).wav", 60);
        start();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        Coordinate playercenter = player.getPlayerCenter();
        g.setColor(new Color(122, 9, 250, 20));
        g.fillOval(playercenter.x - xLvlOffset - Config.CHAR_SIZE,
                playercenter.y - yLvlOffset - Config.CHAR_SIZE, Config.CHAR_SIZE * 2,
                Config.CHAR_SIZE * 2);
    }

    @Override
    public void onUpdate() {
        player.mpRegenMul = 1.75;
    }

    @Override
    public void onExpired() {
        player.mpRegenMul = player.mpRegenMulDefault;
        endSound.play();
    }

    @Override
    public void onActive() {
        castSound.play();
    }

}

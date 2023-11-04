package Magic.Buff_Spell;

import java.awt.Color;
import java.awt.Graphics;

import entities.Player;
import helperClass.Coordinate;
import main.sound.SoundEffect;
import util.Constants.Config;

public class Rage extends Buff {

    public static double cost = 40;

    Player player;

    SoundEffect castSound;
    SoundEffect endSound;
    // int scale = 3;

    public Rage(Player player) {
        super(5);
        this.player = player;
        castSound = new SoundEffect("magic/start.wav", 70);
        endSound = new SoundEffect("Fantasy/Fantasy_UI (52).wav", 60);
        castSound.play();
        start();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        Coordinate playerCenter = player.getPlayerCenter();
        int size = Config.CHAR_SIZE; // Adjust the size as needed

        g.setColor(new Color(255, 100, 12, 20));

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI / 6 * i;
            xPoints[i] = (int) (playerCenter.x - xLvlOffset + size * Math.cos(angle));
            yPoints[i] = (int) (playerCenter.y - yLvlOffset + size * Math.sin(angle));
        }

        g.fillPolygon(xPoints, yPoints, 6);
    }

    @Override
    public void onUpdate() {
        Player.dmgMul = 1.5;
    }

    @Override
    public void onExpired() {
        Player.dmgMul = Player.dmgMulDefault;
        endSound.play();
    }

    @Override
    public void onActive() {
    }

}

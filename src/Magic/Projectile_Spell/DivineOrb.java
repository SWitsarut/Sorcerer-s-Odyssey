package Magic.Projectile_Spell;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import entities.Player;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class DivineOrb extends Projectile {

    public static double cost = 24;

    private SoundEffect castSound;

    public DivineOrb(Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.HOLY, 70* Player.dmgMul), true, 1, targetCoor.x - 50, targetCoor.y - 50, playerCoor, 100, 100);
        setSpeed(5);
        setExpiredOntarget(false);
        castSound = new SoundEffect("magic/start.wav", 40);
        castSound.play();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        // this.drawHitbox(g, xLvlOffset, yLvlOffset);
        g.setColor(Color.YELLOW);

        g.fillOval((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset),
                (int) (hitbox.width),
                (int) (hitbox.height));

        g.setColor(new Color(196, 234, 252));
        g.fillOval((int) (hitbox.x - xLvlOffset) + 4, (int) (hitbox.y - yLvlOffset) + 4,
                (int) (hitbox.width) - 8,
                (int) (hitbox.height) - 8);
    }

    @Override
    protected void onExpired() {
        // impactSound.play();
    }

}

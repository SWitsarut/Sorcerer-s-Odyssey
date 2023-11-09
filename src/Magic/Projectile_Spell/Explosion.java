package Magic.Projectile_Spell;

import static util.Constants.Config.UPDATE_PER_TIME;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import entities.Player;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class Explosion extends Projectile {

    public static double cost = 24;

    private SoundEffect castSound;

    public Explosion(Coordinate targetCoor) {
        super(new Damage(Damage.FIRE, 30 * Player.dmgMul), true, 12 / UPDATE_PER_TIME, targetCoor.x, targetCoor.y,
                targetCoor,
                150, 150);
        setSpeed(0);
        setExpiredOntarget(false);
        castSound = new SoundEffect("magic/start.wav", 40);
        castSound.play();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
//        this.drawHitbox(g, xLvlOffset, yLvlOffset);
        g.setColor(new Color(255, 40, 12, 80));

        g.fillOval((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset),
                (int) (hitbox.width),
                (int) (hitbox.height));

        g.setColor(new Color(200, 255, 0, 70));
        g.fillOval((int) (hitbox.x - xLvlOffset) + 30, (int) (hitbox.y - yLvlOffset) + 30,
                (int) (hitbox.width) - 60,
                (int) (hitbox.height) - 60);
    }

    @Override
    protected void onExpired() {
        // impactSound.play();
    }

}

package Magic.Projectile_Spell;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import entities.Player;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class ArcaneBullet extends Projectile {

    public static double cost = 20;

    private SoundEffect castSound;

    public ArcaneBullet(Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.ARCANE, 10 * Player.dmgMul), true, 0.3, playerCoor.x, playerCoor.y, targetCoor, 16, 16);
        setSpeed(20);
        setExpiredOntarget(false);
        castSound = new SoundEffect("Fantasy/Fantasy_UI (32).wav", 60);
        castSound.play();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        // this.drawHitbox(g, xLvlOffset, yLvlOffset);

        g.setColor(new Color(122, 9, 250));
        g.fillOval((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset),
                (int) (hitbox.width),
                (int) (hitbox.height));

        g.setColor(new Color(243, 177, 245));
        g.fillOval((int) (hitbox.x - xLvlOffset + hitbox.width / 4), (int) (hitbox.y - yLvlOffset + hitbox.height / 4),
                (int) (hitbox.width / 2),
                (int) (hitbox.height / 2));
    }

    @Override
    protected void onExpired() {

    }

}

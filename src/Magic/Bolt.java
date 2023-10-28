package Magic;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class Bolt extends Projectile {

    public static double cost = 80;

    private SoundEffect castSound;

    public Bolt(Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.LIGHTING, 70), true, 0.2, playerCoor.x, playerCoor.y, targetCoor, 24, 24);
        setSpeed(90);
        setExpiredOntarget(false);
        castSound = new SoundEffect("magic/start.wav", 40);
        castSound.play();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        // this.drawHitbox(g, xLvlOffset, yLvlOffset);
        g.setColor(new Color(44, 59, 106));

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

package Magic;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class ArcaneBullet extends Projectile {

    public static double cost = 20;

    private SoundEffect castSound;
    private SoundEffect impactSound;

    public ArcaneBullet(Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.ARCANE, 10), true, 0.3, playerCoor.x, playerCoor.y, targetCoor, 16, 16);
        setSpeed(20);
        setExpiredOntarget(false);
        castSound = new SoundEffect("magic/start.wav", 70);
        impactSound = new SoundEffect("magic/end.wav", 70);
        castSound.play();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        // this.drawHitbox(g, xLvlOffset, yLvlOffset);
        g.setColor(Color.MAGENTA);
        g.fillOval((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset),
                (int) (hitbox.width),
                (int) (hitbox.height));
    }

    @Override
    protected void onExpired() {
        impactSound.play();
    }

}

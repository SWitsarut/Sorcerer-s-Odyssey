package Magic;

import static util.Constants.Config.UPDATE_PER_TIME;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Action.Damage;
import entities.Entity;
import entities.Player;
import entities.Projectile.Projectile;
import helperClass.Coordinate;

public abstract class Normal extends Projectile {

    public static double cost = 20;
    double angle_degrees;
    BufferedImage slash[];

    public static final int U = 0;
    public static final int R = 1;
    public static final int L = 2;
    public static final int D = 3;
    // private SoundEffect castSound;
    public static final double castTime = 3 / UPDATE_PER_TIME;

    public Normal(BufferedImage[] slash, Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.PHYSICAL, 35 * Player.dmgMul), true, castTime, playerCoor.x, playerCoor.y,
                targetCoor, 152,
                152);
        setSpeed(12);
        setExpiredOntarget(false);

        angle_degrees = Math.atan2(targetCoor.y - playerCoor.y, targetCoor.x - playerCoor.x) * 180 / Math.PI;
        this.slash = slash;
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {

        this.drawHitbox(g, xLvlOffset, yLvlOffset);
        if (angle_degrees <= 45 && angle_degrees >= -45) {
            g.drawImage(slash[R], (int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset), (int) (width),
                    (int) (height),
                    null);
        } else if (angle_degrees <= 135 && angle_degrees >= 45) {
            g.drawImage(slash[D], (int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset), (int) (width),
                    (int) (height),
                    null);
        } else if (angle_degrees <= -45 && angle_degrees >= -135) {
            g.drawImage(slash[U], (int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset), (int) (width),
                    (int) (height),
                    null);
        } else {
            g.drawImage(slash[L], (int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset), (int) (width),
                    (int) (height),
                    null);
        }
    }

    @Override
    protected void onExpired() {

    }

    @Override
    public abstract Damage hit(Entity entity);

}

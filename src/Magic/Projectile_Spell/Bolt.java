package Magic.Projectile_Spell;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import Magic.Magic;
import entities.Entity;
import entities.Player;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.Sound;

public class Bolt extends Projectile {

    public static double cost = 30;

    private Sound castSound;
    private Magic magic;

    public Bolt(Magic magic, Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.LIGHTING, 50 * Player.dmgMul), true, 0.2, playerCoor.x, playerCoor.y, targetCoor, 24,
                24);
        setSpeed(90);
        setExpiredOntarget(false);
        castSound = new Sound("magic/Electric_1.wav");
        castSound.setVolume(70);
        castSound.play();
        this.magic = magic;
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

    @Override
    public Damage hit(Entity entity) {
        Coordinate target = new Coordinate(entity.getCenterX(), entity.getCenterY());
        magic.spawnLesserStrike(target);
        return damage;
    }

}

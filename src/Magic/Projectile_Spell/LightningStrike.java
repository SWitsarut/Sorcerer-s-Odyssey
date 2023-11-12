package Magic.Projectile_Spell;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import Magic.Magic;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.Sound;

public class LightningStrike extends Projectile {

    private Magic magic;

    public static int cost = 38;

    public LightningStrike(Magic magic, Coordinate targetCoor) {
        super(new Damage(Damage.LIGHTING, 70), true, 0.2, targetCoor.x, targetCoor.y, targetCoor, 140, 140);
        setSpeed(0);
        setExpiredOntarget(false);
        Sound castSound = new Sound("magic/Electric_1.wav");
        castSound.setVolume(70);
        castSound.play();
        this.magic = magic;
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {

        g.setColor(new Color(44, 59, 106, 100));
        g.fillRoundRect((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset), (int) hitbox.width,
                (int) hitbox.height,
                (int) hitbox.width / 3,
                (int) hitbox.width / 3);
        g.setColor(new Color(196, 234, 252, 100));
        g.fillRoundRect((int) (hitbox.x - xLvlOffset + hitbox.width / 4),
                (int) (hitbox.y - yLvlOffset + (hitbox.height / 4)),
                (int) hitbox.width / 2,
                (int) hitbox.height / 2,
                (int) hitbox.width / 2,
                (int) hitbox.height / 2);
    }

    @Override
    protected void onExpired() {
        Coordinate top = new Coordinate((int) (targetCoor.x + width), (int) (targetCoor.y + 120 + width));
        Coordinate down = new Coordinate((int) (targetCoor.x + width), (int) (targetCoor.y - 120 + width));
        Coordinate left = new Coordinate((int) (targetCoor.x - 120 + width), (int) (targetCoor.y + width));
        Coordinate right = new Coordinate((int) (targetCoor.x + 120 + width), (int) (targetCoor.y + width));
        magic.spawnLesserStrike(top);
        magic.spawnLesserStrike(down);
        magic.spawnLesserStrike(left);
        magic.spawnLesserStrike(right);
    }
}
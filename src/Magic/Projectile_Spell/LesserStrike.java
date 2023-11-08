package Magic.Projectile_Spell;

import java.awt.Color;
import java.awt.Graphics;

import Action.Damage;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class LesserStrike extends Projectile {

    public LesserStrike(Coordinate targetCoor, SoundEffect castSoundEffect) {
        super(new Damage(Damage.LIGHTING, 24), true, 0.2, targetCoor.x, targetCoor.y, targetCoor, 70, 70);
        castSoundEffect.play();
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

    }
}
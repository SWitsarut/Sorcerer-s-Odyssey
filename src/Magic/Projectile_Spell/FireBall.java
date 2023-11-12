package Magic.Projectile_Spell;

import Action.Damage;
import Magic.Magic;
import entities.Player;
import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class FireBall extends Projectile {
    public static double cost = 40;
    private SoundEffect castSound;
    private SoundEffect impactSound;
    private Magic magic;

    public FireBall(Magic magic, Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.FIRE, 80 * Player.dmgMul), true, 0.8, playerCoor.x, playerCoor.y, targetCoor, 32, 32);
        setSpeed(9);
        this.magic = magic;
        setExpiredOntarget(true);
        castSound = new SoundEffect("magic/start.wav", 70);
        impactSound = new SoundEffect("magic/end.wav", 70);
        castSound.play();
    }

    @Override
    protected void onExpired() {
        impactSound.play();
        Coordinate curCoor = new Coordinate(getCenterX(), getCenterY());
        magic.castExplosion(curCoor);
    }

}

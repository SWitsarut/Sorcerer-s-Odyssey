package Magic;

import entities.Projectile.Projectile;
import helperClass.Coordinate;
import main.sound.SoundEffect;

public class FireBall extends Projectile {

    public static double cost = 35;

    private SoundEffect castSound;
    private SoundEffect impactSound;

    public FireBall(Coordinate playerCoor, Coordinate targetCoor) {
        super(true, 0.5, playerCoor.x, playerCoor.y, targetCoor, 32, 32);
        setSpeed(7);
        setExpiredOntarget(true);
        castSound = new SoundEffect("magic/start.wav", 70);
        impactSound = new SoundEffect("magic/end.wav", 70);
        castSound.play();
    }

    @Override
    protected void onExpired() {
        impactSound.play();
    }

}

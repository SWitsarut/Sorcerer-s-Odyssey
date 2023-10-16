package Magic;

import entities.Projectile.Projectile;
import helperClass.Coordinate;

public class FireBall extends Projectile {

    public static double cost = 35;

    public FireBall(Coordinate playerCoor, Coordinate targetCoor) {
        super(true, playerCoor.x, playerCoor.y, targetCoor, 32, 32);
        setLifeTime(1);
        setSpeed(7);
    }

}

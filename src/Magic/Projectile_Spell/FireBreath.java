package Magic.Projectile_Spell;

import Action.Damage;
import entities.Projectile.Projectile;
import helperClass.Coordinate;

public class FireBreath extends Projectile {

    public static double cost = 4;

    public FireBreath(Coordinate playerCoor, Coordinate targetCoor) {
        super(new Damage(Damage.FIRE, 8), true, 0.3, playerCoor.x, playerCoor.y, targetCoor, 32, 32);
        damage = new Damage(Damage.FIRE, 12);
        setExpiredOntarget(false);
        setExpiredOnHit(false);
        setSpeed(10);
    }

    @Override
    protected void onExpired() {
    }

}

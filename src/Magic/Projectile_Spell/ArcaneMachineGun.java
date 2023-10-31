package Magic.Projectile_Spell;

import Action.Damage;
import helperClass.Coordinate;

public class ArcaneMachineGun extends ArcaneBlast {

    public static double cost = 10;

    public ArcaneMachineGun(Coordinate playerCoor, Coordinate targetCoor) {
        super(playerCoor, targetCoor);
        damage = new Damage(Damage.ARCANE, 24.5);
        setExpiredOntarget(false);
        setExpiredOnHit(true);
        setSpeed(24);
    }
}

package entities.Enemy;

import java.awt.image.BufferedImage;

import Action.Def;

public class CorruptedTreant extends Enemy {

    public CorruptedTreant(int mapIndex, BufferedImage[] animation, float x, float y) {
        super(mapIndex, animation, 2, Def.CorruptedTreantDef, x, y, 32, 32);
        maxAniFrame = 4;
        damage = 22;
        speed = 4;
        aggroRange = 420;
        setHp(200);
    }

}

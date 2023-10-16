package entities.Enemy;

import java.awt.image.BufferedImage;

import Action.Def;

public class CorruptedTreant extends Enemy {

    public CorruptedTreant(BufferedImage[] animation, float x, float y) {
        super(animation, 2, Def.CorruptedTreantDef, x, y, 32, 32);
        maxAniFrame = 4;
        damage = 10;
    }

}

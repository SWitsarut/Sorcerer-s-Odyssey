package entities.Enemy;

import Action.Def;

import java.awt.image.BufferedImage;

public class Skeleton extends Enemy {
    public Skeleton(int mapIndex, BufferedImage[] animation, float x, float y) {
        super(mapIndex, animation, 2, Def.Skeleton, x, y, 32, 32);
        maxAniFrame = 1;
        damage = 40;
        speed = 5;
        aggroRange = 500;
        setHp(80);
    }
}

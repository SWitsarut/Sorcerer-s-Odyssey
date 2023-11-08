package entities.Enemy;

import java.awt.image.BufferedImage;

import Action.Def;

public class GoblinWolfRider extends Enemy {

    public GoblinWolfRider(int mapIndex, BufferedImage[] animation, float x, float y) {
        super(mapIndex, animation, 2.5f, Def.GoblinWolfRider, x, y, 32, 32);
        maxAniFrame = 4;
        damage = 35;
        speed = 4;
        aggroRange = 420;
        setHp(175);
    }
}

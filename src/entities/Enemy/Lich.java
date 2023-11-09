package entities.Enemy;

import java.awt.image.BufferedImage;

import Action.Def;

public class Lich extends Enemy {

    public Lich(int mapIndex, BufferedImage[] animation, float x, float y) {
        super(mapIndex, animation, 2, Def.Lich, x, y, 64, 64);
        maxAniFrame = 10;
        damage = 60;
        speed = 4;
        aggroRange = 420;
        setHp(600);
    }

}

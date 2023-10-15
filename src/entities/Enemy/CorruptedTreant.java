package entities.Enemy;

public class CorruptedTreant extends Enemy {

    public CorruptedTreant(float x, float y) {
        super("CorruptedTreantIdle.png", 2, x, y, 32, 32);
        maxAniFrame = 4;
        damage = 10;
        loadImage();
    }

}

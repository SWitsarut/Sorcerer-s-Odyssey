package event;

import gamestates.Playing;
import helperClass.Coordinate;
import helperClass.UpdateCounter;

import java.awt.*;

public class DogChallenge implements Event {

    private Playing playing;

    private int mapIndex;

    public boolean active;
    private Spawner spawner;

    public DogChallenge(Playing playing, double second, int mapIndex) {
        this.spawner = new Spawner(second, false);
        this.mapIndex = mapIndex;
        active = true;
        spawner.start();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        System.out.println(spawner.curCount + "/" + spawner.targetCount);
    }

    @Override
    public void update() {
        spawner.update();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    class Spawner extends UpdateCounter {
        int time = 0;
        int maxTime = 5;

        public Spawner(double second, boolean cycle) {
            super(second, cycle);
        }

        @Override
        public void onUpdate() {
            if (time < maxTime) {
                Coordinate playerCoor = playing.getPlayer().getPlayerCenter();
                playing.getEnemyManager().spawnSkeletion(mapIndex, playerCoor.x - 400, playerCoor.y, true);
                playing.getEnemyManager().spawnSkeletion(mapIndex, playerCoor.x + 400, playerCoor.y, true);
                time++;
            } else {
                active = false;
            }
        }


    }

}

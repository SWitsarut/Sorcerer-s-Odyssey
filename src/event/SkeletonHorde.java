package event;

import gamestates.Playing;
import helperClass.Coordinate;
import helperClass.UpdateCounter;

import java.awt.*;

public class SkeletonHorde implements Event {

    public boolean active;
    public int mapIndex;
    HordeSpawn spawnCounter;

    public SkeletonHorde(Playing game, int mapIndex, double second, boolean infinite) {
        spawnCounter = new HordeSpawn(game, second, infinite);
        System.out.println("start");
        this.mapIndex = mapIndex;
        active = true;
        spawnCounter.start();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {

    }

    @Override
    public void update() {
        // System.out.println();
        spawnCounter.update();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public int getMapIndex() {
        return mapIndex;
    }

    private class HordeSpawn extends UpdateCounter {

        // EnemyManager em;
        private final Playing playing;

        public HordeSpawn(Playing playing, double second, boolean cycle) {
            super(second, cycle);
            this.playing = playing;
        }

        @Override
        public void onUpdate() {
            Coordinate playerCoor = playing.getPlayer().getPlayerCenter();
            playing.getEnemyManager().spawnSkeletion(mapIndex, playerCoor.x - 400, playerCoor.y, true);
            playing.getEnemyManager().spawnSkeletion(mapIndex, playerCoor.x + 400, playerCoor.y, true);
        }

    }
}

package event;

import java.awt.Graphics;

import gamestates.Playing;
import helperClass.Coordinate;
import helperClass.UpdateCounter;

public class WolfHorde implements Event {

    public boolean active;
    public int mapIndex;
    HordeSpawn spawnCounter;

    public WolfHorde(Playing game, int mapIndex, double second, boolean infinite) {
        spawnCounter = new HordeSpawn(game, second, infinite);
        this.mapIndex = mapIndex;
        active = true;
        spawnCounter.start();
    }

    @Override
    public void update() {
        // System.out.println();
        spawnCounter.update();
    }

    private class HordeSpawn extends UpdateCounter {

        // EnemyManager em;
        private Playing game;
        public int mapIndex;

        public HordeSpawn(Playing game, double second, boolean cycle) {
            super(second, cycle);
            this.game = game;
        }

        @Override
        public void onUpdate() {
            Coordinate platerCoordinate = game.getPlayer().getPlayerCenter();
            game.getEnemyManager().spawnWolfRider(mapIndex, platerCoordinate.x, platerCoordinate.y + 350, true);
            game.getEnemyManager().spawnWolfRider(mapIndex, platerCoordinate.x, platerCoordinate.y - 350, true);
        }

    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
    }

}

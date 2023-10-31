package event;

import java.awt.Graphics;

import helperClass.Coordinate;
import helperClass.UpdateCounter;
import main.Game;

public class MonsterHorde implements Event {

    public boolean active;
    HordeSpawn spawnCounter;

    public MonsterHorde(Game game, double second, boolean infinite) {
        spawnCounter = new HordeSpawn(game, second, infinite);
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
        private Game game;

        public HordeSpawn(Game game, double second, boolean cycle) {
            super(second, cycle);
            this.game = game;
        }

        @Override
        public void onUpdate() {
            Coordinate platerCoordinate = game.getPlaying().getPlayer().getPlayerCenter();
            game.getPlaying().getEnemyManager().spawnWolfRider(platerCoordinate.x, platerCoordinate.y + 350, true);
            game.getPlaying().getEnemyManager().spawnWolfRider(platerCoordinate.x, platerCoordinate.y - 350, true);
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

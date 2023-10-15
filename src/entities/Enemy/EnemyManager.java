package entities.Enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entities.Player;
import gamestates.Playing;

public class EnemyManager {

    ArrayList<Enemy> enemies;
    private Player player;

    public EnemyManager(Playing playing) {
        player = playing.getPlayer();
        enemies = new ArrayList<>();
        enemies.add(new CorruptedTreant(900, 900));
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (int i = 0; i < enemies.size(); i++) {
            g.setColor(Color.RED);
            enemies.get(i).draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (player.getHitbox().intersects(enemy.getHitbox())) {
                player.getAttacked(enemy.damage);
            }
            enemy.update();
        }
    }
}

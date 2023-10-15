package entities.Enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class EnemyManager {
    ArrayList<Enemy> enemies;

    public EnemyManager() {
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
            enemies.get(i).update();
        }
    }
}

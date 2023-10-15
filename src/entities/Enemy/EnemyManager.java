package entities.Enemy;

import java.awt.Graphics;
import java.util.ArrayList;

public class EnemyManager {
    ArrayList<Enemy> enemies;

    public EnemyManager() {
        enemies = new ArrayList<>();
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g, xLvlOffset, yLvlOffset);
        }
    }
}

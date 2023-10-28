package entities.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Magic.Magic;
import effect.EffectManager;
import entities.Player;
import entities.Projectile.Projectile;
import gamestates.Playing;
import util.LoadSave;

public class EnemyManager {

    BufferedImage[] corruptedTreantAnimation;
    ArrayList<Enemy> enemies;
    private Player player;
    private EffectManager effectManager;
    private Magic magic;

    public EnemyManager(Playing playing) {
        player = playing.getPlayer();
        effectManager = playing.getEffectManager();
        magic = playing.getMagic();
        enemies = new ArrayList<>();
        LoadAnimation();
        enemies.add(new CorruptedTreant(corruptedTreantAnimation, 500, 500));
    }

    private void initEnemy(int level) {
        switch (level) {

        }
    }

    private void LoadAnimation() {
        corruptedTreantAnimation = LoadSave.LinearAnimationLoader("entity/enemy/CorruptedTreantIdle.png", 16);
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            int enemyCenterX = (int) (enemy.getHitbox().x + enemy.getHitbox().width / 2);
            int enemyCenterY = (int) (enemy.getHitbox().y + enemy.getHitbox().height / 2);
            if (!enemy.isDead) {
                enemy.update(player);
                if (player.getHitbox().intersects(enemy.getHitbox())) {
                    player.getAttacked(enemy.damage);
                }
                for (int j = 0; j < magic.projectiles.size(); j++) {
                    Projectile projectile = magic.projectiles.get(j);
                    if (!projectile.enemyhitted.contains(enemy)
                            && enemy.getHitbox().intersects(projectile.getHitbox())
                            && projectile.isPlayerOwn()) {
                        enemy.getAttacked(projectile.damage);
                        effectManager.playAttacked(enemyCenterX,
                                enemyCenterY);
                        projectile.enemyhitted.add(enemy);
                    }
                }
            } else {
                effectManager.playDied(enemyCenterX, enemyCenterY);
                enemies.remove(enemy);
            }
        }
    }

}

package entities.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Levels.LevelManager;
import Magic.Magic;
import effect.EffectManager;
import entities.Player;
import entities.Projectile.Projectile;
import gamestates.Playing;
import util.LoadSave;

public class EnemyManager {

    BufferedImage[] corruptedTreantAnimation;
    BufferedImage[] goblinWolfRiderAni;

    BufferedImage[] skeletonAni;

    private ArrayList<Enemy> enemies;
    private Player player;
    private EffectManager effectManager;
    private Magic magic;

    public static int MOB_COUNT = 0;
    public static final int MOBCAP = 45;

    public EnemyManager(Playing playing) {
        player = playing.getPlayer();
        effectManager = playing.getEffectManager();
        magic = playing.getMagic();
        enemies = new ArrayList<>();
        LoadAnimation();
        initEnemy(LevelManager.curMapIndex);

        // enemies.add(new CorruptedTreant(corruptedTreantAnimation, 500, 500));
    }

    public void initEnemy(int level) {
        // enemies.clear();
        switch (level) {
            case LevelManager.LAVADUNGEON:
                addMob(new CorruptedTreant(LevelManager.LAVADUNGEON, corruptedTreantAnimation, 500, 500));
                addMob(new CorruptedTreant(LevelManager.LAVADUNGEON, corruptedTreantAnimation, 750, 500));
                addMob(new CorruptedTreant(LevelManager.LAVADUNGEON, corruptedTreantAnimation, 800, 500));
                addMob(new CorruptedTreant(LevelManager.LAVADUNGEON, corruptedTreantAnimation, 1000, 500));
                break;
        }
    }

    private void LoadAnimation() {
        corruptedTreantAnimation = LoadSave.LinearAnimationLoader("entity/enemy/CorruptedTreantIdle.png", 16);
        goblinWolfRiderAni = LoadSave.LinearAnimationLoader("entity/enemy/GoblinWolfRiderIdleSide.png", 16);
        skeletonAni = LoadSave.LinearAnimationLoader("entity/[VerArc Stash] Mini_Characters/char_35.png", 16);
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.mapIndex == LevelManager.curMapIndex) {
                enemy.draw(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    public void enemiesClear() {
        enemies.clear();
    }

    public void spawnTreant(int mapIndex, int x, int y) {
        addMob(new CorruptedTreant(mapIndex, corruptedTreantAnimation, x, y));
    }

    public void spawnSkeletion(int mapIndex, int x, int y) {
        addMob(new Skeleton(mapIndex, skeletonAni, x, y));
    }

    public void spawnSkeletion(int mapIndex, int x, int y, boolean aggro) {

        Enemy Skeleton = new Skeleton(mapIndex, skeletonAni, x, y);
        Skeleton.attacked = aggro;
        addMob(Skeleton);

    }

    public void spawnWolfRider(int mapIndex, int x, int y, boolean aggro) {
        Enemy wolfRider = new GoblinWolfRider(mapIndex, goblinWolfRiderAni, x, y);
        wolfRider.attacked = aggro;
        addMob(wolfRider);

    }

    public void addMob(Enemy enemy) {
        if (!(MOB_COUNT > MOBCAP)) {
            enemies.add(enemy);
        }
    }

    public void update() {
        MOB_COUNT = enemies.size();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            int enemyCenterX = (int) (enemy.getHitbox().x + enemy.getHitbox().width / 2);
            int enemyCenterY = (int) (enemy.getHitbox().y + enemy.getHitbox().height / 2);
            if (!enemy.isDead) {
                if (enemy.mapIndex == LevelManager.curMapIndex) {

                    enemy.update(player);
                    if (player.getHitbox().intersects(enemy.getHitbox())) {
                        player.getAttacked(enemy.damage);
                    }

                    for (int j = 0; j < magic.projectiles.size(); j++) {
                        Projectile projectile = magic.projectiles.get(j);
                        if (!projectile.enemyhitted.contains(enemy)
                                && enemy.getHitbox().intersects(projectile.getHitbox())
                                && projectile.isPlayerOwn()) {
                            enemy.getAttacked(projectile.hit(enemy));
                            effectManager.playAttacked(enemyCenterX,
                                    enemyCenterY, false);
                            projectile.enemyhitted.add(enemy);
                        }
                    }
                }
            } else {
                effectManager.playDied(enemyCenterX, enemyCenterY);
                enemies.remove(enemy);
            }
        }
    }

}

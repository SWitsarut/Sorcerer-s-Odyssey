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
import helperClass.Coordinate;
import main.Manager;
import util.Helper;
import util.LoadSave;

public class EnemyManager implements Manager {

    BufferedImage[] corruptedTreantAnimation;
    BufferedImage[] goblinWolfRiderAni;
    BufferedImage[] skeletonAni;

    BufferedImage[] lichAni;

    private ArrayList<Enemy> enemies;
    private Player player;
    private EffectManager effectManager;
    private Magic magic;

    public static int[] MOB_COUNT = new int[6];
    public static final int MOB_CAP = 30;
    private boolean[] spawned = new boolean[6];

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
            case LevelManager.FOREST:
                if (!spawned[LevelManager.FOREST]) {
                    Coordinate[] enemyCoors = new Coordinate[15];

                    enemyCoors[0] = Helper.getPosFromTile(new Coordinate(9, 10));
                    enemyCoors[1] = Helper.getPosFromTile(new Coordinate(14, 11));
                    enemyCoors[2] = Helper.getPosFromTile(new Coordinate(10, 28));
                    enemyCoors[3] = Helper.getPosFromTile(new Coordinate(20, 23));
                    enemyCoors[4] = Helper.getPosFromTile(new Coordinate(35, 12));
                    enemyCoors[5] = Helper.getPosFromTile(new Coordinate(29, 8));
                    enemyCoors[6] = Helper.getPosFromTile(new Coordinate(25, 20));
                    enemyCoors[7] = Helper.getPosFromTile(new Coordinate(14, 5));
                    enemyCoors[8] = Helper.getPosFromTile(new Coordinate(36, 3));
                    enemyCoors[9] = Helper.getPosFromTile(new Coordinate(28, 13));
                    enemyCoors[10] = Helper.getPosFromTile(new Coordinate(12, 25));
                    enemyCoors[11] = Helper.getPosFromTile(new Coordinate(9, 10));
                    enemyCoors[12] = Helper.getPosFromTile(new Coordinate(9, 10));
                    enemyCoors[13] = Helper.getPosFromTile(new Coordinate(9, 10));
                    enemyCoors[14] = Helper.getPosFromTile(new Coordinate(9, 10));

                    for (Coordinate enemyCoor : enemyCoors) {
                        addMob(new CorruptedTreant(LevelManager.FOREST, corruptedTreantAnimation, enemyCoor.x, enemyCoor.y));
                    }
                    spawned[LevelManager.FOREST] = true;
                }
                break;
            case LevelManager.CAVE:
                Coordinate[] enemyCoors = new Coordinate[3];

                enemyCoors[0] = Helper.getPosFromTile(new Coordinate(37, 37));
                enemyCoors[1] = Helper.getPosFromTile(new Coordinate(51, 37));
                enemyCoors[2] = Helper.getPosFromTile(new Coordinate(51, 51));
                for (Coordinate enemyCoor : enemyCoors) {
                    addMob(new Lich(LevelManager.CAVE, lichAni, enemyCoor.x, enemyCoor.y));
                }
                break;
        }
    }

    private void LoadAnimation() {
        corruptedTreantAnimation = LoadSave.LinearAnimationLoader("entity/enemy/CorruptedTreantIdle.png", 16);
        goblinWolfRiderAni = LoadSave.LinearAnimationLoader("entity/enemy/GoblinWolfRiderIdleSide.png", 16);
        skeletonAni = LoadSave.LinearAnimationLoader("entity/[VerArc Stash] Mini_Characters/char_35.png", 16);
        lichAni = LoadSave.LinearAnimationLoader("entity/enemy/BloodLichIdleSide.png", 32);
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

    public void spawnLich(int mapIndex, int x, int y, boolean aggro) {
        Enemy lich = new Lich(mapIndex, lichAni, x, y);
        lich.attacked = aggro;
        addMob(lich);
    }

    public void addMob(Enemy enemy) {
        if (!(MOB_COUNT[enemy.mapIndex] > MOB_CAP)) {
            enemies.add(enemy);
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    public void update() {
//        MOB_COUNT[] =enemies.size();
        int[] newMobCount = new int[MOB_COUNT.length];
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            newMobCount[enemy.mapIndex]++;
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
                        if (!projectile.enemyhitted.contains(enemy) && enemy.getHitbox().intersects(projectile.getHitbox()) && projectile.isPlayerOwn()) {
                            enemy.getAttacked(projectile.hit(enemy));
                            effectManager.playAttacked(enemyCenterX, enemyCenterY, false);
                            projectile.enemyhitted.add(enemy);
                        }
                    }
                }
            } else {
                effectManager.playDied(enemyCenterX, enemyCenterY);
                enemies.remove(enemy);
            }
        }
        MOB_COUNT = newMobCount;
    }

}

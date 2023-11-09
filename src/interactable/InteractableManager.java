package interactable;

import java.awt.Graphics;
import java.util.ArrayList;

import Inventory.Item;
import Levels.LevelManager;
import entities.Player;
import gamestates.Playing;
import helperClass.Coordinate;
import util.Helper;

public class InteractableManager {

    ArrayList<Interactable> interactables;

    Playing playing;
    Player player;

    public InteractableManager(Playing playing) {
        this.playing = playing;
        interactables = new ArrayList<>();
        initPortal(LevelManager.curMapIndex);
        this.player = playing.getPlayer();
    }

    public void initPortal(int mapIndex) {
        interactables.clear();
        switch (mapIndex) {
            case LevelManager.LAVADUNGEON:
                spawnPotalatAtTile(LevelManager.mapNameArr[LevelManager.ROOFTOP], LevelManager.LAVADUNGEON, 29, 2,
                        new Coordinate(1410, 2340), 3,
                        3);
                break;
            case LevelManager.ROOFTOP:
                spawnPotalatAtTile(LevelManager.mapNameArr[LevelManager.BRIDGE], LevelManager.ROOFTOP, 31, 19,
                        new Coordinate(945, 3700), 5,
                        5);
                spawnPotalatAtTile(LevelManager.mapNameArr[LevelManager.LAVADUNGEON], LevelManager.ROOFTOP, 28, 48,
                        new Coordinate(1455, 205), 3,
                        3);
                break;
            case LevelManager.BRIDGE:
                // top potal
                spawnPotalatAtTile(LevelManager.mapNameArr[LevelManager.DOG], LevelManager.BRIDGE, 12, 0,
                        new Coordinate(1655, 3050), 16,
                        8);
                // //bot potal
                spawnPotalatAtTile(LevelManager.mapNameArr[LevelManager.ROOFTOP], LevelManager.BRIDGE, 12, 72,
                        new Coordinate(1450, 1000), 16,
                        8);
                break;
            case LevelManager.DOG:
                LockPotalatAtTile(LevelManager.mapNameArr[LevelManager.ROOFTOP], LevelManager.DOG, 30, 14,
                        new Coordinate(1410, 2340), 13, 10, Item.dog_sigil, "dog spirit blocking");
                break;
        }

    }

    public void spawnPotalatAtTile(String targetMap, int mapIndex, int xTile, int yTile, Coordinate des, int width,
                                   int height) {
        Coordinate pos = Helper.getPosFromTile(xTile, yTile);
        Potal potal = new Potal(playing, targetMap, mapIndex);
        potal.initHitbox(pos.x, pos.y, width, height);
        potal.setDes(des);
        interactables.add(potal);
    }

    public void LockPotalatAtTile(String targetMap, int mapIndex, int xTile, int yTile, Coordinate des, int width,
                                  int height, int requireItem, String lockMsg) {
        Coordinate pos = Helper.getPosFromTile(xTile, yTile);
        LockPotal potal = new LockPotal(playing, targetMap, mapIndex, requireItem, lockMsg);
        potal.initHitbox(pos.x, pos.y, width, height);
        potal.setDes(des);
        interactables.add(potal);
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (int i = 0; i < interactables.size(); i++) {
            Interactable interactable = interactables.get(i);
            interactable.draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void update() {
        for (int i = 0; i < interactables.size(); i++) {
            Interactable interactable = interactables.get(i);
            interactable.update();
            if (player.getHitbox().intersects(interactable.getHitbox())) {
                interactable.setShowMessage(true);
                if (player.interacting) {
                    interactable.interact();
                }
            } else {
                interactable.setShowMessage(false);
            }
        }
    }

}

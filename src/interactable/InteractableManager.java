package interactable;

import java.awt.Graphics;
import java.util.ArrayList;

import Levels.LevelManager;
import entities.Player;
import gamestates.Playing;
import helperClass.Coordinate;
import main.Game;
import util.Helper;

public class InteractableManager {

    ArrayList<Interactable> interactables;

    Playing playing;

    public InteractableManager(Playing playing) {
        this.playing = playing;
        interactables = new ArrayList<>();
        initPortal(LevelManager.curMapIndex);
    }

    public void initPortal(int mapIndex) {
        interactables.clear();
        switch (mapIndex) {
            case LevelManager.CAVE:
                spawnPotalatAtTile("temple of fire", 10, 10);
                break;
        }
    }

    public void spawnPotalatAtTile(String targetMap, int xTile, int yTile) {
        Coordinate pos = Helper.getPosFromTile(xTile, yTile);
        Potal potal = new Potal(playing.getLevelManager(), targetMap);
        potal.initHitbox(pos.x, pos.y, 4, 4);
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
            if (playing.getPlayer().getHitbox().intersects(interactable.getHitbox())) {
                interactable.setShowMessage(true);
            } else {
                interactable.setShowMessage(false);
            }
        }
    }

}

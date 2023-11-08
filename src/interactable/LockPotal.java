package interactable;

import Levels.LevelManager;
import gamestates.Playing;

public class LockPotal extends Potal {

    private int requireItem;
    private String lockMassage;
    private String originalMassage;

    public LockPotal(Playing playing, String mapName, int mapIndex, int requireItem, String lockMassage) {
        super(playing, mapName, mapIndex);
        this.requireItem = requireItem;
        this.lockMassage = lockMassage;
        originalMassage = massage;
    }

    @Override
    public void onSubmit() {
        if (playing.getPlayer().getInv().check(requireItem)) {
            levelManager.curMapEventOnExit();
            LevelManager.curMapIndex = levelManager.getIndexFromMapName(mapName);
            playing.getPlayer().setPosition(targetCoor);
            playing.handleMapChange();
            playing.getPlayer().interacting = false;
            playing.getPlayer().interactBlock = true;
        }
    }

    @Override
    public void update() {
        if (playing.getPlayer().getInv().check(requireItem)) {
            massage = originalMassage;
        } else {
            massage = lockMassage;
        }
    }
}

package interactable;

import gamestates.Playing;

public class LockPortal extends Portal {

    private int[] requireItem;
    private String lockMassage;
    private String originalMassage;

    public LockPortal(String massage, Playing playing, int targetMap, int mapIndex, int[] requireItem, String lockMassage) {
        super(playing, targetMap, mapIndex, massage);
        this.requireItem = requireItem;
        this.lockMassage = lockMassage;
        originalMassage = massage;
    }

    @Override
    public void onSubmit() {
        if (playing.getPlayer().getInv().check(requireItem)) {
            playing.goTomap(targetMap);
            playing.getPlayer().setPosition(targetCoor);
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

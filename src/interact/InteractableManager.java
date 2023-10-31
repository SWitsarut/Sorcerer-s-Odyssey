package interact;

import main.Game;

// import static util.ObjectType.*;

import java.awt.Graphics;
import java.util.ArrayList;

public class InteractableManager {

    private ArrayList<Interactable> interactables;

    private Game game;

    public InteractableManager(Game game) {
        this.game = game;
        initClasses();
    }

    private void initClasses() {
        interactables = new ArrayList<>();
    }

    public void monsterHordeEvent() {
        interactables.add(new MonsterHorde(game, 1, true));
    }

    public void monsterHordeEnd() {
        for (int i = 0; i < interactables.size(); i++) {
            Interactable interactable = interactables.get(i);
            if (interactable instanceof MonsterHorde) {
                ((MonsterHorde) interactable).active = false;
            }
        }
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
            if (interactable.isActive()) {
                interactable.update();
            } else {
                interactables.remove(interactable);
                i--;
            }

        }
    }

}

package event;

// import static util.ObjectType.*;

import java.awt.Graphics;
import java.util.ArrayList;

import gamestates.Playing;

public class EventManager {

    public ArrayList<Event> interactables;

    private Playing game;

    public EventManager(Playing game) {
        this.game = game;
        initClasses();
    }

    private void initClasses() {
        interactables = new ArrayList<>();
    }

    public void wolfHordeStart(int mapIndex) {
        interactables.add(new WolfHorde(game, mapIndex, 1.25, true));
    }

    public void wolfHordEnd(int mapIndex) {
        for (Event interactable : interactables) {
            if (interactable instanceof WolfHorde) {
                ((WolfHorde) interactable).active = false;
            }
        }
    }

    public void skeletonHordeStart(int mapIndex) {
        interactables.add(new SkeletonHorde(game, mapIndex, 0.8, true));
    }

    public void startDog(int mapIndex) {
        interactables.add(new DogChallenge(game, 1, mapIndex));
    }

    public void skeletonHordeEnd(int mapIndex) {
        for (Event interactable : interactables) {
            if (interactable instanceof SkeletonHorde) {
                ((SkeletonHorde) interactable).active = false;
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Event interactable : interactables) {
            interactable.draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void update() {
        for (int i = 0; i < interactables.size(); i++) {
            Event interactable = interactables.get(i);
            if (interactable.isActive()) {
                interactable.update();
            } else {
                interactables.remove(interactable);
                i--;
            }

        }
    }

}

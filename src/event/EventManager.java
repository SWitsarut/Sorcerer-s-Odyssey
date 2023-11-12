package event;

// import static util.ObjectType.*;

import java.awt.Graphics;
import java.util.ArrayList;

import gamestates.Playing;
import helperClass.Coordinate;
import main.Manager;

public class EventManager implements Manager {

    public ArrayList<Event> events;

    private Playing game;

    public EventManager(Playing game) {
        this.game = game;
        initClasses();
    }

    private void initClasses() {
        events = new ArrayList<>();
    }

    public void wolfHordeStart(int mapIndex) {
        events.add(new WolfHorde(game, mapIndex, 1.25, true));
    }

    public void wolfHordEnd(int mapIndex) {
        for (Event event : events) {
            if (event instanceof WolfHorde) {
                ((WolfHorde) event).active = false;
            }
        }
    }


    public void skeletonHordeStart(int mapIndex) {
        events.add(new SkeletonHorde(game, mapIndex, 0.8, true));
    }

    public void startChallenge(int mapIndex) {
        boolean contain = false;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event instanceof Challenge) {
                contain = true;
                break;
            }
        }
        if (!contain) {
            Coordinate playerCoor = game.getPlayer().getPlayerCenter();
            game.getEnemyManager().spawnLich(mapIndex, playerCoor.x + 400, playerCoor.y + 400, true);
            game.getEnemyManager().spawnLich(mapIndex, playerCoor.x + 400, playerCoor.y - 400, true);
            game.getEnemyManager().spawnLich(mapIndex, playerCoor.x - 400, playerCoor.y + 400, true);
            game.getEnemyManager().spawnLich(mapIndex, playerCoor.x - 400, playerCoor.y - 400, true);

            events.add(new Challenge(game, mapIndex, 3.5, true));
        }
    }

    public void skeletonHordeEnd(int mapIndex) {
        for (Event interactable : events) {
            if (interactable instanceof SkeletonHorde) {
                ((SkeletonHorde) interactable).active = false;
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Event interactable : events) {
            interactable.draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void update() {
        for (int i = 0; i < events.size(); i++) {
            Event interactable = events.get(i);
            if (interactable.isActive()) {
                interactable.update();
            } else {
                events.remove(interactable);
                i--;
            }

        }
    }

}

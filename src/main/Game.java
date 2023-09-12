package main;

import java.awt.Graphics;

import entity.Player;
import static util.Constants.Config.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = MAX_FPS;
    private final int UPS_SET = MAX_UPS;

    private Player player;

    public Game() {
        initClass();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClass() {
        player = new Player(0, 0);
    }

    public void render(Graphics g) {
        player.render(g);
    }

    public void update() {
        player.update();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frame = 0;
        int update = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {
                // update
                update++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                frame++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("fps : " + frame + " | ups : " + update);
                frame = 0;
                update = 0;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }


}

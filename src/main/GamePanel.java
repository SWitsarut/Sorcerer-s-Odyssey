package main;

import javax.swing.JPanel;

import input.KeyboardInput;
import input.MouseInput;

import java.awt.Dimension;
import java.awt.Graphics;
import static util.Constants.Config.*;

public class GamePanel extends JPanel {

    private MouseInput mouseInput;

    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));

        setPanelSize();
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void render(Graphics g) {
        super.paintComponent(g);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.update();
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}

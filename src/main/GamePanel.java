package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import input.KeyboardInput;
import input.MouseInput;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static util.Constants.PlayerConstants.*;
import static util.Constants.Direction.*;

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
        Dimension size = new Dimension(1280, 800);
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

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

public class GamePanel extends JPanel {

    MouseInput mouseInput;
    private double deltaX = 100, deltaY = 100;
    private BufferedImage img;
    private BufferedImage[] idleAni, walkAni;
    private int aniTick, aniIndex, aniFramePersecond = 15;

    private int tileSize = 32;

    public GamePanel() {
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));

        importImg();

        setPanelSize();
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/img/entity/rogue.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        loadIdleAni();
    }

    private void loadIdleAni() {
        idleAni = new BufferedImage[10];
        walkAni = new BufferedImage[10];
        for (int i = 0; i < idleAni.length; i++) {
            idleAni[i] = img.getSubimage(i * tileSize, tileSize * 0, tileSize, tileSize);
            walkAni[i] = img.getSubimage(i * tileSize, tileSize * 4, tileSize, tileSize);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniFramePersecond) {
            aniTick = 0;
            aniIndex = (aniIndex + 1) % 10;
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();
        g.drawImage(idleAni[aniIndex], 0, 0, 64, 64, null);
        g.drawImage(walkAni[aniIndex], tileSize, 0, 64, 64, null);
    }

}

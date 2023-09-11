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
    private BufferedImage img, subImage;

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
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private int animateX = 0;
    private int animateY = 0;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImage = getNextAnimate(img);
        g.drawImage(subImage, 0, 0, 64, 64, null);
    }

    private BufferedImage getNextAnimate(BufferedImage img) {
        BufferedImage sub = img.getSubimage(animateX, animateY, tileSize, tileSize);
        animateX = (animateX + tileSize) % (tileSize * 10);
        return sub;
    }
}

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // g.drawimage(null, x, y, null);
    }
}

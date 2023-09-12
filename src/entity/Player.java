package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import static util.Constants.PlayerConstants.*;

public class Player extends Entity {
    private int charectorSize = 32;
    private BufferedImage[][] animation;
    private boolean up, down, right, left;
    private int aniTick = 0, aniIndex, aniFramePersecond = 15;

    private int palyerAction = IDLE;
    private double speed = 1.25;

    public Player(float x, float y) {
        super(x, y);
        loadAnimation();
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniFramePersecond) {
            aniTick = 0;
            aniIndex = (aniIndex + 1) % 10;
        }
    }

    public void update() {
        move();
        updateAnimationTick();
    }

    public void render(Graphics g) {
        g.drawImage(animation[palyerAction][aniIndex], (int) x, (int) y, 64, 64, null);
    }

    private void loadAnimation() {
        InputStream is = getClass().getResourceAsStream("/img/entity/rogue.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animation = new BufferedImage[5][10];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    animation[i][j] = img.getSubimage(charectorSize * j, charectorSize * i, charectorSize,
                            charectorSize);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void move() {
        if (up || right || left || down) {
            setPalyerAction(WALKING);
            if (left && !right) {
                x -= speed;
            } else if (right && !left) {
                x += speed;
            }

            if (up && !down) {
                y -= speed;
            } else if (down && !up) {
                y += speed;
            }
        } else {
            setPalyerAction(IDLE);
        }

    }

    public void setPalyerAction(int palyerAction) {
        this.palyerAction = palyerAction;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
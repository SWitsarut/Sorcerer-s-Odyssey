package Magic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Player;

public class LightningBuff extends Buff {

    public static double cost = 50;

    Player player;

    int scale = 3;
    private BufferedImage[] ani;

    public LightningBuff(BufferedImage[] ani, Player player) {
        super(7.5);
        this.player = player;
        this.ani = ani;
        start();
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.drawImage(ani[buff.curCount % ani.length],
                (int) (player.getHitbox().x + player.hitboxXcenter - xLvlOffset
                        - scale * ani[buff.curCount % ani.length].getWidth() / 2),
                (int) (player.getHitbox().y + player.hitboxYcenter - yLvlOffset
                        - scale * ani[buff.curCount % ani.length].getHeight() / 2),
                scale * ani[buff.curCount % ani.length].getWidth(),
                scale * ani[buff.curCount % ani.length].getHeight(),
                null);
        // g.drawOval((int) (player.getHitbox().x + player.hitboxXcenter - xLvlOffset),
        // (int) (player.getHitbox().y + player.hitboxYcenter - yLvlOffset), 100, 100);
    }

    @Override
    public void onActive() {
        player.speed = 7;
    }

    @Override
    public void onExpired() {
        player.speed = Player.speedDefault;
    }

}

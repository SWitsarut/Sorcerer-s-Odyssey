package Magic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Player;
import entities.Projectile.Projectile;
import gamestates.Playing;
import helperClass.Coordinate;
import util.LoadSave;

public class Magic {
    private Player player;
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Buff> buffs = new ArrayList<>();

    private BufferedImage lightningAni[];

    public Magic(Playing playing) {
        this.player = playing.getPlayer();
        lightningAni = LoadSave.LinearAnimationLoader("asset/effect/lightning.png", 32);
    }

    public void update() {
        for (int i = 0; i < buffs.size(); i++) {
            Buff buff = buffs.get(i);
            if (buff.isActive()) {
                buffs.get(i).update();
            } else {
                buffs.remove(i);
                i--;
            }
        }

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            if (!projectile.isActive()) {
                projectiles.remove(i);
                i--;
            } else {
                projectile.update();
            }

        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Buff buff : buffs) {
            buff.draw(g, xLvlOffset, yLvlOffset);
        }
        for (Projectile projectile : projectiles) {
            projectile.draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void castFireBall(Coordinate targetCoor) {
        if (player.castSpell(FireBall.cost)) {

            projectiles.add(new FireBall(player.getPlayerCenter(), targetCoor));

        }
    }

    public void castLightningBuff() {
        if (player.castSpell(LightningBuff.cost)) {
            buffs.add(new LightningBuff(lightningAni, player));
        }
    }

    public void castArcaneBullets(Coordinate targetCoor) {
        if (player.castSpell(ArcaneBullet.cost)) {
            Coordinate left = new Coordinate(targetCoor.x - 20, targetCoor.y - 50);
            Coordinate right = new Coordinate(targetCoor.x - 20, targetCoor.y + 50);
            projectiles.add(new ArcaneBullet(player.getPlayerCenter(), left));
            projectiles.add(new ArcaneBullet(player.getPlayerCenter(), targetCoor));
            projectiles.add(new ArcaneBullet(player.getPlayerCenter(), right));

        }
    }

}

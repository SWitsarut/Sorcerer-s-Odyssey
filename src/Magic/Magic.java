package Magic;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.Player;
import entities.Projectile.Projectile;
import gamestates.Playing;
import helperClass.Coordinate;

public class Magic {
    Player player;
    ArrayList<Projectile> projectiles = new ArrayList<>();

    public Magic(Playing playing) {
        this.player = playing.getPlayer();
    }

    public void update() {
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
        for (Projectile projectile : projectiles) {
            projectile.draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void castFireBall(Coordinate targetCoor) {
        if (player.castSpell(FireBall.cost)) {
            projectiles.add(new FireBall(player.getPlayerCenter(), targetCoor));
        }

    }

}

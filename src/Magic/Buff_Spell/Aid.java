package Magic.Buff_Spell;

import entities.Player;
import main.sound.Sound;

public class Aid {

    public static double cost = 30;

    public Aid(Player player) {
        Sound castSound = new Sound("Fantasy/Fantasy_UI (51).wav");
        player.heal(30);
        castSound.play();
    }

}

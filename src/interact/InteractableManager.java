package interact;

import java.util.ArrayList;

import Levels.Level;
import Levels.LevelManager;

import static util.ObjectType.*;

public class InteractableManager {
    private LevelManager lm;

    public InteractableManager(LevelManager lm) {
        this.lm = lm;
    }

    public void handleMapUpdate() {
        int[][] collionMap = lm.getCollision().getData();
        int sum = 0;
        for (int i = 0; i < collionMap.length; i++) {
            for (int j = 0; j < collionMap[i].length; j++) {
                if (collionMap[i][j] == 0) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }

}

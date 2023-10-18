package helperClass;

import util.Constants.Config;

public class UpdateCounter {

    public boolean active;
    public boolean isCycle;
    private int targetCount, curCount;

    public UpdateCounter(double second, boolean cycle) {
        isCycle = cycle;
        active = true;
        curCount = 0;
        targetCount = (int) (second * Config.UPS_SET);
    }

    public void update() {
        curCount++;
        if (curCount > targetCount && active) {
            curCount = 0;
            if (!isCycle) {
                active = false;
            }
        }
    }

}

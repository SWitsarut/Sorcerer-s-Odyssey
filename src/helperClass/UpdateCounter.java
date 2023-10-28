package helperClass;

import util.Constants.Config;

public class UpdateCounter {

    public boolean active;
    public boolean isCycle;
    public int targetCount, curCount;

    public UpdateCounter(double second, boolean cycle) {
        isCycle = cycle;
        curCount = 0;
        targetCount = (int) (second * Config.UPS_SET);
    }

    public void start() {
        active = true;
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

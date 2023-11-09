package helperClass;

import util.Constants.Config;

public class UpdateCounter {

    public boolean active;
    public boolean oldActive;
    public boolean isCycle;
    public int targetCount, curCount;

    public UpdateCounter(double second, boolean cycle) {
        isCycle = cycle;
        curCount = 0;
        targetCount = (int) (second * Config.UPS_SET);
    }

    public void onUpdate() {
    }

    public void onEnd() {
    }

    public void start() {
        active = true;
    }

    public void update() {
        curCount++;
        oldActive = active;
        if (curCount > targetCount && active) {
            onUpdate();
            curCount = 0;
            if (!isCycle) {
                active = false;
            }
            if (oldActive != active) {
                onEnd();
            }
        }

    }

}

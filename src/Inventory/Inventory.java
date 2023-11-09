package Inventory;

import java.util.ArrayList;

public class Inventory {
    public ArrayList<Integer> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void add(int i) {
        if (!inventory.contains(i)) {
            inventory.add(i);
        }
    }

    public boolean check(int[] item) {
        boolean had = true;
        for (int j : item) {
            had = had && inventory.contains(j);
        }
        return had;
    }
}

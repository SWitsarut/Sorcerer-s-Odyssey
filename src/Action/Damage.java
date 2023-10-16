package Action;

public class Damage {
    public static final int FIRE = 0;
    public static final int HOLY = 1;
    public static final int LIGHTING = 2;
    public static final int ARCANE = 3;
    public static final int PHYSICAL = 4;

    private int type;
    private int damage;

    public Damage(int type, int damage) {
        this.type = type;
        this.damage = damage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}

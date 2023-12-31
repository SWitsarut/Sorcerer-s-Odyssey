package Action;

public class Damage {
    public static final int FIRE = 0;
    public static final int HOLY = 1;
    public static final int LIGHTING = 2;
    public static final int ARCANE = 3;
    public static final int PHYSICAL = 4;
    private final int type;
    private double damage;
    public Damage(int type, double damage) {
        this.type = type;
        this.damage = damage;
    }
    public int getType() {
        return type;
    }
    public double getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

}

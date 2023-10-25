package Action;

public class Def {

    public double FireDef;
    public double HolyDef;
    public double LightingDef;
    public double ArcaneDef;
    public double PhysicalDef;

    public Def(double fireDef, double holyDef, double lightingDef, double arcaneDef, double physicalDef) {
        FireDef = fireDef;
        HolyDef = holyDef;
        LightingDef = lightingDef;
        ArcaneDef = arcaneDef;
        PhysicalDef = physicalDef;
    }

    public static final Def CorruptedTreantDef = new Def(-100, -10, 70, 0, 20);
}

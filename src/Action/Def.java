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

    public static final Def CorruptedTreantDef = new Def(-100, -10, 70, 0, 30);
    public static final Def GoblinWolfRider = new Def(60, 50, 0, 20, 20);
    public static final Def Skeleton = new Def(0, -100, 40, 70, 20);
    public static final Def Lich = new Def(30, 40, 50, 20, 65);
}

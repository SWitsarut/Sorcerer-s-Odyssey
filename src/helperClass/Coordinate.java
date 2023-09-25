package helperClass;

public class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x " + x + " | y " + y;
    }
}
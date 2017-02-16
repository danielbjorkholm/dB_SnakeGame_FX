package Model;

public class Location {
    private int X;
    private int Y;

    Location(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    void setX(int x) {
        X = x;
    }

    void setY(int y) {
        Y = y;
    }
}

package Model;

public class TailPiece {
    private int X;
    private int Y;

    TailPiece(int x, int y) {
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

    @Override
    public String toString() {
        return "TailPiece{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}

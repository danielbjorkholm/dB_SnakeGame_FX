package Model;

import SnakeLogic.Board;
import SnakeLogic.GameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Eatable implements GameObject{
    private Color mColor;
    private int x;
    private int y;
    private boolean mDangerous;

    public Eatable(int x, int y, boolean dangerous) {
        this.x = x;
        this.y = y;
        mDangerous = dangerous;
        if (isDangerous()){
            mColor = Color.GREEN;
        } else {
            mColor = Color.RED;
        }
    }

    public Color getColor() {
        return mColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDangerous() {
        return mDangerous;
    }

    @Override
    public void update(KeyCode keypressed) {

    }

    @Override
    public void draw(Board board) {

    }
}

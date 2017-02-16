package Model;

import SnakeLogic.GameObject;
import javafx.scene.paint.Color;

public class Item implements GameObject{
    private Color mColor;
    private int x;
    private int y;
    private boolean mDangerous;

    public Item(int x, int y, boolean dangerous) {
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
    public void update() {

    }
}

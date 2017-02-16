package Model;

import javafx.scene.input.KeyCode;

public class Player{

    private int X;
    private int Y;
    private int mEatCount;

    public Player() {
        X = 0;
        Y = 0;
        mEatCount = 0;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setXY(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public boolean checkLocation(int x, int y){
        if(this.X == x && this.Y == y){
            return true;
        } else {
            return false;
        }
    }

    public void eat(){
        mEatCount++;
    }

    public int getEatCount() {
        return mEatCount;
    }

    // Hvordan giver man en overridet metode forskellige attributer ??
    public void update(KeyCode keyPressed) {
        switch (keyPressed)
        {
            case DOWN:
                this.Y++;
                break;
            case LEFT:
                this.X--;
                break;
            case RIGHT:
                this.X++;
                break;
            case UP:
                this.Y--;
                break;
        }
        //TODO: Test om Player er inden for banen.
    }
}

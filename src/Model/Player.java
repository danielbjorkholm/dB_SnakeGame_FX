package Model;

import javafx.scene.input.KeyCode;

import java.util.LinkedList;

public class Player{

    private int currX;
    private int currY;
    private int mEatCount;
    private boolean mAlive;
    private LinkedList<Location> mLocations = new LinkedList<>();

    public Player() {
        currX = 0;
        currY = 0;
        mEatCount = 0;
        mAlive = true;
    }

    public int getCurrX() {
        return currX;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public int getCurrY() {
        return currY;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    public void setXY(int x, int y){
        this.currX = x;
        this.currY = y;
    }

    public boolean isAlive() {
        return mAlive;
    }

    public LinkedList<Location> getLocations() {
        return mLocations;
    }

    public boolean checkLocation(int x, int y){
        if(this.currX == x && this.currY == y){
            return true;
        } else {
            for(Location l : mLocations){
                if(l.getX() == x && l.getY() == y){
                    return true;
                }
            }
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
    public void update(KeyCode keyPressed, int width, int height) {
        if (mLocations.size() < mEatCount) {
            mLocations.addLast(new Location(currX, currY));
        }
        for (int i = mLocations.size(); i >= 1; i--) {
            if(i == 1){
                mLocations.get(i - 1).setX(currX);
                mLocations.get(i - 1).setY(currY);
            } else {
                mLocations.get(i - 1).setX(mLocations.get(i - 2).getX());
                mLocations.get(i - 1).setY(mLocations.get(i - 2).getY());
            }
        }
        switch (keyPressed)
        {
            case DOWN:
                this.currY++;
                break;
            case LEFT:
                this.currX--;
                break;
            case RIGHT:
                this.currX++;
                break;
            case UP:
                this.currY--;
                break;
        }
        System.out.println(currX + "  " + currY);
        if(checkForTailGrab()||checkForOOB(width, height)){
            System.out.println("Player dead!");
            mAlive = false;
        }
    }

    private boolean checkForOOB(int width, int height) {
        boolean result = false;
        System.out.println("Checking OOB");
        if ((currX < 0 || currX > width || currY < 0 || currY > height)){
            result = true;
        }
        System.out.println(result);
        return result;
    }

    private boolean checkForTailGrab() {
        boolean result = false;
        for (Location l : mLocations){
            if(l.getX() == currX && l.getY() == currY){
                result = true;
            }
        }
        return result;
    }
}

package SnakeLogic;


import Model.Eatable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Board {
    private double mFieldHeight;
    private double mFieldWidth;
    private int mWidth = 30;
    private int mHeight = 20;
    private Canvas mCanvas;

    private ArrayList<Eatable> mEatables = new ArrayList<>();

    private static Board ourInstance = new Board();
    public static Board getInstance() {
        return ourInstance;
    }

    /**
     * Calculate height and width of each field
     */
    public void setBoard(Canvas canvas) {
        mCanvas = canvas;
        mFieldHeight = canvas.getHeight() / mHeight;
        mFieldWidth = canvas.getWidth() / mWidth;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public double getFieldHeight() {
        return mFieldHeight;
    }

    public double getFieldWidth() {
        return mFieldWidth;
    }

    public GraphicsContext getGraphicsContext(){
        return mCanvas.getGraphicsContext2D();
    }

    public void draw(){
        GraphicsContext g = mCanvas.getGraphicsContext2D();

        g.clearRect(0,0,mWidth*mFieldWidth ,mHeight*mFieldHeight);

        // draw all fields
        g.setFill(Color.BLUE);
        for (int i = 0; i < mWidth ; i++) {
            for (int j = 0; j < mHeight ; j++) {
                g.fillRoundRect(i*mFieldWidth, j*mFieldHeight, mFieldWidth,mFieldHeight, 5, 5);
            }
        }
        // draw eatables
        for (Eatable e : mEatables) {
            e.draw(this);
        }
    }

    public boolean tryEatEatable(int x, int y){
        for (Eatable e: mEatables) {
            if (e.getX() == x && e.getY() == y){
                mEatables.remove(e);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Eatable> getEatables() {
        return mEatables;
    }

    public void clearEatables(){
        mEatables.clear();
    }
}

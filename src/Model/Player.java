package Model;

import SnakeLogic.Board;
import SnakeLogic.GameObject;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Player implements GameObject{

    private int currX;
    private int currY;
    private int mEatCount;
    private boolean mAlive;
    private LinkedList<TailPiece> mTailPieces = new LinkedList<>();

    public Player() {
        currX = 0;
        currY = 0;
        mEatCount = 0;
        mAlive = true;
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }

    public boolean isAlive() {
        return mAlive;
    }

    public boolean checkLocation(int x, int y){
        if(this.currX == x && this.currY == y){
            return true;
        } else {
            for(TailPiece l : mTailPieces){
                if(l.getX() == x && l.getY() == y){
                    return true;
                }
            }
            return false;
        }
    }

    public int getEatCount() {
        return mEatCount;
    }

    // Hvordan giver man en overridet metode forskellige attributer ??
    public void update(KeyCode keyPressed) {
        int prevX = currX;
        int prevY = currY;

        switch (keyPressed) {
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

        if(Board.getInstance().tryEatEatable(currX, currY)){
            mEatCount++;
        }

        //Hvis der er færre tailpieces end EatCount..
        if (mTailPieces.size() < mEatCount) {
            //..læg et ekstra piece forrest i halen.
            mTailPieces.addLast(new TailPiece(prevX, prevY));
        }

        //For hver tailpiece ..
        for (int i = mTailPieces.size(); i >= 1; i--) {
            if(i == 1){

                mTailPieces.get(i - 1).setX(prevX);
                mTailPieces.get(i - 1).setY(prevY);
            } else {
                mTailPieces.get(i - 1).setX(mTailPieces.get(i - 2).getX());
                mTailPieces.get(i - 1).setY(mTailPieces.get(i - 2).getY());
            }
        }

        System.out.println("Jake pos: " + currX + "  " + currY);
        System.out.println("Tail pos: " + mTailPieces);

        if(checkForTailGrab() || checkForOOB(Board.getInstance().getWidth(), Board.getInstance().getHeight())) {
            System.out.println("Player dead!");
            mAlive = false;
        }
    }

    @Override
    public void draw(Board board) {
        // draw 'player'
        GraphicsContext g = board.getGraphicsContext();
        g.setFill(Color.WHITE);
        g.fillRoundRect(currX * board.getFieldWidth(), currY * board.getFieldHeight(),
                            board.getFieldWidth(), board.getFieldHeight(), 3, 3);
        for (TailPiece p: mTailPieces) {
            g.fillRoundRect(p.getX() * board.getFieldWidth(), p.getY() * board.getFieldHeight(),
                            board.getFieldWidth(), board.getFieldHeight(), 3, 3);
        }
    }

    private boolean checkForOOB(int width, int height) {
        boolean result = false;
        if ((currX < 0 || currX > width || currY < 0 || currY > height)){
            result = true;
        }
        return result;
    }

    private boolean checkForTailGrab() {
        boolean result = false;
        for (TailPiece l : mTailPieces){
            if(l.getX() == currX && l.getY() == currY){
                result = true;
            }
        }
        return result;
    }
}

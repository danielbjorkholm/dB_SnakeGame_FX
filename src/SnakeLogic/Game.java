package SnakeLogic;

import SnakeGUI.GameViewController;
import Model.Item;
import Model.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static Game ourInstance = new Game();
    private GameViewController mViewController;
    private Random mRandomGenerator = new Random();
    private boolean mGameInstatiated = false;
    private boolean mGameEnded = false;
    private AnimationTimer mTimer;

    private KeyCode mKeyPressed = KeyCode.RIGHT;

    private final float mRefreshRate = 150; //Original 300
    private Player mPlayer;
    private ArrayList<Item> mGameItems = new ArrayList<>();

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        // Start and control game loop
        new AnimationTimer() {
            long lastUpdate;

            public void handle(long now) {
                if (now > lastUpdate + mRefreshRate * 1000000) {
                        lastUpdate = now;
                        update(now);
                }
            }
        }.start();
    }

    //Instantiating methods
    public void setViewController(GameViewController viewController) {
        mViewController = viewController;
    }

    public void setStage(Stage stage) {
        stage.getScene().setOnKeyPressed(event -> keyPressed(event.getCode())
        );
    }

    private void keyPressed(KeyCode keyCode) {
        this.mKeyPressed = keyCode;
    }

    private void instantiateGame() {

        System.out.println("Inst start");
        mGameEnded = false;
        mPlayer = new Player();
        mKeyPressed = KeyCode.RIGHT;
        mGameItems.clear();
        addGameItem();
    }

    private void addGameItem() {
        int x = mPlayer.getCurrX();
        int y = mPlayer.getCurrY();

        while (mPlayer.checkLocation(x, y)) {
            x = mRandomGenerator.nextInt(mViewController.getWidth());
            y = mRandomGenerator.nextInt(mViewController.getHeight());

            mGameItems.add(new Item(x, y, false));
        }
    }

    /**
     * Game loop - executed continously during the game
     *
     * @param now game time in nano seconds
     */
    private void update(long now) {

        if (!mGameEnded) {
            if (!mGameInstatiated){
                instantiateGame();
                mGameInstatiated = true;
            }

            mPlayer.update(mKeyPressed, mViewController.getWidth(), mViewController.getHeight());

            if (mGameItems.isEmpty()) {
                addGameItem();
            }
            if (mPlayer.checkLocation(mGameItems.get(0).getX(), mGameItems.get(0).getY())) {
                mGameItems.clear();
                addGameItem();
                mPlayer.eat();
            }
            mViewController.updateEatCount(mPlayer.getEatCount());
            mViewController.updateCanvas(mPlayer.getCurrX(), mPlayer.getCurrY(),mPlayer.getLocations(), mGameItems);
        }

        if(!mPlayer.isAlive()) {
            mGameEnded = true;
            mViewController.showGameEnded();

        }
    }

    public void restart() {
        instantiateGame();
    }

}

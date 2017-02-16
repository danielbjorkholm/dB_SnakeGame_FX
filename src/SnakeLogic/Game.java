package SnakeLogic;

import SnakeGUI.GameViewController;
import Model.Item;
import Model.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static Game ourInstance = new Game();
    private GameViewController mViewController;
    Random mRandomGenerator = new Random();

    private KeyCode mKeyPressed = KeyCode.BACK_SPACE;

    private final float mRefreshRate = 300;
    private Player mPlayer;
    ArrayList<Item> mGameItems = new ArrayList<Item>();

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {


        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now) {
                if (now > lastUpdate + mRefreshRate * 1000000) {
                    lastUpdate = now;
                    update(now);
                }
            }
        }.start();

        instantiateGame();
    }

    //Instantiating methods
    public void setViewController(GameViewController viewController) {
        mViewController = viewController;
    }
    public void setStage(Stage stage) {
        stage.getScene().setOnKeyPressed(event -> keyPressed(event.getCode())
        );}
    private void keyPressed(KeyCode keyCode)
    {
        this.mKeyPressed = keyCode;
    }
    public void instantiateGame(){
        mPlayer = new Player();
        mKeyPressed = KeyCode.BACK_SPACE;
        mGameItems.clear();
        addGameItem();
    }

    private void addGameItem() {
        int x = mPlayer.getX();
        int y = mPlayer.getY();

        while (mPlayer.checkLocation(x, y)) {
            x = mRandomGenerator.nextInt(mViewController.getWidth());
            y = mRandomGenerator.nextInt(mViewController.getHeight());
        }
        mGameItems.add(new Item(x, y, false));
    }

    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now) {
        mPlayer.update(mKeyPressed);


        mViewController.updateCanvas(mPlayer.getX(), mPlayer.getY(), mGameItems);
    }

}

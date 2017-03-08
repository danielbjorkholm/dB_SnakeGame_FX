package SnakeLogic;

import Model.Eatable;
import Model.Player;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static Game ourInstance = new Game();
    public static Game getInstance() {
        return ourInstance;
    }

    private Board mBoard = Board.getInstance();
    private Random mRandomGenerator = new Random();
    private boolean mGameInstatiated = false;
    private boolean mGameEnded = false;
    private KeyCode mKeyPressed = KeyCode.RIGHT;

    private Player mPlayer;


    private Game() {}

    public void setStage(Stage stage) {
        stage.getScene().setOnKeyPressed(event -> keyPressed(event.getCode())
        );
    }
    private void keyPressed(KeyCode keyCode) {
        mKeyPressed = keyCode;
    }

    /**
     * Game loop - executed continously during the game
     *
     * @param now game time in nano seconds
     */
    public void update(long now) {

        //Hvis ikke er slut..
        if (!mGameEnded) {
            //..og spillet ikke er startet..
            if (!mGameInstatiated){
                //..Instantiér spillet
                instantiateGame();
                mGameInstatiated = true;
            }

            //Så længe der er for få Eatables.
            while(mBoard.getEatables().size() < 1){
                addGameItem();
            }

            //Opdatér spiller objectet
            mPlayer.update(mKeyPressed);

            mBoard.updateBoard();
            mPlayer.draw(mBoard);

        }

        if(!mPlayer.isAlive()) {
            mGameEnded = true;

        }
    }









    private void instantiateGame() {

        //Konstruér spiller object
        mPlayer = new Player();
        //Sæt Keycode
        mKeyPressed = KeyCode.RIGHT;
        //Ryd mEatables
        mBoard.clearEatables();
    }






    private void addGameItem() {
        int x = mPlayer.getCurrX();
        int y = mPlayer.getCurrY();

        while (mPlayer.checkLocation(x, y)) {
            x = mRandomGenerator.nextInt(mBoard.getWidth());
            y = mRandomGenerator.nextInt(mBoard.getHeight());

            //TODO: Check også for overlappende eatables, hvis der skal flere på boardet.
            mBoard.getEatables().add(new Eatable(x, y, false));
        }
    }



    public void restart() {
        instantiateGame();
    }

}

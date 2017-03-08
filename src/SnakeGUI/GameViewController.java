package SnakeGUI;


import Model.Eatable;
import Model.TailPiece;
import SnakeLogic.Board;
import SnakeLogic.Game;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

public class GameViewController implements Initializable {

    @FXML Label btnRelatedLabel;
    @FXML Canvas canvas;
    @FXML Label itemsEaten;



    private final float mRefreshRate = 150; //Original 300

    private Game mGame = Game.getInstance();
    private Board mBoard = Board.getInstance();


    /**
     * Executed when JavaFX is initialized. Used to setup the Snake mGame
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Instantiate Gameinfo object
        mBoard.setBoard(canvas);

        // Start and control mGame loop
        new AnimationTimer() {
            long lastUpdate;

            public void handle(long now) {
                if (now > lastUpdate + mRefreshRate * 1000000) {
                    lastUpdate = now;
                    mGame.update(now);
                }
            }
        }.start();


    }


    public void updateEatCount(int count){
        itemsEaten.setText("Items Eaten: " + count);
    }

    public void showGameEnded(){
        itemsEaten.setText("GAME OVER!");
        btnRelatedLabel.setText("<- Press restart!");
    }


    public void handleRestart(ActionEvent actionEvent) {
        btnRelatedLabel.setText("");
        Game.getInstance().restart();
    }

}

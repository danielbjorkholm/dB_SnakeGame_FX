package SnakeGUI;


import Model.Item;
import SnakeLogic.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class GameViewController implements Initializable {

    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;
    @FXML
    Label itemsEaten;

    private double fieldHeight;
    private double fieldWidth;
    private final int width = 30;
    private final int height = 20;


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void btnStartAction(ActionEvent event) {
        System.out.println("btn clicked");
        labelStatus.setText("test");
    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game.getInstance().setViewController(this);

        calculateFields();
    }
    /**
     * Calculate height and width of each field
     */
    private void calculateFields() {
        this.fieldHeight = canvas.getHeight() / this.height;
        this.fieldWidth = canvas.getWidth() / this.width;
    }

    /**
     * Draw the canvas
     */
    public void updateCanvas(int X, int Y, List<Item> items) {
        GraphicsContext g = canvas.getGraphicsContext2D();

        g.clearRect(0,0,width*fieldWidth ,height*fieldHeight);

        // draw all fields
        g.setFill(Color.BLUE);
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                g.fillRoundRect(i*fieldWidth, j*fieldHeight, fieldWidth,fieldHeight, 5, 5);
            }
        }
        // draw items
        for (Item item : items) {
            g.setFill(item.getColor());
            g.fillRoundRect(item.getX() * fieldWidth, item.getY() * fieldHeight, fieldWidth, fieldHeight, 5,5);
        }

        // draw 'player'
        g.setFill(Color.WHITE);
        g.fillRoundRect(X * fieldWidth, Y * fieldHeight, fieldWidth, fieldHeight, 3, 3);
    }

    public void updateEatCount(int count){
        itemsEaten.setText("Items Eaten: " + count);
    }




}
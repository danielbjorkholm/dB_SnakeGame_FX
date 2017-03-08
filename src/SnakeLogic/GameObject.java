package SnakeLogic;


import javafx.scene.input.KeyCode;

public interface GameObject {

    void update(KeyCode keypressed);

    void draw(Board board);
}

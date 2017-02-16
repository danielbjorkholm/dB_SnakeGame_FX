import SnakeLogic.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Markup/gameView.fxml"));
        primaryStage.setTitle("JakeGame");
        primaryStage.setScene(new Scene(root, 600,400));
        primaryStage.getScene().getStylesheets().add("CSS/stylesheet.css");
        primaryStage.show();
        Game.getInstance().setStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

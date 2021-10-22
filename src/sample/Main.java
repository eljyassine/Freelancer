package sample;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/utilisateurs/Authentification.fxml"));
        primaryStage.setTitle("FREELANCER APP");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        primaryStage.setScene(new Scene(root, width, height -300  ));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

  public static void main(String[] args) {

        launch(args);
    }
}

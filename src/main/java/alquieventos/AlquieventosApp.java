package alquieventos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class AlquieventosApp extends Application implements Initializable {

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/alquieventos/inicio.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/alquieventos/welcomeStyle.CSS");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

}
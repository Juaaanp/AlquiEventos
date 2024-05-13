package alquieventos;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegistrarseController {

    @FXML
    private AnchorPane Bg;
    @FXML
    private VBox franjaSuperior;
    @FXML 
    private Label registrate;
    @FXML
    private Label cedula;
    @FXML
    private TextField cedulaField;
    @FXML
    private Label nombre;
    @FXML
    private TextField nombreField;
    @FXML
    private Label numeroTel;
    @FXML 
    private TextField numeroTelField;
    @FXML
    private Label correo;
    @FXML
    private TextField correoField;
    @FXML
    private Label contraseña;
    @FXML 
    private PasswordField contraseñaField;
    @FXML
    private Button registrarse; 

    @FXML
    public void keyOnAction(@SuppressWarnings("exports") KeyEvent event){
        Object evt = event.getSource();
        if (evt.equals(cedulaField)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }

    }

    @FXML
    public void eventOnAction(@SuppressWarnings("exports") ActionEvent event) throws IOException{
        Object evt = event.getSource();
        if (evt.equals(registrarse)) {
            if (!cedulaField.getText().isEmpty() && !nombreField.getText().isEmpty() && !numeroTelField.getText().isEmpty() && 
                !correoField.getText().isEmpty() && !contraseñaField.getText().isEmpty()) {
                if (numeroTelField.getText().length()== 10) {
                    loadStage("/alquieventos/codigoVerificacion.fxml", event);
                }
                else{
                    System.err.println("el numero de telefono debe tener 10 digitos");
                }
            }
            else{
                System.out.println("datos incompletos");
            }
        }
    }

    @FXML
    public void loadStage (String url, @SuppressWarnings("exports") Event event) throws IOException{
        Window window = ((Node)(event.getSource())).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();
        
    }

}

package alquieventos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class WelcomeController {

    @FXML
    private VBox iniciarSesionBg;
    
    @FXML
    private Label iniciarSesion;
    
    @FXML
    private Label usuario;
    
    @FXML
    private TextField usuarioTF;

    @FXML
    private Label contraseña;

    @FXML
    private PasswordField contraseñaPF;
    
    @FXML
    private Button ingresar;

    @FXML
    private Button registrarse;

    @FXML
    private Label aunNoTienesCuenta;

    @FXML
    private Label alquiEventos;

    @FXML
    private VBox registrarseBg;

    @FXML
    private VBox alquiEventosBg;
    

    @FXML
    public void ingresarOnAction(@SuppressWarnings("exports") ActionEvent event){
        iniciarSesion.setText("Bienvenido");
    }
    
    
   
}

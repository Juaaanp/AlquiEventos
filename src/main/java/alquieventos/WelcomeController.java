package alquieventos;


import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WelcomeController {

    @FXML
    private VBox iniciarSesionBg;
    
    @FXML
    private Label iniciarSesion;
    
    @FXML
    private Label correo;
    
    @FXML
    private TextField correoTF;

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
    
    // este método evita que se escriban espacios en los TF de correo y contraseña. 
    @FXML
    public void keyAction(@SuppressWarnings("exports") KeyEvent event){
        Object evt = event.getSource();
        if (evt.equals(correoTF)) {
            correoTF.setText(correoTF.getText().trim());
            correoTF.positionCaret(correoTF.getText().length());

        }
        else if (evt.equals(contraseñaPF)) {
            contraseñaPF.setText(contraseñaPF.getText().trim());
            contraseñaPF.positionCaret(contraseñaPF.getText().length());
        }
    }
    
   // métodos para dirigir la app según el boton que oprima el usuario.
    @FXML
    public void eventOnAction(@SuppressWarnings("exports") ActionEvent event){
        Object evt = event.getSource();
        Alert alert = new Alert(AlertType.ERROR);
        
        if (evt.equals(ingresar)) {

            if (!correoTF.getText().isEmpty() && !contraseñaPF.getText().isEmpty()) {

                if (permitirAcceso()) {
                    loadStage("/alquieventos/inicio.fxml", event);
                }
                else if (correoTF.getText().equalsIgnoreCase("admin@unieventos.com") && contraseñaPF.getText().equals("admin123")) {
                    loadStage("/alquieventos/admin.fxml", event);
                }
                else{
                    alert.setTitle("Error al iniciar sesión");
                    alert.setContentText("comprueba que los datos que ingresaste estén correctos.");
                    alert.showAndWait();
                }

            }
            else{
                alert.setTitle("Datos incompletos");
                alert.setContentText("Por favor completa todos los campos para ingresar.");
                alert.showAndWait();
            }
        }

        else if (evt.equals(registrarse)) {
            loadStage("/alquieventos/registrarse.fxml", event);
        }
    }

    @FXML
    private void loadStage(String url, Event event){
        try {
            Window window = ((Node)(event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage newStage = (Stage) window;
            newStage.setScene(scene);
            newStage.show();
            
        } catch (Exception e) {
            new Exception("Error al cambiar de escena");
        }
    }    
    
    @FXML
    private boolean permitirAcceso(){
        RegistrarseController registroClientes = new RegistrarseController();
        registroClientes.cargarDatos();
        ArrayList<Cliente> listaClientes = registroClientes.getlistaClientes();
        for (int i = 0; i < listaClientes.size(); i++){
            if (listaClientes.get(i).getEmail().equalsIgnoreCase(correoTF.getText()) && listaClientes.get(i).getContraseña().equals(contraseñaPF.getText())) {
                return true;
            }
        }
        return false;

    }

    
}
    
    
    
   


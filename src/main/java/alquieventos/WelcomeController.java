package alquieventos;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class WelcomeController  {

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
    

    @FXML
    public void keyAction(@SuppressWarnings("exports") KeyEvent event){
        Object evt = event.getSource();
        if (evt.equals(correoTF)) {
            correoTF.setText(correoTF.getText().trim());
            correoTF.positionCaret(correoTF.getText().length());

        }
        else if (evt.equals(contraseñaPF)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }
    }
    
   
    @FXML
    public void eventOnAction(@SuppressWarnings("exports") ActionEvent event){
        Object evt = event.getSource();
        if (evt.equals(ingresar)) {
            if (!correoTF.getText().isEmpty() && !contraseñaPF.getText().isEmpty()) {
                String correo = correoTF.getText();
                String contraseña = contraseñaPF.getText();
                if (correo.equalsIgnoreCase("admin@unieventos.com") && contraseña.equals("admin123")) {
                    loadStage("/alquieventos/admin.fxml", event);
                }

                else{
                    Alert alert1 = new Alert(AlertType.ERROR);
                    alert1.setTitle("No eres el administrador");
                }
            }
            else{
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error al iniciar sesión, comprueba si los datos que ingresaste estén correctos.");
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
            
}
    
    
    
   


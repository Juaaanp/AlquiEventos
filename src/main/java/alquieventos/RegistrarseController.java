package alquieventos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class RegistrarseController implements Initializable, Serializable {

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

    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    @FXML
    public void keyOnAction(@SuppressWarnings("exports") KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(cedulaField)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }

    }

    @FXML
    public void eventOnAction(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        Object evt = event.getSource();
        if (evt.equals(registrarse)) {
            if (!cedulaField.getText().isEmpty() && !nombreField.getText().isEmpty()
                    && !numeroTelField.getText().isEmpty() &&
                    !correoField.getText().isEmpty() && !contraseñaField.getText().isEmpty()) {
                if (numeroTelField.getText().length() == 10) {
                    listaClientes.add(new Cliente(nombreField.getText(), cedulaField.getText(),
                            numeroTelField.getText(), correoField.getText(), contraseñaField.getText()));
                    guardarDatos();
                    loadStage("/alquieventos/codigoVerificacion.fxml", event);
                } else {
                    System.err.println("el número de teléfono debe tener 10 digitos");
                }
            } else {
                System.out.println("datos incompletos");
            }
        }
    }

    @FXML
    public void loadStage(String url, @SuppressWarnings("exports") Event event) throws IOException {
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();

    }

    public void mostrarClientes() {
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.toString());
        }
    }

    public void guardarDatos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("ListaClientes.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(listaClientes);
            mostrarClientes();
            objectOut.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        try {
            FileInputStream fileIn = new FileInputStream("ListaClientes.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            listaClientes = (ArrayList<Cliente>) in.readObject();
            in.close();
            fileIn.close();
            mostrarClientes();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("La clase Cliente no se encontró");
            c.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cargarDatos();

    }

}

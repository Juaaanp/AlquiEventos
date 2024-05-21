package alquieventos;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarLocalidadController implements Initializable {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnSalir;

    @FXML
    private TextField tfCapacidad;

    @FXML
    private TextField tfPrecio;

    private Localidad localidad;
    private ObservableList<Localidad> localidades;

    @FXML
    void guardar(ActionEvent event) {
        double precio = Double.parseDouble(this.tfPrecio.getText());
        int capacidad = Integer.parseInt(this.tfCapacidad.getText());

        Localidad l = new Localidad(precio,capacidad);

        if(!localidades.contains(l)) {
            this.localidad = l;

            Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La localidad ya existe");
            alert.showAndWait();
        }
    }

    @FXML
    void salir(ActionEvent event) {
        this.localidad = null;
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void initAtributos(ObservableList<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Localidad getLocalidad() {
        return localidad;
    }
}

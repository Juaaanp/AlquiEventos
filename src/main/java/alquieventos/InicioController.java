package alquieventos;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InicioController implements Initializable {

    @FXML
    private TableView<Evento> tblEventos;
    
    @FXML
    private TableColumn colCiudad;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colTipo;

    @FXML
    private ComboBox combCiudad;

    @FXML
    private ComboBox combNombre;

    @FXML
    private ComboBox combTipo;

    @FXML
    private Label label;

    private ObservableList<Evento> eventos;
    private ObservableList<Evento> filtroEventos;

    @FXML
    void selectCiudad(ActionEvent event) {
        
    }

    @FXML
    void selectNombre(ActionEvent event) {

    }

    @FXML
    void selectTipo(ActionEvent event) {
        String s = combTipo.getSelectionModel().getSelectedItem().toString();
        label.setText(s);
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<TipoEvento> list = FXCollections.observableArrayList(TipoEvento.CONCIERTO,TipoEvento.TEATRO,
                                                                            TipoEvento.DEPORTE,TipoEvento.FESTIVAL,
                                                                            TipoEvento.OTRO);
        combTipo.setItems(list);

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
        this.colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        eventos = FXCollections.observableArrayList();
        filtroEventos = FXCollections.observableArrayList();
    }

    @FXML
    public void agregarEvento(ActionEvent event) {

    }
}

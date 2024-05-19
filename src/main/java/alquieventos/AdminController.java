package alquieventos;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Util.Serializacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdminController implements Initializable, Serializable{

    @FXML
    private Button agregarEvento;

    @FXML
    private TableColumn colCiudad;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colDireccion;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colTipo;

    @FXML
    private TableView<Evento> tblEventos;

    @FXML
    private TextField tfCiudad;

    @FXML
    private TextField tfDescripcion;

    @FXML
    private TextField tfDireccion;

    @FXML
    private DatePicker tfFecha;

    @FXML
    private TextField tfNombre;

    @FXML
    private ComboBox combTipo;

    private ObservableList<Evento> eventos;

    @FXML
    void selectTipo(ActionEvent event) {
        String s = combTipo.getSelectionModel().getSelectedItem().toString();
        tfNombre.setText(s);
    }

    @FXML
    void agregarEvento(ActionEvent event) {
        String nombre = this.tfNombre.getText();
        String ciudad = this.tfCiudad.getText();
        String descripcion = this.tfDescripcion.getText();
        TipoEvento tipo = (TipoEvento) this.combTipo.getSelectionModel().getSelectedItem();
        LocalDate fecha = this.tfFecha.getValue();
        String direccion = this.tfDireccion.getText();

        Evento evento = new Evento(nombre,ciudad,descripcion,tipo,fecha,direccion);

        if(!this.eventos.contains(evento)) {
            this.eventos.add(evento);
            this.tblEventos.setItems(eventos);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El evento ya existe");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<TipoEvento> list = FXCollections.observableArrayList(TipoEvento.CONCIERTO,TipoEvento.TEATRO,
                                                                            TipoEvento.DEPORTE,TipoEvento.FESTIVAL,
                                                                            TipoEvento.OTRO);
        combTipo.setItems(list);

        eventos = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colCiudad.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    }

    @FXML
    void eliminar(ActionEvent event) {
        Evento e = this.tblEventos.getSelectionModel().getSelectedItem();//Devuelve la persona que seleccionemos

        if(e == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un evento");
            alert.showAndWait();
        }else {
            this.eventos.remove(e);
            this.tblEventos.refresh();
        }
    }

    @FXML
    void modificar(ActionEvent event) {
        Evento e = this.tblEventos.getSelectionModel().getSelectedItem();//Devuelve la persona que seleccionemos

        if(e == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un evento");
            alert.showAndWait();
        }else {
            String nombre = this.tfNombre.getText();
            String ciudad = this.tfCiudad.getText();
            String descripcion = this.tfDescripcion.getText();
            TipoEvento tipo = (TipoEvento) this.combTipo.getSelectionModel().getSelectedItem();
            LocalDate fecha = this.tfFecha.getValue();
            String direccion = this.tfDireccion.getText();
    
            Evento aux = new Evento(nombre,ciudad,descripcion,tipo,fecha,direccion);
    
            if(!this.eventos.contains(aux)) {
                e.setNombre(nombre);
                e.setCity(ciudad);
                e.setDescripcion(descripcion);
                e.setTipoEvento(tipo);
                e.setFecha(fecha);
                e.setDireccion(direccion);
                this.tblEventos.refresh();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El evento ya existe");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void seleccionar(MouseEvent event) {
        Evento e = this.tblEventos.getSelectionModel().getSelectedItem();//Devuelve la persona que seleccionemos

        if(e != null){
            this.tfNombre.setText(e.getNombre());
            this.tfCiudad.setText(e.getCity());
            this.tfDescripcion.setText(e.getDescripcion());
            this.combTipo.setValue(e.getTipoEvento());
            this.tfFecha.setValue(e.getFecha());
            this.tfDireccion.setText(e.getDireccion());
        }
    }

    public void guardar(){
        Serializacion.serializarObjeto("ListaEventos", this);
    }
    
}


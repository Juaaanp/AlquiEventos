package alquieventos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class AdminController implements Initializable, Serializable {

    @FXML
    private Button agregarEvento;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colCiudad;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colDescripcion;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colDireccion;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colFecha;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colNombre;

    @SuppressWarnings("rawtypes")
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

    @SuppressWarnings("rawtypes")
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

        Evento evento = new Evento(nombre, ciudad, descripcion, tipo, fecha, direccion, );

        if (!this.eventos.contains(evento)) {
            this.eventos.add(evento);
            guardarEventos();
            this.tblEventos.setItems(eventos);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El evento ya existe");
            alert.showAndWait();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<TipoEvento> list = FXCollections.observableArrayList(TipoEvento.CONCIERTO, TipoEvento.TEATRO,
                TipoEvento.DEPORTE, TipoEvento.FESTIVAL,
                TipoEvento.OTRO);
        combTipo.setItems(list);

        eventos = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colCiudad.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        cargarEventos();

    }

    @FXML
    void eliminar(ActionEvent event) {
        Evento e = this.tblEventos.getSelectionModel().getSelectedItem();// Devuelve la persona que seleccionemos

        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un evento");
            alert.showAndWait();
        } else {
            this.eventos.remove(e);
            this.tblEventos.refresh();
        }
    }

    @FXML
    void modificar(ActionEvent event) {
        Evento e = this.tblEventos.getSelectionModel().getSelectedItem();// Devuelve la persona que seleccionemos

        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un evento");
            alert.showAndWait();
        } else {
            String nombre = this.tfNombre.getText();
            String ciudad = this.tfCiudad.getText();
            String descripcion = this.tfDescripcion.getText();
            TipoEvento tipo = (TipoEvento) this.combTipo.getSelectionModel().getSelectedItem();
            LocalDate fecha = this.tfFecha.getValue();
            String direccion = this.tfDireccion.getText();

            Evento aux = new Evento(nombre, ciudad, descripcion, tipo, fecha, direccion);

            if (!this.eventos.contains(aux)) {
                e.setNombre(nombre);
                e.setCity(ciudad);
                e.setDescripcion(descripcion);
                e.setTipoEvento(tipo);
                e.setFecha(fecha);
                e.setDireccion(direccion);
                this.tblEventos.refresh();
                guardarEventos();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El evento ya existe");
                alert.showAndWait();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @FXML
    void seleccionar(MouseEvent event) {
        Evento e = this.tblEventos.getSelectionModel().getSelectedItem();// Devuelve la persona que seleccionemos

        if (e != null) {
            this.tfNombre.setText(e.getNombre());
            this.tfCiudad.setText(e.getCity());
            this.tfDescripcion.setText(e.getDescripcion());
            this.combTipo.setValue(e.getTipoEvento());
            this.tfFecha.setValue(e.getFecha());
            this.tfDireccion.setText(e.getDireccion());
        }
    }

    public void guardarEventos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("EstadoListaEventos.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new ArrayList<Evento>(eventos)); // Convertir a ArrayList antes de serializar
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void cargarEventos() {
        try {
            FileInputStream fileIn = new FileInputStream("EstadoListaEventos.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            eventos = FXCollections.observableArrayList((ArrayList<Evento>) in.readObject()); // Convertir de nuevo a
                                                                                              // ObservableList después
                                                                                              // de deserializar
            in.close();
            fileIn.close();

            // Actualiza la tabla con los nuevos datos
            tblEventos.setItems(eventos);
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("La clase Evento no se encontró");
            c.printStackTrace();
            return;
        }
    }

}

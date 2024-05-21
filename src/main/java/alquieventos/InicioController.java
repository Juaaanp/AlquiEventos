package alquieventos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public class InicioController implements Initializable {

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colCiudad;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colComprar;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colFecha;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colNombre;

    @SuppressWarnings("rawtypes")
    @FXML
    private TableColumn colTipo;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox combFiltrarTipo;

    @FXML
    private Button comprar;

    @FXML
    private TableView<Evento> tblEventos;

    @FXML
    private TextField tfFiltrarCiudad;

    @FXML
    private TextField tfFiltrarNombre;

    private ObservableList<Evento> eventos;
    private ObservableList<Evento> filtroEventos;

    private Evento eventoSeleccionado;

    @FXML
    void filtrarCiudad(KeyEvent event) {
        String filtroCiudad = this.tfFiltrarCiudad.getText();
        if(filtroCiudad.isEmpty()){
            this.tblEventos.setItems(eventos);
        }else {
            this.filtroEventos.clear();
            
            for(Evento e: this.eventos){
                if(e.getCity().toLowerCase().startsWith(filtroCiudad.toLowerCase())){
                    this.filtroEventos.add(e);
                }
            }
            this.tblEventos.setItems(filtroEventos);
        }
    }

    @FXML
    void filtrarNombre(KeyEvent event) {
        String filtroNombre = this.tfFiltrarNombre.getText();
        if(filtroNombre.isEmpty()){
            this.tblEventos.setItems(eventos);
        }else {
            this.filtroEventos.clear();
            
            for(Evento e: this.eventos){
                if(e.getNombre().toLowerCase().startsWith(filtroNombre.toLowerCase())){
                    this.filtroEventos.add(e);
                }
            }
            this.tblEventos.setItems(filtroEventos);
        }
    }

    @FXML
    void filtrarTipo(ContextMenuEvent event) {
        TipoEvento filtroTipo = (TipoEvento) this.combFiltrarTipo.getSelectionModel().getSelectedItem();
        if(filtroTipo == TipoEvento.TODOS){
            this.tblEventos.setItems(eventos);
        }else {
            this.filtroEventos.clear();
            for(Evento e: this.eventos){
                if(e.getTipoEvento() == (filtroTipo)){
                    this.filtroEventos.add(e);
                }
            }
            this.tblEventos.setItems(filtroEventos);
        }
    }

    @FXML
    void comprarBoleto(ActionEvent event) {
        if (eventoSeleccionado != null) {
            try {
                abrirVentanaDetalles(eventoSeleccionado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void si(){
        tblEventos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Evento>() {
            @Override
            public void changed(ObservableValue<? extends Evento> observable, Evento oldValue, Evento newValue) {
                if (newValue != null) {
                    System.out.println("Seleccionado el evento: " + newValue.getNombre());
                    eventoSeleccionado = newValue;
                    // Aquí puedes realizar alguna acción con la fila seleccionada
                }
            }
        });
    }

    private void abrirVentanaDetalles(Evento evento) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pago.fxml"));
        Parent root = loader.load();

        PagoController pagoController = loader.getController();
        pagoController.setEvento(evento);

        Stage stage = new Stage();
        stage.setTitle("Detalles del Evento");
        stage.setScene(new Scene(root));
        stage.show();
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

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<TipoEvento> list = FXCollections.observableArrayList(TipoEvento.values());
        combFiltrarTipo.setItems(list);

        this.tblEventos.setItems(eventos);

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
        this.colCiudad.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        eventos = FXCollections.observableArrayList();
        filtroEventos = FXCollections.observableArrayList();

        cargarEventos();

        si();
    }

    @SuppressWarnings("unchecked")
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

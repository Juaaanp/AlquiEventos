package alquieventos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

    @FXML
    void comprarBoleto(ActionEvent event) {

    }

    @FXML
    void filtrarCiudad(KeyEvent event) {
        String filtroCiudad = this.tfFiltrarCiudad.getText();
        if(filtroCiudad.isEmpty()){
            this.tblEventos.setItems(eventos);
        }else {
            this.filtroEventos.clear();
            
            for(Evento e: this.eventos){
                if(e.getCity().toLowerCase().contains(filtroCiudad.toLowerCase())){
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
                if(e.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())){
                    this.filtroEventos.add(e);
                }
            }
            this.tblEventos.setItems(filtroEventos);
        }
    }

    @FXML
    void filtrarTipo(KeyEvent event) {
        TipoEvento filtroTipo = (TipoEvento) this.combFiltrarTipo.getSelectionModel().getSelectedItem();
        if(filtroTipo == null){
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
    void seleccionarEvento(MouseEvent event) {
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<TipoEvento> list = FXCollections.observableArrayList(TipoEvento.CONCIERTO,TipoEvento.TEATRO,
                                                                        TipoEvento.DEPORTE,TipoEvento.FESTIVAL,
                                                                        TipoEvento.OTRO);
        combFiltrarTipo.setItems(list);

        this.tblEventos.setItems(eventos);

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
        this.colCiudad.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        eventos = FXCollections.observableArrayList();
        filtroEventos = FXCollections.observableArrayList();

        cargarEventos();
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

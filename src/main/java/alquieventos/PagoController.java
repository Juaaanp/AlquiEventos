package alquieventos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PagoController extends SerializacionTemplate implements Serializable, Initializable{

    @FXML
    private Label lEvento;

    @FXML
    private Label lCiudad;

    @FXML
    private Label lDescripcion;

    @FXML
    private Label lTipoEvento;

    @FXML
    private Label lFecha;

    private Evento evento = getEvento();
    private List<Persona> personas;

    public Evento getEvento() {
        InicioController inicio = new InicioController();
        return inicio.getEventoSeleccionado();
    }

    // Método para recibir y mostrar los datos del evento seleccionado
    public void setEvento(Evento evento) {
        lEvento.setText(evento.getNombre());
        lTipoEvento.setText(evento.getTipoEvento().toString());
        lCiudad.setText(evento.getCity());
        lFecha.setText(evento.getFecha().toString());
        lDescripcion.setText(evento.getDescripcion());
    }

    @FXML
    public void realizarCompra() {
        System.out.println("Personas: "+ personas.toString());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //System.out.println(personas.toString());    
    }

    @Override
    public void deserializar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EstadoListaEventos.txt"))) {
            personas = (List<Persona>) ois.readObject();

        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada durante la deserialización: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S durante la deserialización: " + e.getMessage());
        }
    }


    @Override
    public void serializar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serializar'");
    }
}

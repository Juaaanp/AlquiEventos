package alquieventos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PagoController {

    @FXML
    private Label lEvento;

    // Método para recibir y mostrar los datos del evento seleccionado
    public void setEvento(Evento evento) {
        lEvento.setText(evento.getNombre());
    }
}

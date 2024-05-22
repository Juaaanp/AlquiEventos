package alquieventos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private static Admin instancia;
    private List<Evento> eventos = new ArrayList<>();
    private String email;
    private String contraseña;

    private Admin() {
        this.email = "admin@unieventos.com";
        this.contraseña = "admin123";
    }

    public static Admin getInstancia() {
        if (instancia == null) {
            instancia = new Admin();
        }
        return instancia;
    }

    public Cupon crearCupon(double porcentaje) {
        return new Cupon(porcentaje);
    }

    public void obtenerEstadisticas() {

    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

}

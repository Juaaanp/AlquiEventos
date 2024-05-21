package alquieventos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Admin extends Persona {
    private List<Evento> eventos = new ArrayList<>();

    //Constructor heredado.
    public Admin(String nombre, String cedula, String numTelefono, String email, String contraseña) {
        super(nombre, cedula, numTelefono, email, contraseña);
    }

    @Override
    public void loguear(){
        
    }

    public void gestionarEventos(){

    }

    public Cupon crearCupon(LocalDate fechaEspecial, double porcentaje){
        return new Cupon(porcentaje);
    }

    public void obtenerEstadisticas(){

    }

    //public Collection<Evento> listarEventosMayorRecaudacion(){
        
      //  return eventos.stream()
    //}
    
}

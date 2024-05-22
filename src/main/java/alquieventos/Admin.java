package alquieventos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Localidad, Double> porcentajeEntradasVendidas(Evento evento) {
        Map<Localidad, Double> porcentajePorLocalidad = new HashMap<>();
        ArrayList<Localidad> localidades = evento.getLocalidades();

        for (Localidad localidad : localidades) {
            int capacidad = localidad.getCapacidad();
            int entradasVendidas = localidad.getCupos().size();
            double porcentaje = (entradasVendidas / (double) capacidad) * 100;
            porcentajePorLocalidad.put(localidad, porcentaje);
        }

        return porcentajePorLocalidad;
    }

    public double totalGanadoPorVentas() {
        double total = 0.0;
        ArrayList<Localidad> localidades = new ArrayList<>();
        for (Evento evento : eventos) {
            localidades.addAll(evento.getLocalidades());
        }

        for(int i = 0; i < localidades.size(); i++){
            total+= localidades.get(i).calcularRecaudacionLocalidad();
        }
        return total;
        
    }

    public ArrayList<Evento> listarEventosMayorRecaudacion(){
        return (ArrayList<Evento>) eventos.stream().sorted(Comparator.comparingDouble(Evento::calcularRecaudacion).reversed()).collect(Collectors.toList());
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

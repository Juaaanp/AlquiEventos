package alquieventos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Evento implements Serializable{
    private String nombre;
    private String city;
    private String descripcion;
    private TipoEvento tipoEvento;
    private String imagen;
    private LocalDate fecha;
    private String direccion;
    private ArrayList<Localidad> localidades;

    //Constructor vacío.
    public Evento(){}
    
    // Constructor con argumentos.
    public Evento(String nombre, String city, String descripcion, TipoEvento tipo, LocalDate fecha, String direccion, ArrayList<Localidad> localidades){
        this.nombre = nombre;
        this.city = city;
        this.descripcion = descripcion;
        this.tipoEvento = tipo;
        this.fecha = fecha;
        this.direccion = direccion;
        this.localidades = localidades;
    }

    //Métodos gets y sets.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override//Se debe usar este metodo porque .contains compara todos los campos de la clase
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nombre, evento.nombre) &&
                Objects.equals(city, evento.city) &&
                Objects.equals(descripcion, evento.descripcion) &&
                tipoEvento == evento.tipoEvento &&
                Objects.equals(fecha, evento.fecha) &&
                Objects.equals(direccion, evento.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, city, descripcion, tipoEvento, fecha, direccion);
    }
    public double calcularRecaudacion(){
        double recaudacion = 0.0;
        for (Localidad localidad : localidades) {
            recaudacion += localidad.calcularRecaudacionLocalidad();
        }
        return recaudacion;
    }

    public ArrayList<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(ArrayList<Localidad> localidades) {
        this.localidades = localidades;
    }

    //}
}


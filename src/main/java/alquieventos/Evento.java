package alquieventos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Evento implements Serializable{
    private String nombre;
    private String city;
    private String descripcion;
    private TipoEvento tipoEvento;
    private String imagen;
    private LocalDate fecha;
    private String direccion;
    private Localidad localidad;
    private int capacidad;
    private Ciudad ciudad;

    // Constructor vacío
    public Evento(String nombre, String city, String descripcion, TipoEvento tipo, LocalDate fecha, String direccion){
        this.nombre = nombre;
        this.city = city;
        this.descripcion = descripcion;
        this.tipoEvento = tipo;
        this.fecha = fecha;
        this.direccion = direccion;
    }

    //Constructor con argumentos.
    /*public Evento(String nombre, String descripcion, TipoEvento tipoEvento, String imagen, LocalDate fecha,
            String direccion, Localidad localidad, int capacidad, Ciudad ciudad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoEvento = tipoEvento;
        this.imagen = imagen;
        this.fecha = fecha;
        this.direccion = direccion;
        this.localidad = localidad;
        this.capacidad = capacidad;
        this.ciudad = ciudad;
    }*/

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

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
    //public double calcularRecaudacion(){

    //}
}


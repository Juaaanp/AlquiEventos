package alquieventos;

import java.time.LocalDate;

public class Evento {
    private String nombre;
    private String descripcion;
    private TipoEvento tipoEvento;
    private String imagen;
    private LocalDate fecha;
    private String direccion;
    private Localidad localidad;
    private int capacidad;
    private Ciudad ciudad;
    private String city;

    // Constructor vacío
    public Evento(String nombre, String ciudad, String descripcion, TipoEvento tipo, LocalDate fecha, String direccion){
        this.nombre = nombre;
        this.city = ciudad;
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

    //public double calcularRecaudacion(){

    //}
}


package alquieventos;

public class Localidad {
    private double precio;
    private int capacidad;
    
    //Constructor vacío.
    public Localidad() {
    }

    //Constructor con argumentos.
    public Localidad(double precio, int capacidad) {
        this.precio = precio;
        this.capacidad = capacidad;
    }

    //Métodos gets y sets.
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    private void medirCapacidad(Compra compra){

    }
    
    
}

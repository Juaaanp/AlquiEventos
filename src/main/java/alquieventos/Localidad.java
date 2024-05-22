package alquieventos;

import java.io.Serializable;
import java.util.ArrayList;

public class Localidad implements Serializable {
    private double precio;
    private int capacidad;
    private ArrayList<Integer> cupos = new ArrayList<Integer>() ;
    
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
    
    public ArrayList<Integer> getCupos(){
        return cupos;
    }
    
    public boolean hayCupos(){
         if (cupos.size() == capacidad) {
            return false;
         }
         return true;
    }

    public void cupoComprado(Compra compra){
        compra.getCliente().realizarCompra(compra);
        cupos.add(1);
    }

    public int contarCupos(){
        int cuposcomprados = 0; 
        for (int i = 0; i < cupos.size(); i++) {
            cuposcomprados++;
        }
        return cuposcomprados;
    }

    public double calcularRecaudacionLocalidad(){
        return contarCupos()*getPrecio();
    }

    @Override
    public String toString() {
        return "Precio: " + precio + "\nCapacidad: " + capacidad + "\n";
    }
}
    

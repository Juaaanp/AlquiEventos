package alquieventos;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Cliente extends Persona  {
    private Collection <Compra> compras;
    private List<Evento> eventos = new ArrayList<>();
    private static final long serialVersionUID = -6914307937088448381L;
    
    //Constructor con argumentos heredados
    public Cliente(String nombre, String cedula, String numTelefono, String email, String contraseña) {
        super(nombre, cedula, numTelefono, email, contraseña); 
    }
    
    //Constructor vacío
    public Cliente(){}

    // Realizar compra si se cumplen las siguientes validaciones: 
    //la fecha de la factura es antes a la fecha del dia del evento.

    public void realizarCompra(Compra compra){
        if (validarFechaCompra(compra) && compra.getLocalidad().hayCupos()) {
            compras.add(compra);
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Error al realizar compra");
            alert.showAndWait();
        }
    }
    
    private boolean validarFechaCompra(Compra compra){
        if (compra.getFactura().getFecha().isBefore(compra.getEvento().getFecha())) {
            return true;
        }
        return false;
    }

    // Cancela una compra con tres condiciones, la primera es que valida que la lista de compras no esté vacía,  
    // luego procede a validar si la fecha en la que el cliente quiere cancelar es antes del evento,
    // si cumple estas dos condiciones el método procede a recorrer la lista de compras y remover la compra que coincida
    // con las caracteristicas de la compra que el cliente desea eliminar. Si la lista está vacía imprime un mensaje por pantalla.


    public void cancelarCompra(Compra compra) {
        if (!compras.isEmpty()) {
            if (compra.getFactura().getFecha().isBefore(compra.getEvento().getFecha())) {
                Iterator<Compra> iterator = compras.iterator();
                while (iterator.hasNext()) {
                    Compra c = iterator.next();
                    if (c.equals(compra)) {
                        iterator.remove();
                        System.out.println("La compra ha sido cancelada exitosamente.");
                        return; // Exit the method once the compra is found and removed
                    }
                }
                System.out.println("La compra no está registrada en la lista de compras, no se ha podido cancelar su compra.");
            } else {
                System.out.println("No se puede cancelar la compra porque la fecha de la factura no es anterior a la fecha del evento.");
            }
        } else {
            System.out.println("La lista de compras está vacía, no se ha podido cancelar su compra.");
        }
    }
    
    public Compra redimirCupones(Cupon cupon, Compra compra){
       double sumaCupones = compra.getCupon().getDescuento() + cupon.getDescuento();
       double descuentoSubtotal = compra.getFactura().getSubTotal()-(compra.getFactura().getSubTotal()*sumaCupones);
       compra.getFactura().setSubTotal(descuentoSubtotal);
       return compra;
    }

    public void listarCompras(){
        
    }

    //Métodos get y set
    public Collection<Compra> getCompras() {
        return compras;
    }

    public void setCompras(Collection<Compra> compras) {
        this.compras = compras;
    }

    @Override
    public String toString() {
        return "Cliente [Nombre =" + getNombre() + ", Cedula =" + getCedula() + ", NumTelefono ="
                + getNumTelefono() + ", Email=" + getEmail() + ", Contraseña =" + getContraseña() + "]";
    }

    
}

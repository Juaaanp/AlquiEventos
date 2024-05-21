package alquieventos;

import java.util.ArrayList;

public class Compra {
    private Cliente cliente;
    private Evento evento;
    private Localidad localidad;
    private Cupon cupon;
    private Factura factura;
    private ArrayList <Compra> Listacompras; 

    //constructor vacío.
    public Compra(){

    }

    //constructor con argumentos.
    public Compra(Cliente cliente, Evento evento, Localidad localidad, Cupon cupon, Factura factura){
        this.cliente = cliente;
        this.evento = evento;
        this.localidad = localidad;
        this.cupon = cupon;
        this.factura = factura;
    }
    // Creación del builder.
    public static class CompraBuilder {
        
        private Cliente cliente;
        private Evento evento;
        private Localidad localidad;
        private Cupon cupon;
        private Factura factura;

        public CompraBuilder cliente (Cliente cliente){
            this.cliente = cliente;
            return this;
        }

        public CompraBuilder evento (Evento evento){
            this.evento = evento;
            return this;
        }

        public CompraBuilder localidad (Localidad localidad){
            this.localidad = localidad;
            return this;
        }

        public CompraBuilder cupon (Cupon cupon){
            this.cupon = cupon;
            return this;
        }

        public CompraBuilder factura (Factura factura){
            this.factura = factura;
            return this;
        }

        public Compra build(){
            return new Compra(cliente, evento, localidad, cupon, factura);
        }
    }

    //Métodos gets y sets.
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}

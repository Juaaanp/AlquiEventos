package alquieventos;

public class Ciudad {
    
    private String nombre;
    private Evento evento;

    public Ciudad() {
    }

    public Ciudad(String nombre,Evento evento) {
        this.nombre = nombre;
        this.evento = evento;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Patron builder
    public static class CiudadBuilder{
        private String nombre;
        private Evento evento;

        public CiudadBuilder nombre (String nombre){
            this.nombre = nombre;
            return this;
        } 

        public CiudadBuilder evento (Evento evento){
            this.evento = evento;
            return this;
        }

        public Ciudad build(){
            return new Ciudad(nombre, evento);
        }
    }


    
    
}

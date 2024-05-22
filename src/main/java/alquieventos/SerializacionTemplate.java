package alquieventos;

public abstract class SerializacionTemplate {
    
    public final void guardarYCargarDatos() {
        serializar();
        deserializar();
    }

    public abstract void serializar();

    public abstract void deserializar();
}

package alquieventos;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    private String nombre;
    private String cedula;
    private String numTelefono;
    private String email;
    private String contraseña;

   //Constructor con argumentos
   public Persona(String nombre, String cedula, String numTelefono, String email, String contraseña) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.numTelefono = numTelefono;
        this.email = email;
        this.contraseña = contraseña;
    }
   // Constructor vacío
    public Persona(){}
   
    //Métodos gets y sets.
   public String getNombre() {
     return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getCedula() {
      return cedula;
   }

    public void setCedula(String cedula) {
      this.cedula = cedula;
   }

   public String getNumTelefono() {
      return numTelefono;
   }

   public void setNumTelefono(String numTelefono) {
      this.numTelefono = numTelefono;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getContraseña() {
      return contraseña;
   }

   public void setContraseña(String contraseña) {
      this.contraseña = contraseña;
   }
}

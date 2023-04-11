package PROYECTO;

/**
 *
 * @author Lenovo
 */
public class Cliente extends Persona {

    private String correo;
    private String direccion;

    public Cliente(String nombre, String apellido, String correo, String direccion) {
        super(nombre, apellido);
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}

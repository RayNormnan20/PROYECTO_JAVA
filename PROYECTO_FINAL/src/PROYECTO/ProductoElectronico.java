package PROYECTO;

/**
 *
 * @author Lenovo
 */
public class ProductoElectronico {

    private String modelo;
    private int potencia;

    public ProductoElectronico(String modelo, int potencia) {
        this.modelo = modelo;
        this.potencia = potencia;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}

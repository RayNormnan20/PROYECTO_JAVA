package PROYECTO;

public class ProductoBase {

    private String nombre;
    private int idElectronicos;
    private int idAlimenti;
    private String descrip;
    private float precio;
    private int stock;

    public ProductoBase(String nombre, int idElectronicos, int idAlimenti, String descrip, float precio, int stock) {

        this.nombre = nombre;
        this.idElectronicos = idElectronicos;
        this.idAlimenti = idAlimenti;
        this.descrip = descrip;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdElectronicos() {
        return idElectronicos;
    }

    public void setIdElectronicos(int idElectronicos) {
        this.idElectronicos = idElectronicos;
    }

    public int getIdAlimenti() {
        return idAlimenti;
    }

    public void setIdAlimenti(int idAlimenti) {
        this.idAlimenti = idAlimenti;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

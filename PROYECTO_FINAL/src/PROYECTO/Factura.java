package PROYECTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Factura {

    private int idFactura;
    private String fecha;
    private int idCliente;
    private int idProducto;
    private int cantidad;
    private float precioUnitario;
    private float subtotal;
    private float igv;
    private float total;

    public Factura(int idFactura, String fecha, int idCliente, int idProducto, int cantidad, float precioUnitario, float subtotal, float igv, float total) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getIgv() {
        return igv;
    }

    public void setIgv(float igv) {
        this.igv = igv;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public static void listaFacturaVentas(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            rs = stmt.executeQuery("SELECT * FROM facturas");
            System.out.println("");
            System.out.println("==================FACTURA DE LA VENTA REALIZADA===================");
            System.out.println("ID\tFecha\t\tidCli\tidPro\tcantidad \tprecioUni\tsubtotal \tigv\ttotal");
            System.out.println("");

            // Iterar sobre los resultados de la consulta
            while (rs.next()) {
                int idFactura = rs.getInt("idFactura");
                String fecha = rs.getString("fecha");
                int idCliente = rs.getInt("idCliente");
                int idProducto = rs.getInt("idProducto");
                int cantidad = rs.getInt("cantidad");
                float precioUnitario = rs.getFloat("precioUnitario");
                float subtotal = rs.getFloat("subtotal");
                float igv = rs.getFloat("igv");
                float total = rs.getFloat("total");

                System.out.println(idFactura + "\t" + fecha + "\t" + idCliente
                        + "\t" + idProducto + "\t" + cantidad + "\t\t" + precioUnitario + "\t\t" + subtotal + "\t\t" + igv + "\t" + total);
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error al obtener el listado de productos alimenticios: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }
    
}

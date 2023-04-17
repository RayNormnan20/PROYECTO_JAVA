package PROYECTO;

import PROYECTO.ProductoBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import PROYECTO.ProductoBase;

/**
 *
 * @author Lenovo
 */
public class DetalleFactura {

    private int idFactura;
    private int idProducto;
    private int cantidad;
    private float precioUnitario;
    private float subtotal;

    public DetalleFactura(int idFactura, int idProducto, float precioUnitario, int cantidad, float subtotal) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.subtotal = precioUnitario * cantidad;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
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

    public class jose {

        public static void hacerFacturaDetalle(ResultSet rs, Statement stmt, Scanner entrada) {
           
            System.out.print("Ingrese el ID del cliente: ");
            int idCliente = entrada.nextInt();
            entrada.nextLine(); // Limpiar el buffer del scanner
            System.out.print("Ingrese la fecha de la factura (formato YYYY-MM-DD): ");
            String fecha = entrada.nextLine();

            // Crear una instancia de la clase Factura y agregar detalles
            Factura factura = new Factura(idCliente, 0, 0, 0, fecha);
            System.out.print("Ingrese el número de detalles de factura: ");
            int n = entrada.nextInt();
            entrada.nextLine(); 
            for (int i = 0; i < n; i++) {
                System.out.print("Ingrese el ID del producto: ");
                int idProducto = entrada.nextInt();
                entrada.nextLine(); 
                System.out.print("Ingrese la cantidad: ");
                int cantidad = entrada.nextInt();
                entrada.nextLine(); 
                float precio = ProductoBase.getPrecio(idProducto);
                float subtotal = precio * cantidad;
                DetalleFactura detalle = new DetalleFactura(factura.getId(), idProducto, precio, cantidad, subtotal);
                factura.addDetalleFactura(detalle);
            }

            // Calcular el total y el igv de la factura por los detalles 
            float total = 0;
            for (DetalleFactura detalle : factura.getDetalles()) {
                total += detalle.getSubtotal();
            }
            float igv = total * 0.18f;
            factura.setSubtotal(total);
            factura.setTotal(total + igv);
            factura.setIgv(igv);

            // Guardar la factura y sus detalles en la base de datos
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto?", "root", "12345678")) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO facturas (idCliente, subtotal, total, igv, fecha) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, factura.getIdCliente());
                ps.setFloat(2, factura.getSubtotal());
                ps.setFloat(3, factura.getTotal());
                ps.setFloat(4, factura.getIgv());
                ps.setString(5, factura.getFecha());
                ps.executeUpdate();

                // Obtener el id generado para la factura
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idFactura = rs.getInt(1);
                    factura.setId(idFactura);
                    // Guardar los detalles de la factura
                    String sql = "INSERT INTO detalle_factura (idFactura, idProducto, cantidad, precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(sql);
                    for (DetalleFactura detalle : factura.getDetalles()) {
                        ps.setInt(1, detalle.getIdFactura());
                        ps.setInt(2, detalle.getIdProducto());
                        ps.setInt(3, detalle.getCantidad());
                        ps.setFloat(4, detalle.getPrecioUnitario());
                        ps.setFloat(5, detalle.getSubtotal());
                        ps.executeUpdate();
                    }

                    System.out.println("Factura creada con éxito:");
                    System.out.println(factura.toString());
                } else {
                    System.out.println("No se pudo obtener el id generado para la factura.");
                }
            } catch (SQLException ex) {
                System.out.println("Error al guardar la factura en la base de datos: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendedorError" + ex.getErrorCode());
            }
        }

    }

}

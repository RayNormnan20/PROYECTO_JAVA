package PROYECTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class Factura {

    private int id;
    private int idCliente;
    private float total;
    private float igv;
    private String fecha;
    private List<DetalleFactura> detalles;

    public Factura(int idCliente, float total, float igv, String fecha) {
        this.idCliente = idCliente;
        this.total = total;
        this.igv = igv;
        this.fecha = fecha;
        this.detalles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getIgv() {
        return igv;
    }

    public void setIgv(float igv) {
        this.igv = igv;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public static void listaFacturaVentas(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            rs = stmt.executeQuery("SELECT * FROM facturas");
            System.out.println("");
            System.out.println("==================FACTURA DE LA VENTA REALIZADA===================");
            System.out.println("ID\tFecha\t\tidCli\tidPro\tcantidad \tprecioUni\tsubtotal \tigv\ttotal");

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

    public static void buscarFactura(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el ID de la factura que desea buscar:");
            int idFactura = entrada.nextInt();

            String query = "SELECT * FROM facturas WHERE idFactura = " + idFactura;
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("");
                System.out.println("==================FACTURA DE LA VENTA REALIZADA===================");
                System.out.println("ID\tFecha\t\tidCli\tidPro\tcantidad \tprecioUni\tsubtotal \tigv\ttotal");
                System.out.println("");

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
            } else {
                System.out.println("No se encontró ninguna factura con el ID ingresado.");
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error al buscar la factura: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());

        }

    }

    public class DacturaFetalle {

        public void hacerFacturaDetalle(ResultSet rs, Statement stmt, Scanner entrada) {
            // Solicitar al usuario el idCliente y la fecha de la factura
            System.out.print("Ingrese el ID del cliente: ");
            int idCliente = entrada.nextInt();
            System.out.print("Ingrese la fecha de la factura (formato YYYY-MM-DD): ");
            String fecha = entrada.next();

            // Crear una instancia de la clase Factura y agregar detalles
            Factura factura = new Factura(idCliente, 0, 0, fecha);
            System.out.print("Ingrese el número de detalles de factura: ");
            int n = entrada.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.print("Ingrese el ID del producto: ");
                int idProducto = entrada.nextInt();
                System.out.print("Ingrese la cantidad: ");
                int cantidad = entrada.nextInt();

                float precio = ProductoBase.getPrecio(idProducto);
                float subtotal = precio * cantidad;
                factura.detalles(new DetalleFactura(factura.getId(), idProducto, precio, cantidad, subtotal));
            }

            // Calcular el total y el igv de la factura por los detalles 
            float total = 0;
            for (DetalleFactura detalle : factura.getDetalles()) {
                total += detalle.getSubtotal();
            }
            float igv = total * 0.18f;
            factura.setTotal(total);
            factura.setIgv(igv);

            // Guardar la factura y sus detalles en la base de datos
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "tu_usuario", "tu_contraseña")) {
                String query = "INSERT INTO facturas (idCliente, total, igv, fecha) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, factura.getIdCliente());
                ps.setFloat(2, factura.getTotal());
                ps.setFloat(3, factura.getIgv());
                ps.setString(4, factura.getFecha());
                ps.executeUpdate();

                // Obtener el id generado para la factura
                if (rs.next()) {
                    int idFactura = rs.getInt(1);
                    factura.setId(idFactura);
                    // Guardar los detalles de la factura
                    for (DetalleFactura detalle : factura.getDetalles()) {
                        query = "INSERT INTO detalle_factura (idFactura, idProducto,cantidad precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";
                        ps = conn.prepareStatement(query);
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
            } catch (SQLException e) {
                System.out.println("Error al guardar la factura en la base de datos: " + e.getMessage());
            }
        }
    }

    public class ReporteActores {

        public static void generarReporte() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto?"
                        + "user=root&password=12345678");
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        "C:\\Users\\Lenovo\\JaspersoftWorkspace\\MyReports\\Factura.jasper",
                        null, conn);
                JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Lenovo\\JaspersoftWorkspace\\MyReports\\Factura.pdf");

                JasperViewer jasperViewer = new JasperViewer(jasperPrint);
                jasperViewer.setVisible(true);

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendedorError" + ex.getErrorCode());

            } catch (JRException jre) {
                System.out.println(jre.getMessage());
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException sqlEx) {
                        System.out.println(sqlEx.getMessage());
                    }
                    conn = null;
                }
            }
        }
    }

}

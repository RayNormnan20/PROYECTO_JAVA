package PROYECTO.VENTAS;

import PROYECTO.VENTAS.DetalleFactura;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
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
    private float subtotal;
    private float total;
    private static final float igv;
    private String fecha;
    private List<DetalleFactura> detalles;

    public Factura(int idCliente, float subtotal, float total, float igv, String fecha) {
        this.idCliente = idCliente;
        this.subtotal = subtotal;
        this.total = total;
        Factura.igv = igv;
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

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
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
            System.out.println("=================FACTURA DE LA VENTA REALIZADA================");
            System.out.println("ID\tidCli\t\tsubtotal\t\ttotal\t\tigv\t\tFecha");

            while (rs.next()) {
                int idFactura = rs.getInt("idFactura");
                String fecha = rs.getString("fecha");
                int idCliente = rs.getInt("idCliente");
                float subTotal = rs.getFloat("subtotal");
                float igv = rs.getFloat("igv");
                float total = rs.getFloat("total");

                System.out.println(idFactura + "\t" + idCliente + "\t\t" + subTotal + "\t\t" + total + "\t\t" + igv + "\t\t" + fecha);
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
                System.out.println("=========FACTURA DE LA VENTA REALIZADA===========");
                System.out.println("ID\tidCli\tsubtotal\t\ttotal\tigv\tFecha");
                System.out.println("");

                int idCliente = rs.getInt("idCliente");
                float subtotal = rs.getFloat("subtotal");
                float total = rs.getFloat("total");
                float igv = rs.getFloat("igv");
                String fecha = rs.getString("fecha");

                System.out.println(idFactura + "\t" + idCliente + "\t" + subtotal + "\t\t" + total + "\t" + igv + "\t" + fecha);
            } else {
                System.out.println("No se encontro ninguna factura con el ID ingresado.");
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error al buscar la factura: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());

        }

    }

    public class ReporteFacturas {

        public static void generarReporte() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto?"
                        + "user=root&password=12345678");
                JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Lenovo\\JaspersoftWorkspace\\MyReports\\REPORTE_FACTURAS.jasper", null, conn);
                //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Lenovo\\JaspersoftWorkspace\\MyReports\\REPORTE_FACTURAS.pdf");
                System.out.println("Archivo creado correctamente");
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

    public static void generarReporteVentas() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto?"
                    + "user=root&password=12345678");
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Lenovo\\JaspersoftWorkspace\\MyReports\\FACTURA_VENTA.jasper", null, conn);
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Lenovo\\JaspersoftWorkspace\\MyReports\\FacturaVentas.pdf");
            System.out.println("Archivo creado correctamente");
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



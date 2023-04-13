package PROYECTO;

/**
 *
 * @author Lenovo
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ventas {

    public void hacerVenta(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.println("Ingrese la fecha: ");
            String fecha = entrada.next();
            System.out.print("Ingrese el ID del cliente: ");
            int idCliente = entrada.nextInt();
            entrada.nextLine();

            // The next() method should be called after executing the query to get the result set
            rs = stmt.executeQuery("SELECT * FROM clientes WHERE idCliente = " + idCliente);
            if (!rs.next()) {
                System.out.println("No existe un cliente con el ID ingresado.");
                return;
            }

            System.out.print("Ingrese el ID del producto a vender: ");
            int idProducto = entrada.nextInt();
            entrada.nextLine();

            rs = stmt.executeQuery("SELECT * FROM productos_base WHERE idPro = " + idProducto);
            if (rs.next()) {
                int stock = rs.getInt("stock");
                if (stock <= 0) {
                    System.out.println("No hay suficiente stock para realizar la venta.");
                    return;
                }
                System.out.print("Ingrese la cantidad: ");
                int cantidad = entrada.nextInt();

                // The nextDouble() method should be called to get the total, not to read the quantity entered above
                // Also, initialize the total to 0.0f to avoid a possible uninitialized variable error
                double total = 0.0f;

                float precioUnitario = rs.getFloat("precio");

                float subtotal = cantidad * precioUnitario;
                float igv = subtotal * 0.18f; // 18% de IGV
                total = subtotal + igv;
                stmt.executeUpdate("UPDATE productos_base SET stock = " + (stock - cantidad) + " WHERE idPro = " + idProducto);
                stmt.executeUpdate("INSERT INTO facturas (fecha, idCliente, idProducto, cantidad, precioUnitario, subtotal, igv, total) "
                        + "SELECT '" + fecha + "', " + idCliente + ", " + idProducto + ", " + cantidad + ", precio, " + subtotal + ", " + igv + ", " + total
                        + " FROM productos_base WHERE idPro = " + idProducto);

                System.out.println("");
                System.out.println("Venta realizada correctamente.");
            } else {
                System.out.println("No existe un producto con el ID " + idProducto + " ingresado.");
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error al realizar la venta: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

    public void listarTodo(ResultSet rs, Statement stmt) {
        try {
            System.out.println("\nListado Completo de las Tablas");
            System.out.println("--------------------------------");

            rs = stmt.executeQuery("SELECT p.idPro, p.nombre, e.modelo, e.potencia, a.fecha_vencimiento, a.pais_origen, p.descrip, p.precio, p.stock FROM productos_base p INNER JOIN productos_electronicos e ON p.idElectronicos = e.idPro INNER JOIN productos_alimenticios a ON p.idAlimenti = a.idProAli");

            System.out.println("ID\tNombre\t\tModelo\t\tPotencia\t\tFecha de Vencimiento\tPais de Origen\tDescripcion\tPrecio\tStock");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {

                int idPro = rs.getInt("idPro");
                String nombre = rs.getString("nombre");
                String modelo = rs.getString("modelo");
                double potencia = rs.getDouble("potencia");
                String fecha_vencimiento = rs.getString("fecha_vencimiento");
                String pais_origen = rs.getString("pais_origen");
                String descrip = rs.getString("descrip");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                // Imprimir los valores de las columnas
                System.out.println(idPro + "\t" + nombre + "\t\t" + modelo + "\t\t" + potencia + "\t\t" + fecha_vencimiento + "\t\t" + pais_origen + "\t\t" + descrip + "\t\t" + precio + "\t" + stock);
            }
            System.out.println("Found a record!");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendedorError" + ex.getErrorCode());
        }
    }

}
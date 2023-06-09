package PROYECTO.VENTAS;

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
            System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
            String fecha = entrada.next();
            System.out.print("Ingrese el ID del cliente: ");
            int idCliente = entrada.nextInt();
            entrada.nextLine();

            rs = stmt.executeQuery("SELECT * FROM clientes WHERE idCliente = " + idCliente);
            if (!rs.next()) {
                System.out.println("No existe un cliente con el ID ingresado.");
                return;
            }

            boolean seguirAgregandoProductos = true;
            double subtotal = 0.0;
            double total = 0;

            stmt.executeUpdate("INSERT INTO facturas (idCliente, subtotal, total, igv, fecha) "
                    + "VALUES (" + idCliente + ", " + subtotal + ", " + total + ", " + 0.0 + ", '" + fecha + "')", Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            int idFactura = 0;
            if (rs.next()) {
                idFactura = rs.getInt(1);
            }

            while (seguirAgregandoProductos) {
                System.out.print("Ingrese el ID del producto a vender: ");
                int idProducto = entrada.nextInt();
                entrada.nextLine();

                rs = stmt.executeQuery("SELECT * FROM productos_base WHERE idPro = " + idProducto);
                if (rs.next()) {
                    int stock = rs.getInt("stock");
                    if (stock <= 0) {
                        System.out.println("No hay suficiente stock para realizar la venta.");
                        continue;
                    }
                    System.out.print("Ingrese la cantidad: ");
                    int cantidad = entrada.nextInt();

                    double precioUnitario = rs.getDouble("precio");

                    double subtotalProducto = cantidad * precioUnitario;
                    subtotal += subtotalProducto;
                    double igv = subtotal * 0.18f; // 18% 

                    //Redondeo
                    total = subtotal + igv;

                    // Redondeo de 2 decimales con Math
                    stmt.executeUpdate("UPDATE productos_base SET stock = " + (stock - cantidad) + " WHERE idPro = " + idProducto);
                    stmt.executeUpdate("INSERT INTO detalle_factura (idFactura, idProducto, cantidad, precioUnitario) "
                            + "VALUES (" + idFactura + ", " + idProducto + ", " + cantidad + ", " + precioUnitario + ")");

                    System.out.println("");
                    System.out.println("Producto agregado correctamente.");
                    System.out.print("¿Desea agregar otro producto? (S/N): ");
                    entrada.nextLine();
                    String respuesta = entrada.nextLine();
                    if (!respuesta.equalsIgnoreCase("S")) {
                        seguirAgregandoProductos = false;
                    }
                } else {
                    System.out.println("No existe un producto con el ID " + idProducto + " ingresado.");
                }
            }

            double igv = subtotal * 0.18f;
            total = subtotal + igv;
            stmt.executeUpdate("UPDATE facturas SET subtotal = " + subtotal + ", total = " + total + ", igv = " + igv + " WHERE idFactura = " + idFactura);

            System.out.println("");
            System.out.println("Venta registrada correctamente.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendedorError" + ex.getErrorCode());
        }
    }

}

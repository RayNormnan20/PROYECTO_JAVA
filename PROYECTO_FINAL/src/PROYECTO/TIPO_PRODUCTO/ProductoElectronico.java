package PROYECTO.TIPO_PRODUCTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class ProductoElectronico {

    private static void menuPrincipal() {
    }

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

    /*========================================TODO SOBLE LA TABLA GENERAL ELECTRONICOS===================================================================*/
    public static void registrarElectronicos(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese modelo del producto electrónico: ");
            String modelo = entrada.next();
            System.out.print("Ingrese la potencia del producto: ");
            int potencia = entrada.nextInt();
            // Consume el salto de línea pendiente
            entrada.nextLine(); 

            ProductoElectronico proElec = new ProductoElectronico(modelo, potencia);
            stmt.executeUpdate("INSERT INTO productos_electronicos (modelo, potencia) VALUES ('" + proElec.getModelo() + "', '" + proElec.getPotencia() + "');");

            System.out.println("Producto agregado correctamente.");
        } catch (SQLException sqlEx) {
            System.out.println("Error al insertar el producto: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }

    }

    public static void listaProductosElectronicos(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.println("\nListado Productos Electronicos");
            System.out.println("--------------------------------");
            rs = stmt.executeQuery("SELECT * FROM productos_electronicos");

            System.out.println("ID\tModelo\t\tPotencia");
            System.out.println("---------------------------------");

            while (rs.next()) {
                int idPro = rs.getInt("idPro");
                String modelo = rs.getString("modelo");
                double potencia = rs.getDouble("potencia");

                // Imprimir los valores de las columnas
                System.out.println(idPro + "\t" + modelo + "\t\t" + potencia);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendedorError" + ex.getErrorCode());
        }
    }

    public static void eliminarProductoElectronico(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el ID del producto electrónico a eliminar: ");
            int idPro = entrada.nextInt();
            entrada.nextLine();

            // Ver si hay productos ==
            rs = stmt.executeQuery("SELECT * FROM productos_base WHERE idPro = " + idPro);
            if (rs.next()) {
                System.out.println("No se puede eliminar el producto electrónico porque existen productos base que lo están referenciando.");
                return;
            }

            // Si no hay se elimna
            rs = stmt.executeQuery("SELECT * FROM productos_electronicos WHERE idPro = " + idPro);
            if (rs.next()) {
                stmt.executeUpdate("DELETE FROM productos_electronicos WHERE idPro = " + idPro);
                System.out.println("Producto electrónico eliminado correctamente.");
            } else {
                System.out.println("No existe un producto electrónico con el ID ingresado.");
            }
            menuPrincipal();
        } catch (SQLException sqlEx) {
            System.out.println("Error al eliminar el producto electrónico: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

    public static void actualizarProductoElectronico(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el ID del producto electrónico a actualizar: ");
            int idPro = entrada.nextInt();
            entrada.nextLine();

            rs = stmt.executeQuery("SELECT modelo, potencia FROM productos_electronicos WHERE idPro = '" + idPro + "'");
            if (!rs.next()) {
                System.out.println("No se encontró ningún cliente con el ID ingresado.");
                return;
            }
            System.out.print("Ingrese el nuevo modelo del producto electrónico: ");
            String modelo = entrada.nextLine();

            
            System.out.print("Ingrese la nueva potencia del producto electronico: ");
            int potencia = entrada.nextInt();
            

            ProductoElectronico proElec = new ProductoElectronico(modelo, potencia);
            stmt.executeUpdate("UPDATE productos_electronicos SET modelo = '" + proElec.getModelo()
                    + "', potencia = " + proElec.getPotencia() + " WHERE idPro = " + idPro);

            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException sqlEx) {
            System.out.println("Error al actualizar el producto electrónico: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

}

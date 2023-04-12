package PROYECTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class ProductosAlimenticios {

    private static void menuPrincipal() {
    }

    private String fechaVencimiento;
    private String paisOrigen;

    public ProductosAlimenticios(String fechaVencimiento, String paisOrigen) {
        this.fechaVencimiento = fechaVencimiento;
        this.paisOrigen = paisOrigen;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /*========================================TODO SOBLE LA TABLA GENERAL ALIMENTICIOS===================================================================*/
    public static void registrarAlimenticios(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.println("Ingrese la fecha de vencimiento del producto (en formato DD/MM/YYYY): ");
            String fechaVencimiento = entrada.next();

            System.out.println("Ingrese el país de origen del producto: ");
            String paisOrigen = entrada.nextLine();

            ProductosAlimenticios proAlimen = new ProductosAlimenticios(fechaVencimiento, paisOrigen);
            stmt.executeUpdate("INSERT INTO productos_alimenticios (fecha_vencimiento, pais_origen) VALUES ('" + proAlimen.getFechaVencimiento() + "', '" + proAlimen.getPaisOrigen() + "');");
            System.out.println("Producto agregado correctamente.");
        } catch (SQLException sqlEx) {
            System.out.println("Error al insertar el producto: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

    public static void listarAlimenticios00(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            rs = stmt.executeQuery("SELECT * FROM productos_alimenticios");

            System.out.println("ID\tFecha \t\tPaís de origen");
            System.out.println("---------------------------------");

            // Iterar sobre los resultados de la consulta
            while (rs.next()) {
                int idProAli = rs.getInt("idProAli");
                String fecha_vencimiento = rs.getString("fecha_vencimiento");
                String pais_origen = rs.getString("pais_origen");

                System.out.println(idProAli + "\t" + fecha_vencimiento + "\t" + pais_origen);
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error al obtener el listado de productos alimenticios: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

    public static void actualizarAlimenticios(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            // Solicitar el ID del producto a actualizar
            System.out.println("Ingrese el ID del producto alimenticio que desea actualizar: ");
            int idProAli = entrada.nextInt();
            entrada.nextLine();

            System.out.println("Ingrese la nueva fecha de vencimiento del producto (en formato YYYY/MM/DD): ");
            String fechaVencimiento = entrada.nextLine();

            System.out.println("Ingrese el nuevo país de origen del producto: ");
            String paisOrigen = entrada.nextLine();

            // Actualizar el registro correspondiente en la tabla productos_alimenticios
            ProductosAlimenticios proAlimen = new ProductosAlimenticios(fechaVencimiento, paisOrigen);

            stmt.executeUpdate("UPDATE productos_alimenticios SET fecha_vencimiento = '" + proAlimen.getFechaVencimiento()
                    + "', pais_origen = '" + proAlimen.getPaisOrigen() + "' WHERE id = '" + idProAli + "'");

            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException sqlEx) {
            System.out.println("Error al actualizar el producto: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());

        }
    }

    public static void eliminarProductoAlimenticio(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el ID del producto alimenticio a eliminar: ");
            int idProAli = entrada.nextInt();
            entrada.nextLine();

            // Verificar si existen productos_base que estén haciendo referencia al registro a eliminar
            rs = stmt.executeQuery("SELECT * FROM productos_base WHERE idAlimenti = " + idProAli);
            if (rs.next()) {
                System.out.println("No se puede eliminar el producto alimenticio porque existen productos base que lo están referenciando.");
                return;
            }

            // Si no hay productos_base referenciando el producto alimenticio, se puede eliminar el registro
            rs = stmt.executeQuery("SELECT * FROM productos_alimenticios WHERE idProAli = " + idProAli);
            if (rs.next()) {
                stmt.executeUpdate("DELETE FROM productos_alimenticios WHERE idProAli = " + idProAli);
                System.out.println("Producto alimenticio eliminado correctamente.");
            } else {
                System.out.println("No existe un producto alimenticio con el ID: " + idProAli + " ingresado.");
            }
            menuPrincipal();
        } catch (SQLException sqlEx) {
            System.out.println("Error al eliminar el producto alimenticio " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

}

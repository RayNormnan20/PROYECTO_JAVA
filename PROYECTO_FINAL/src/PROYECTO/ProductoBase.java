package PROYECTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

    /*========================================TODO SOBLE LA TABLA GENERAL PRODUCTOS===================================================================*/
    public static void actualizarProductoBase(Connection conn, ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            PreparedStatement ps;
            System.out.print("Ingrese el ID del producto a modificar: ");
            int idPro = entrada.nextInt();
            entrada.nextLine();

            // Verificar que el producto existe en la tabla de productos_base
            rs = stmt.executeQuery("SELECT * FROM productos_base WHERE idPro = '" + idPro + "'");
            if (!rs.next()) {
                System.out.println("No se encontró ningún producto con el ID ingresado.");
                return;
            }

            System.out.print("Ingrese el nuevo nombre del producto: ");
            String nombre = entrada.nextLine();
            System.out.print("Ingrese la nueva descripción del producto: ");
            String descrip = entrada.nextLine();
            System.out.print("Ingrese el nuevo precio del producto: ");
            float precio = entrada.nextFloat();
            System.out.print("Ingrese el nuevo stock del producto: ");
            int stock = entrada.nextInt();
            entrada.nextLine();

            // Verificar si el producto es electr...
            ps = conn.prepareStatement("SELECT idElectronicos FROM productos_base WHERE idPro = ?");
            ps.setInt(1, idPro);
            rs = ps.executeQuery();
            Integer idElectronicos = null;
            if (rs.next()) {
                idElectronicos = rs.getInt("idElectronicos");
            }

            // Verificar si el producto es alimenticio
            ps = conn.prepareStatement("SELECT idAlimenti FROM productos_base WHERE idPro = ?");
            ps.setInt(1, idPro);
            rs = ps.executeQuery();
            Integer idAlimenti = null;
            if (rs.next()) {
                idAlimenti = rs.getInt("idAlimenti");
            }

            // Modificar los datos en la tabla ProBASSE
            ps = conn.prepareStatement("UPDATE productos_base SET nombre = ?, idElectronicos = ?, idAlimenti = ?, descrip = ?, precio = ?, stock = ? WHERE idPro = ?");
            ps.setString(1, nombre);
            ps.setObject(2, idElectronicos);
            ps.setObject(3, idAlimenti);
            ps.setString(4, descrip);
            ps.setFloat(5, precio);
            ps.setInt(6, stock);
            ps.setInt(7, idPro);
            ps.executeUpdate();

            System.out.println("Producto modificado correctamente.");

        } catch (SQLException sqlEx) {
            System.out.println("Error al modificar el producto: " + sqlEx.getMessage());
        }
    }

    public static void listaProductos(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.println("\nListado de productos");
            System.out.println("----------------------");
            rs = stmt.executeQuery("Select idPro,nombre,idElectronicos,idAlimenti,descrip,precio, stock FROM productos_base");
            while (rs.next()) {
                System.out.println(rs.getInt("idPro") + " "
                        + rs.getString("nombre") + " " + rs.getInt("idElectronicos") + " " + rs.getString("descrip")
                        + " " + rs.getDouble("precio") + " " + rs.getInt("stock"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendedorError" + ex.getErrorCode());
        }
    }

    public static void eliminarProductoBase(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            listaProductos(rs, stmt, entrada);
            System.out.print("Ingrese el ID del producto que desea eliminar: ");
            int idPro = entrada.nextInt();
            entrada.nextLine(); // Consumir el salto de línea

            rs = stmt.executeQuery("SELECT * FROM productos_base WHERE idPro = " + idPro);

            if (rs.next()) {
                stmt.executeUpdate("DELETE FROM productos_base WHERE idPro = " + idPro);

                System.out.println("Producto eliminado correctamente.");
                listaProductos(rs, stmt, entrada);
            } else {
                System.out.println("No se encontró ningún producto con el ID: " + idPro + " ingresado.");
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error al eliminar el producto: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

}

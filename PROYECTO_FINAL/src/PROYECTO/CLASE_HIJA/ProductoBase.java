package PROYECTO.CLASE_HIJA;

import PROYECTO.CLASE_PADRE.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.DriverManager;

public class ProductoBase extends Productos {

    private int idElectronicos;
    private int idAlimenti;
    private String descrip;

    public ProductoBase(String nombre, int idElectronicos, int idAlimenti, String descrip, double precio, int stock) {
        super(nombre, precio, stock);
        this.idElectronicos = idElectronicos;
        this.idAlimenti = idAlimenti;
        this.descrip = descrip;

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

    Connection conn = null;

    // Creamos una clase 
    public static float getPrecio(int idProducto) {
        float precio = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto?", "root", "12345678")) {
            PreparedStatement ps = conn.prepareStatement("SELECT precio FROM productos_base WHERE idPro = ?");
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                precio = rs.getFloat("precio");
            } else {
                System.out.println("No se encontró ningún producto con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el precio del producto en la base de datos: " + e.getMessage());
        }
        return precio;
    }


    /*========================================TODO SOBLE LA TABLA GENERAL PRODUCTOS===================================================================*/
    public static void actualizarProductoBase(Connection conn, Scanner entrada) {
        try {
            PreparedStatement ps;
            System.out.print("Ingrese el ID del producto a modificar: ");
            int idPro = entrada.nextInt();
            entrada.nextLine();

            // Verificar que el producto existe
            ps = conn.prepareStatement("SELECT * FROM productos_base WHERE idPro = ?");
            ps.setInt(1, idPro);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("No se encontró ningún producto con el ID " + idPro + " ingresado.");
                return;
            }

            System.out.print("Ingrese el nuevo nombre del producto: ");
            String nombre = entrada.nextLine();
            System.out.print("Ingrese la nueva descripcion del producto: ");
            String descrip = entrada.nextLine();
            System.out.print("Ingrese el nuevo precio del producto: ");
            float precio = entrada.nextFloat();
            System.out.print("Ingrese el nuevo stock del producto: ");
            int stock = entrada.nextInt();
            entrada.nextLine();

            // Verificar si el producto es electrónico o alimenticio
            ps = conn.prepareStatement("SELECT idElectronicos, idAlimenti FROM productos_base WHERE idPro = ?");
            ps.setInt(1, idPro);
            rs = ps.executeQuery();
            Integer idElectronicos = null;
            Integer idAlimenti = null;
            if (rs.next()) {
                idElectronicos = (Integer) rs.getObject("idElectronicos");
                idAlimenti = (Integer) rs.getObject("idAlimenti");
            }

            // Modificar los datos en la tabla proBase
            ps = conn.prepareStatement("UPDATE productos_base SET nombre = ?, idElectronicos = ?, idAlimenti = ?, descrip = ?, precio = ?, stock = ? WHERE idPro = ?");
            ps.setString(1, nombre);
            ps.setObject(2, idElectronicos);
            ps.setObject(3, idAlimenti);
            ps.setString(4, descrip);
            ps.setFloat(5, precio);
            ps.setInt(6, stock);
            ps.setInt(7, idPro);
            int lineasAfectadas = ps.executeUpdate();

            if (lineasAfectadas > 0) {
                System.out.println("Producto modificado correctamente.");
            } else {
                System.out.println("No se pudo modificar el producto.");
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error al modificar el producto: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("Error Code: " + sqlEx.getErrorCode());
        }
    }

    public static void listaProductos(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.println("\nListado de productos");
            System.out.println("----------------------");
            rs = stmt.executeQuery("Select idPro,nombre,idElectronicos,idAlimenti,descrip,precio, stock FROM productos_base");
            while (rs.next()) {
                System.out.println(rs.getInt("idPro") + " "
                        + rs.getString("nombre") + " " + rs.getInt("idElectronicos") + " " + rs.getInt("idAlimenti") + " " + rs.getString("descrip")
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
            entrada.nextLine();

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

package PROYECTO.CLASE_HIJA;

import PROYECTO.CLASE_PADRE.Persona;
import static PROYECTO.SALIDA.main.cliNuevo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Cliente extends Persona {

    private String correo;
    private String direccion;

    public Cliente(String nombre, String apellido, String correo, String direccion) {
        super(nombre, apellido);
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*========================================TODO SOBLE LA TABLA GENERAL CLIENTES======================================================================*/
    public static void registrarClientes(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = entrada.nextLine();
            System.out.print("Ingrese apellido del cliente: ");
            String apellido = entrada.nextLine();
            System.out.print("Ingrese correo del cliente: ");
            String correo = entrada.nextLine();
            System.out.print("Ingrese la dirección del cliente: ");
            String direccion = entrada.nextLine();
            
            // Instancia de la clase cliente
            Cliente cli = new Cliente(nombre, apellido, correo, direccion);

            stmt.executeUpdate("INSERT INTO clientes (nombre, apellido, correo, direccion) values ('"
                    + cli.getNombre() + "', '" + cli.getApellido() + "', '"
                    + cli.getCorreo() + "', '" + cli.getDireccion() + "')");
            cliNuevo.add(cli);
            System.out.println("Cliente agregado correctamente.");

        } catch (SQLException sqlEx) {
            System.out.println("Error al insertar el cliente: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

    public static void listaClientes(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.println("\nListado de Clientes");
            System.out.println("----------------------");

            rs = stmt.executeQuery("SELECT * FROM  clientes");

            System.out.println("ID\tNombre\t\t\tApellido\t\t\tCorreo\t\t\tDireccion");
            System.out.println("------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                int idCli = rs.getInt("idCliente");
                String nom = rs.getString("nombre");
                String ape = rs.getString("apellido");
                String correo = rs.getString("correo");
                String direc = rs.getNString("direccion");

                System.out.println(idCli + "\t" + nom + "\t\t\t" + ape + "\t\t\t" + correo + "\t\t" + direc );

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendedorError" + ex.getErrorCode());
        }

    }

    public static void actualizarClientes(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el ID del cliente a actualizar: ");
            int idCliente = entrada.nextInt();
            entrada.nextLine();

            rs = stmt.executeQuery("SELECT nombre, apellido, correo, direccion FROM clientes WHERE idCliente = '" + idCliente + "'");
            if (!rs.next()) {
                System.out.println("No se encontro ningun cliente con el ID ingresado.");
                return;
            }
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = entrada.nextLine();
            System.out.print("Ingrese el apellido del cliente: ");
            String apellido = entrada.nextLine();
            System.out.print("Ingrese el correo del cliente: ");
            String correo = entrada.nextLine();
            System.out.print("Ingrese la dirección del cliente: ");
            String direccion = entrada.nextLine();

            Cliente cli = new Cliente(nombre, apellido, correo, direccion);
            stmt.executeUpdate("UPDATE clientes SET nombre = '" + cli.getNombre() + "', apellido = '" + cli.getApellido()
                    + "', correo = '" + cli.getCorreo() + "', direccion = '" + cli.getDireccion() + "' WHERE idCliente = " + idCliente);

            System.out.println("Cliente actualizado correctamente.");

        } catch (SQLException sqlEx) {
            System.out.println("Error al modificar el cliente: " + sqlEx.getMessage());
             
        }
    }

    public static void eliminarCliente(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el ID del cliente a eliminar: ");
            int idCliente = entrada.nextInt();
            entrada.nextLine();

            rs = stmt.executeQuery("SELECT * FROM clientes WHERE idCliente = " + idCliente);
            if (!rs.next()) {
                System.out.println("El cliente con ID " + idCliente + " no existe.");
                return;
            }
            stmt.executeUpdate("DELETE FROM clientes WHERE idCliente = " + idCliente);
            System.out.println("Cliente eliminado correctamente.");
        } catch (SQLException sqlEx) {
            System.out.println("Error al eliminar el cliente: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendedorError" + sqlEx.getErrorCode());
        }
    }

}

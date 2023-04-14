package PROYECTO;

import PROYECTO.ventas;
import PROYECTO.Cliente;
import static PROYECTO.Factura.ReporteActores.generarReporte;
import PROYECTO.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class main {

    private static void menuPrincipal() {
    }

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;
    public static List<Cliente> cliNuevo = new ArrayList<>();
    public static List<ProductoElectronico> proElectro = new ArrayList<>();
    public static List<ProductoBase> proNuevo = new ArrayList<>();
    public static List<ProductosAlimenticios> proAli = new ArrayList<>();
    public static List<Factura> nuFac = new ArrayList<>();

    public static void main(String[] args) throws ParseException, SQLException {

        ventas ventas = new ventas();

        //Variable spara moverse en menus
        int opcion1 = 0, opcion2 = 0, opcion3 = 0;
        Scanner entrada = new Scanner(System.in);
        //int opcion = 0;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto?"
                    + "user=root&password=12345678");
            stmt = conn.createStatement();

            //List<Producto> pro = new ArrayList<>();
            while (opcion1 != 7) {
                System.out.println("");
                System.out.println("======= MENU =======");
                System.out.println("1. Registro de Datos");
                System.out.println("2. Hacer una venta");
                System.out.println("3. Listar ventas");
                System.out.println("4. Buscar Factura");
                System.out.println("5. Listar Todo");
                System.out.println("6. Reporte Facturas");
                System.out.println("7. Salir");
                System.out.print("Ingrese la opcion deseada: ");
                opcion1 = entrada.nextInt();
                entrada.nextLine();

                switch (opcion1) {
                    case 1:
                        while (opcion2 != 5) {
                            System.out.println("");
                            System.out.println("======= Registro =======");
                            System.out.println("1. Registrar Electronicos ");
                            System.out.println("2. Resgitrar Alimenticios");
                            System.out.println("3. Registro Productos Generales");
                            System.out.println("4. Registrar Clientes");
                            System.out.println("5. Regresar al Menu Principal");
                            System.out.print("Ingrese la opcion deseada: ");
                            opcion2 = entrada.nextInt();
                            entrada.nextLine();

                            switch (opcion2) {
                                case 1:
                                    while (opcion3 != 5) {
                                        System.out.println("");
                                        System.out.println("====PRODUCTOS ELECTRONICOS====");
                                        System.out.println("1. Registrar");
                                        System.out.println("2. Eliminar");
                                        System.out.println("3. Actualizar");
                                        System.out.println("4. Listar");
                                        System.out.println("5. Regresar Menu anterior");
                                        System.out.print("Elija una opción:");
                                        opcion3 = entrada.nextInt();
                                        entrada.nextLine();
                                        switch (opcion3) {
                                            case 1:
                                                ProductoElectronico.registrarElectronicos(rs, stmt, entrada);
                                                break;
                                            case 2:
                                                ProductoElectronico.eliminarProductoElectronico(rs, stmt, entrada);
                                                break;
                                            case 3:
                                                ProductoElectronico.actualizarProductoElectronico(rs, stmt, entrada);
                                                break;
                                            case 4:
                                                ProductoElectronico.listaProductosElectronicos(rs, stmt, entrada);
                                                break;
                                            case 5:
                                                break;
                                            default:
                                                System.out.println("Opción inválida");
                                                break;
                                        }
                                    }
                                    opcion3 = 0;
                                    break;

                                case 2:
                                    while (opcion3 != 5) {
                                        System.out.println("");
                                        System.out.println("====PRODUCTOS ALIMENTICIOS====");
                                        System.out.println("1. Registrar");
                                        System.out.println("2. Eliminar");
                                        System.out.println("3. Actualizar");
                                        System.out.println("4. Listar");
                                        System.out.println("5. Regresar Menu anterior");
                                        System.out.print("Elija una opción:");
                                        opcion3 = entrada.nextInt();
                                        entrada.nextLine();

                                        switch (opcion3) {
                                            case 1:
                                                ProductosAlimenticios.registrarAlimenticios(rs, stmt, entrada);
                                                break;
                                            case 2:
                                                ProductosAlimenticios.eliminarProductoAlimenticio(rs, stmt, entrada);
                                                break;
                                            case 3:
                                                ProductosAlimenticios.actualizarAlimenticios(rs, stmt, entrada);
                                                break;
                                            case 4:
                                                ProductosAlimenticios.listarAlimenticios00(rs, stmt, entrada);
                                                break;
                                            case 5:
                                                break;
                                            default:
                                                System.out.println("Opción inválida");
                                                break;
                                        }
                                    }
                                    opcion3 = 0;
                                    break;
                                case 3:
                                    while (opcion3 != 5) {
                                        System.out.println("");
                                        System.out.println("====PRODUCTOS GENERALES====");
                                        System.out.println("1. Registrar");
                                        System.out.println("2. Eliminar");
                                        System.out.println("3. Actualizar");
                                        System.out.println("4. Listar");
                                        System.out.println("5. Regresar Menu anterior");
                                        System.out.print("Elija una opción:");
                                        opcion3 = entrada.nextInt();
                                        entrada.nextLine();

                                        switch (opcion3) {
                                            case 1:
                                                try {
                                                System.out.print("Ingrese el nombre del producto: ");
                                                String nombre = entrada.nextLine();
                                                System.out.print("Ingrese la descripción del producto: ");
                                                String descrip = entrada.nextLine();
                                                System.out.print("Ingrese el precio del producto: ");
                                                float precio = entrada.nextFloat();
                                                System.out.print("Ingrese el stock del producto: ");
                                                int stock = entrada.nextInt();
                                                entrada.nextLine();

                                                // Agregamos datos en la tabla de productosElectr...
                                                System.out.println("¿El producto es electrónico? (s/n): ");
                                                String esElectronico = entrada.nextLine();
                                                Integer idElectronicos = null;
                                                if (esElectronico.equalsIgnoreCase("s")) {
                                                    System.out.println("Ingrese el ID del producto electrónico correspondiente: ");
                                                    idElectronicos = entrada.nextInt();
                                                    entrada.nextLine();

                                                    // Verificar que el producto electrónico existe en la tabla de productos_electronicos
                                                    PreparedStatement ps = conn.prepareStatement("SELECT idPro FROM productos_electronicos WHERE idPro = ?");
                                                    ps.setInt(1, idElectronicos);
                                                    rs = ps.executeQuery();
                                                    if (!rs.next()) {
                                                        System.out.println("Error: el producto electrónico con ID " + idElectronicos + " no existe.");
                                                        return;
                                                    }
                                                }

                                                // Insertar datos en la tabla de productosAliment...
                                                System.out.print("¿El producto es alimenticio? (s/n): ");
                                                String esAlimenticio = entrada.nextLine();
                                                Integer idAlimenti = null;
                                                if (esAlimenticio.equalsIgnoreCase("s")) {
                                                    System.out.print("Ingrese el ID del producto alimenticio correspondiente: ");
                                                    idAlimenti = entrada.nextInt();
                                                    entrada.nextLine();
                                                }

                                                // Insertar datos
                                                PreparedStatement ps = conn.prepareStatement("INSERT INTO productos_base (nombre, idElectronicos, idAlimenti, descrip, precio, stock) VALUES (?, ?, ?, ?, ?, ?)");
                                                ps.setString(1, nombre);
                                                ps.setObject(2, idElectronicos);
                                                ps.setObject(3, idAlimenti);
                                                ps.setString(4, descrip);
                                                ps.setFloat(5, precio);
                                                ps.setInt(6, stock);
                                                ps.executeUpdate();

                                                System.out.println("Producto agregado correctamente.");
                                                ProductoBase.listaProductos(rs, stmt, entrada);
                                            } catch (SQLException sqlEx) {
                                                System.out.println("Error al insertar el producto: " + sqlEx.getMessage());
                                            }
                                            break;
                                            case 2:
                                                ProductoBase.eliminarProductoBase(rs, stmt, entrada);
                                                break;
                                            case 3:
                                                ProductoBase.actualizarProductoBase(conn, rs, stmt, entrada);
                                                break;
                                            case 4:
                                                ProductoBase.listaProductos(rs, stmt, entrada);
                                                break;
                                            case 5:
                                                break;
                                            default:
                                                System.out.println("Opción inválida");
                                                break;
                                        }
                                    }
                                    opcion3 = 0;
                                    break;

                                case 4:
                                    while (opcion3 != 5) {
                                        System.out.println("");
                                        System.out.println("====CLIENTES====");
                                        System.out.println("1. Registrar");
                                        System.out.println("2. Eliminar");
                                        System.out.println("3. Actualizar");
                                        System.out.println("4. Listar");
                                        System.out.println("5. Regresar Menu anterior");
                                        System.out.print("Elija una opción:");
                                        opcion3 = entrada.nextInt();
                                        entrada.nextLine();

                                        switch (opcion3) {
                                            case 1:
                                                Cliente.registrarClientes(rs, stmt, entrada);
                                                break;
                                            case 2:
                                                Cliente.eliminarCliente(rs, stmt, entrada);
                                                break;
                                            case 3:
                                                Cliente.actualizarClientes(rs, stmt, entrada);
                                                break;
                                            case 4:
                                                Cliente.listaClientes(rs, stmt, entrada);
                                                break;
                                            case 5:
                                                break;
                                            default:
                                                System.out.println("Opción inválida");
                                                break;
                                        }
                                    }
                                    opcion3 = 0;
                                    break;

                                case 5:
                                    break;

                                default:
                                    System.out.println("Opción inválida");
                                    break;
                            }
                        }
                        opcion2 = 0;
                        break;

                    case 2:
                        ventas.hacerVenta(rs, stmt, entrada);
                        break;

                    case 3:
                        Factura.listaFacturaVentas(rs, stmt, entrada);
                        break;

                    case 4:
                        Factura.buscarFactura(rs, stmt, entrada);
                        break;

                    case 5:
                        ventas.listarTodo(rs, stmt);
                        break;
                    case 6:
                        generarReporte();
                        break;
                    case 7:
                        System.out.println("Hasta pronto!");
                        break;

                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendedorError" + ex.getErrorCode());

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
                stmt = null;
            }
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

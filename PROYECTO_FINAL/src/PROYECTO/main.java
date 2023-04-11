package PROYECTO;

import PROYECTO.Venta;
import PROYECTO.Cliente;
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

        Venta ventas = new Venta();

        int opcion1 = 0, opcion2 = 0, opcion3 = 0, opcion4 = 0;
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
            while (opcion1 != 4) {
                System.out.println("");
                System.out.println("======= MENU =======");
                System.out.println("1. Registro de Datos");
                System.out.println("2. Hacer una venta");
                System.out.println("3. Listar ventas");
                System.out.println("4. Salir");
                System.out.print("Ingrese la opcion deseada: ");
                opcion1 = entrada.nextInt();
                entrada.nextLine();

                switch (opcion1) {
                    case 1:
                        while (opcion1 != 5) {
                            System.out.println("");
                            System.out.println("======= Registro =======");
                            System.out.println("1. Registrar Electronicos ");
                            System.out.println("2. Resgitrar Alimenticios");
                            System.out.println("3. Registro Productos Generales");
                            System.out.println("4. Registrar Clientes");
                            System.out.println("5. Regresar al Menú Principal");
                            System.out.print("Ingrese la opcion deseada: ");
                            opcion2 = entrada.nextInt();
                            entrada.nextLine();

                            while (opcion2 != 5) {
                                switch (opcion2) {
                                    case 1:
                                        while (opcion2 != 6) {
                                            System.out.println("");
                                            System.out.println("====PRODUCTOS ELECTRONICOS====");
                                            System.out.println("1. Registrar");
                                            System.out.println("2. Eliminar");
                                            System.out.println("3. Actualizar");
                                            System.out.println("4. Listar");
                                            System.out.println("5. Regresar Menu anterior");
                                            System.out.print("Elija una opción:");
                                            opcion3 = entrada.nextInt();
                                            switch (opcion3) {
                                                case 1:
                                                    listaProductosElectronicos(rs, stmt, entrada);
                                                    registrarElectronicos(rs, stmt, entrada);
                                                    break;
                                                case 2:
                                                    listaProductosElectronicos(rs, stmt, entrada);
                                                    eliminarProductoElectronico(rs, stmt, entrada);
                                                    menuPrincipal();
                                                    break;
                                                case 3:
                                                    listaProductosElectronicos(rs, stmt, entrada);
                                                    actualizarProductoElectronico(rs, stmt, entrada);
                                                    break;
                                                case 4:
                                                    listaProductosElectronicos(rs, stmt, entrada);
                                                case 5:
                                                    // System.out.println("Volviendo al menu principal");
                                                    //if (opcion2 == 5) {
                                                    break;
                                                // }
                                                default:
                                                    System.out.println("Opción inválida");
                                                    break;
                                            }

                                        }

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
                                            switch (opcion3) {
                                                case 1:
                                                    listarAlimenticios00(rs, stmt, entrada);
                                                    registrarAlimenticios(rs, stmt, entrada);
                                                    break;
                                                case 2:
                                                    listarAlimenticios00(rs, stmt, entrada);
                                                    eliminarProductoAlimenticio(rs, stmt, entrada);
                                                    menuPrincipal();
                                                    break;
                                                case 3:
                                                    listarAlimenticios00(rs, stmt, entrada);
                                                    actualizarAlimenticios(rs, stmt, entrada);
                                                case 4:
                                                    listarAlimenticios00(rs, stmt, entrada);
                                                    break;
                                                case 5:
                                                    System.out.println("Volviendo al menu principal");
                                                    break;
                                                default:
                                                    System.out.println("Opción inválida");
                                            }

                                        }

                                    case 3:
                                        while (opcion3 != 5) {
                                            System.out.println("");
                                            System.out.println("PODUCTOS");
                                            System.out.println("1. Registrar");
                                            System.out.println("2. Eliminar");
                                            System.out.println("3. Actualizar");
                                            System.out.println("4. Listar");
                                            System.out.println("5. Regresar Menu anterior");
                                            System.out.println("Elija una opción:");
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

                                                    // Agregamos datos en la tabla de productos_electronicos
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

                                                    // Insertar datos en la tabla de productos_alimenticios
                                                    System.out.print("¿El producto es alimenticio? (s/n): ");
                                                    String esAlimenticio = entrada.nextLine();
                                                    Integer idAlimenti = null;
                                                    if (esAlimenticio.equalsIgnoreCase("s")) {
                                                        System.out.print("Ingrese el ID del producto alimenticio correspondiente: ");
                                                        idAlimenti = entrada.nextInt();
                                                        entrada.nextLine();
                                                    }

                                                    // Insertar datos en la tabla de productos_base
                                                    PreparedStatement ps = conn.prepareStatement("INSERT INTO productos_base (nombre, idElectronicos, idAlimenti, descrip, precio, stock) VALUES (?, ?, ?, ?, ?, ?)");
                                                    ps.setString(1, nombre);
                                                    ps.setObject(2, idElectronicos);
                                                    ps.setObject(3, idAlimenti);
                                                    ps.setString(4, descrip);
                                                    ps.setFloat(5, precio);
                                                    ps.setInt(6, stock);
                                                    ps.executeUpdate();

                                                    System.out.println("Producto agregado correctamente.");
                                                    listaProductos(rs, stmt, entrada);
                                                } catch (SQLException sqlEx) {
                                                    System.out.println("Error al insertar el producto: " + sqlEx.getMessage());
                                                }
                                                break;
                                                case 2:
                                                    eliminarProductoBase(rs, stmt, entrada);
                                                    break;
                                                case 3:
                                                    //Actualizar productos base(FALTA)
                                                    actualizarProductoBase(conn, rs, stmt, entrada);
                                                    break;
                                                case 4:
                                                    listaProductos(rs, stmt, entrada);
                                                    break;
                                                case 5:
                                                    System.out.println("Volviendo al menu principal");

                                                    break;
                                                default:
                                                    System.out.println("Opción inválida");
                                            }

                                        }

                                    case 4:
                                        while (opcion3 != 6) {
                                            System.out.println("");
                                            System.out.println("====CLIENTES====");
                                            System.out.println("1. Registrar");
                                            System.out.println("2. Eliminar");
                                            System.out.println("3. Actualizar");
                                            System.out.println("4. Listar");
                                            System.out.println("5. Regresar Menu anterior");
                                            System.out.print("Elija una opción: ");
                                            opcion3 = entrada.nextInt();
                                            switch (opcion3) {
                                                case 1:
                                                    registrarClientes(rs, stmt, entrada);
                                                    break;
                                                case 2:
                                                    eliminarCliente(rs, stmt, entrada);
                                                    break;
                                                case 3:
                                                    actualizarClientes(rs, stmt, entrada);
                                                    break;
                                                case 4:
                                                    listaClientes(rs, stmt, entrada);
                                                    break;
                                                case 5:
                                                    break;

                                                default:
                                                    System.out.println("Opción inválida");
                                                    break;
                                            }
                                            break;
                                        }

                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Opcion invalida");
                                        break;
                                }

                            }
                            System.out.println("");
                            System.out.println("Regresando al menú principal....");
                            if (opcion2 == 5) {
                                break;
                            }
                        }
                        break;

                    case 2:
                        // Realiza una venta
                        ventas.hacerVenta(rs, stmt, entrada);
                        break;
                    case 3:
                        listaFacturaVentas(rs, stmt, entrada);
                        break;
                    case 4:
                        System.out.println("");
                        System.out.println("¡Gracias por usar nuestro programa!\n¡Te deseamos lo mejor!");
                        System.exit(0);

                    default:
                        System.out.println("Opcion incorrecta");
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

    /*========================================TODO SOBLE LA TABLA GENERAL CLIENTES======================================================================*/
    public static void registrarClientes(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = entrada.next();
            System.out.print("Ingrese apellido del cliente: ");
            String apellido = entrada.next();
            System.out.print("Ingrese correo del cliente: ");
            String correo = entrada.next();
            System.out.print("Ingrese la dirección del cliente: ");
            String direccion = entrada.next();

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
            rs = stmt.executeQuery("Select idCliente,nombre, apellido,correo, direccion FROM clientes");
            while (rs.next()) {
                System.out.println(rs.getInt("idCliente") + " "
                        + rs.getString("nombre") + " " + rs.getString("apellido")
                        + " " + rs.getString("correo") + " " + rs.getString("direccion"));
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

    /*========================================TODO SOBLE LA TABLA GENERAL ELECTRONICOS===================================================================*/
    public static void registrarElectronicos(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            System.out.println("Ingrese modelo del producto electrónico: ");
            String modelo = entrada.next();
            System.out.println("Ingrese la potencia del producto: ");
            int potencia = entrada.nextInt();
            entrada.nextLine(); // Consume el salto de línea pendiente

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
            System.out.println("Ingrese el ID del producto electrónico a actualizar: ");
            int idPro = entrada.nextInt();
            entrada.nextLine();

            rs = stmt.executeQuery("SELECT modelo, potencia FROM productos_electronicos WHERE idPro = '" + idPro + "'");
            if (!rs.next()) {
                System.out.println("No se encontró ningún cliente con el ID ingresado.");
                return;
            }
            System.out.println("Ingrese el nuevo modelo del producto electrónico: ");
            String modelo = entrada.nextLine();

            System.out.println("Ingrese la nueva potencia del producto electrónico: ");
            int potencia = entrada.nextInt();
            entrada.nextLine();

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

    /*========================================TODO SOBLE LA TABLA GENERAL PRODUCTOS===================================================================*/
    public static void actualizarProductoBase(Connection conn, ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            PreparedStatement ps;
            System.out.println("Ingrese el ID del producto a modificar: ");
            int idPro = entrada.nextInt();
            entrada.nextLine();

            // Verificar que el producto existe en la tabla de productos_base
            rs = stmt.executeQuery("SELECT * FROM productos_base WHERE idPro = '" + idPro + "'");
            if (!rs.next()) {
                System.out.println("No se encontró ningún producto con el ID ingresado.");
                return;
            }

            System.out.println("Ingrese el nuevo nombre del producto: ");
            String nombre = entrada.nextLine();
            System.out.println("Ingrese la nueva descripción del producto: ");
            String descrip = entrada.nextLine();
            System.out.println("Ingrese el nuevo precio del producto: ");
            float precio = entrada.nextFloat();
            System.out.println("Ingrese el nuevo stock del producto: ");
            int stock = entrada.nextInt();
            entrada.nextLine();

            // Verificar si el producto es electrónico
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

            // Modificar los datos en la tabla de productos_base
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

    public static void eliminarProductoBase(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            listaProductos(rs, stmt, entrada);
            System.out.println("Ingrese el ID del producto que desea eliminar: ");
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

    public static void listaFacturaVentas(ResultSet rs, Statement stmt, Scanner entrada) {
        try {
            rs = stmt.executeQuery("SELECT * FROM facturas");
            System.out.println("");
            System.out.println("==================FACTURA DE LA VENTA REALIZADA===================");
            System.out.println("ID\tFecha\t\tidCli\tidPro\tcantidad \tprecioUni\tsubtotal \tigv\ttotal");
            System.out.println("");

            // Iterar sobre los resultados de la consulta
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

}

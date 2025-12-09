package LAB10;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        GestorUsuarios gestor = new GestorUsuarios();

        // Inicializar datos base del sistema
        inicializarDatos(banco, gestor);

        // Iniciar el menú principal
        menuPrincipal(sc, banco, gestor);
    }

    // CARGAR DATOS INICIALES

    public static void inicializarDatos(Banco banco, GestorUsuarios gestor) {
        //  Crear Clientes 
        Cliente c1 = new Cliente("Juan", "C-001", "Calle Villanueva");
        Cliente c2 = new Cliente("Ximena", "C-002", "Jiron Cusco");

        // Crear Empleados 
        Empleado e1 = new Empleado("Maria", "E-001", "Avenida Salomon");
        Empleado e2 = new Empleado("Carlos", "E-002", "Avenida Cayma");

        //  Registrar en Banco 
        banco.registrarCliente(c1);
        banco.registrarCliente(c2);
        banco.registrarEmpleado(e1);
        banco.registrarEmpleado(e2);

        // Crear Usuarios 
        UsuarioAdministrador admin = new UsuarioAdministrador("admin", "1234", true);
        UsuarioEmpleado ue1 = new UsuarioEmpleado("maria", "1234", e1);
        UsuarioEmpleado ue2 = new UsuarioEmpleado("carlos", "1234", e2);
        UsuarioCliente uc1 = new UsuarioCliente("juan", "1234", c1);
        UsuarioCliente uc2 = new UsuarioCliente("ximena", "1234", c2);

        // --- Registrar Usuarios en el Gestor ---
        gestor.registrarUsuario(admin);
        gestor.registrarUsuario(ue1);
        gestor.registrarUsuario(ue2);
        gestor.registrarUsuario(uc1);
        gestor.registrarUsuario(uc2);

        System.out.println("\n Datos iniciales cargados correctamente.\n");
    }

    // MENÚ PRINCIPAL

    public static void menuPrincipal(Scanner sc, Banco banco, GestorUsuarios gestor) {
        while (true) {
            System.out.println("=== SISTEMA BANCO ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int op = sc.nextInt();
            sc.nextLine(); 

            switch (op) {
                case 1 -> iniciarSesion(sc, banco, gestor);
                case 2 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // INICIO DE SESIÓN

    public static void iniciarSesion(Scanner sc, Banco banco, GestorUsuarios gestor) {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String clave = sc.nextLine();

        Usuario u = gestor.acceder(usuario, clave);

        if (u == null) return; 

        // Si accede correctamente:
        if (u instanceof UsuarioAdministrador admin) {
            menuAdministrador(sc, banco, gestor, admin);
        } else if (u instanceof UsuarioEmpleado emp) {
            menuEmpleado(sc, banco, emp, emp.getIdEmpleado());
        } else if (u instanceof UsuarioCliente cli) {
            menuCliente(sc, banco, cli, cli.getIdCliente());
        }
    }

    // Menú ADMINISTRADOR

    private static void menuAdministrador(Scanner sc, Banco banco, GestorUsuarios gestor, UsuarioAdministrador admin) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar empleado");
            System.out.println("3. Registrar cuenta a cliente");
            System.out.println("4. Modificar cliente (por ID)");
            System.out.println("5. Modificar empleado (por ID)");
            System.out.println("6. Eliminar cliente (por ID)");
            System.out.println("7. Eliminar empleado (por ID)");
            System.out.println("8. Mostrar usuarios");
            System.out.println("9. Mostrar clientes");
            System.out.println("10. Mostrar empleados");
            System.out.println("11. Mostrar transacciones");
            System.out.println("12. Registrar usuario");
            System.out.println("13. Eliminar usuario");
            System.out.println("14. Cambiar contraseña de usuario");
            System.out.println("15. Cambiar estado de usuario");
            System.out.println("16. Buscar usuario");
            System.out.println("0. Cerrar sesión");
            System.out.print("Opción: ");
            op = parseIntSafe(sc.nextLine(), -1);

            switch (op) {
            case 1 -> {
                System.out.print("Nombre: "); String n = sc.nextLine();
                System.out.print("ID: "); String id = sc.nextLine();
                System.out.print("Dirección: "); String d = sc.nextLine();
                banco.registrarCliente(new Cliente(n, id, d));
            }
            case 2 -> {
                System.out.print("Nombre: "); String n = sc.nextLine();
                System.out.print("ID: "); String id = sc.nextLine();
                System.out.print("Dirección: "); String d = sc.nextLine();
                banco.registrarEmpleado(new Empleado(n, id, d));
            }
            case 3 -> {
                System.out.print("ID cliente: "); String idC = sc.nextLine();
                Cliente c = banco.buscarCliente(idC);
                if (c == null) { System.err.println("Cliente no encontrado."); break;}
                System.out.print("ID cuenta: "); String idCuenta = sc.nextLine();
                banco.registrarCuenta(c, new Cuenta(idCuenta, true));
            }
            case 4 -> {
                System.out.print("ID cliente a modificar: "); String idMod = sc.nextLine();
                Cliente c = banco.buscarCliente(idMod);
                if (c == null) { System.err.println("Cliente no encontrado."); break;}
                System.out.print("Nuevo Nombre: "); String nuevoN = sc.nextLine();
                System.out.print("Nueva Dirección: "); String nuevaD = sc.nextLine();
                banco.modificarCliente(idMod, nuevoN, nuevaD);
                System.out.println("Se modifico al cliente: " + idMod);
            }
            case 5 -> {
                System.out.print("ID empleado a modificar: "); String idMod = sc.nextLine();
                Cliente c = banco.buscarCliente(idMod);
                if (c == null) { System.err.println("Empleadoe no encontrado."); break;}
                System.out.print("Nuevo Nombre: "); String nuevoN = sc.nextLine();
                System.out.print("Nueva Dirección: "); String nuevaD = sc.nextLine();
                banco.modificarEmpleado(idMod, nuevoN, nuevaD);
                System.out.println("Se modifico al empleado: " + idMod);
            }
            case 6 -> {
                System.out.print("ID cliente a eliminar: "); String idDel = sc.nextLine();
                banco.eliminarCliente(idDel);
                System.out.println("Se elimino al cliente: " + idDel);
            }
            case 7 -> {
                System.out.print("ID empleado a eliminar: "); String idDel = sc.nextLine();
                banco.eliminarEmpleado(idDel);
                System.out.println("Se elimino al empleado: " + idDel);
            }
            case 8 -> gestor.mostrarUsuarios();
            case 9 -> banco.mostrarClientes();
            case 10 -> banco.mostrarEmpleados();
            case 11 -> banco.mostrarTransacciones();
            case 12 -> {
                System.out.print("Nombre de usuario: "); String nombre = sc.nextLine();
                System.out.print("Contraseña: "); String clave = sc.nextLine();
                System.out.print("Tipo (cliente/empleado/admin)"); String tipo = sc.nextLine();
                Usuario nuevo = new Usuario(nombre, clave, tipo );
                gestor.registrarUsuario(nuevo);
            }
            case 13 -> {
                System.out.print("Nombre de usuario a eliminar: "); String nombre = sc.nextLine();
                gestor.eliminarUsuario(nombre);
            }
            case 14 -> {
                System.out.print("Usuario: "); String nombre = sc.nextLine();
                System.out.print("Nueva contraseña: "); String nueva = sc.nextLine();
                gestor.cambiarContraseña(nombre, nueva);
            }
            case 15 -> {
                System.out.print("Usuario: "); String nombre = sc.nextLine();
                System.out.print("Nuevo estado (true=Activo / false=Inactivo): "); boolean est = Boolean.parseBoolean(sc.nextLine());
                gestor.cambiarEstado(nombre, est);
            }
            case 16 -> {
                System.out.print("Usuario a buscar: "); String nombre = sc.nextLine();
                Usuario u = gestor.buscarUsuario(nombre);
                if (u != null) System.out.println("Encontrado: " + u);
                else System.out.println("Usuario no encontrado.");
            }

            case 0 -> System.out.println("Cerrando sesión...");
            default -> System.out.println("Opción inválida.");
            }
        }
    }


    // Menú EMPLEADO

    private static void menuEmpleado(Scanner sc, Banco banco, UsuarioEmpleado uemp, String idEmpleadoSesion) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n=== MENÚ EMPLEADO ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Modificar cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Registrar cuenta a cliente");
            System.out.println("5. Procesar depósito");
            System.out.println("6. Procesar retiro");
            System.out.println("7. Ver historial de una cuenta");
            System.out.println("8. Ver mis acciones");
            System.out.println("9. Ver lista de clientes");
            System.out.println("0. Cerrar sesión");
            System.out.print("Opción: ");
            op = parseIntSafe(sc.nextLine(), -1);

            switch (op) {
                case 1 -> {
                    System.out.print("Nombre: "); String n = sc.nextLine();
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Dirección: "); String d = sc.nextLine();
                    banco.registrarCliente(new Cliente(n, id, d));
                }
                case 2 -> {
                    System.out.print("ID cliente a modificar: "); String idMod = sc.nextLine();
                    Cliente c = banco.buscarCliente(idMod);
                    if (c == null) { System.err.println("Cliente no encontrado."); break;}
                    System.out.print("Nuevo Nombre: "); String nuevoN = sc.nextLine();
                    System.out.print("Nueva Dirección: "); String nuevaD = sc.nextLine();
                    banco.modificarCliente(idMod, nuevoN, nuevaD);
                    System.out.println("Se modifico al cliente: " + idMod);
                }
                case 3 -> {
                    System.out.print("ID cliente a eliminar: "); String idC = sc.nextLine();
                    banco.eliminarEmpleado(idC);
                    System.out.println("Implementa eliminarCliente en Banco para esta acción.");
                }
                case 4 -> {
                    System.out.print("ID cliente: "); String idC = sc.nextLine();
                    Cliente c = banco.buscarCliente(idC);
                    if (c == null) { System.err.println("Cliente no encontrado."); break;}
                    System.out.print("ID cuenta nueva: "); String idCuenta = sc.nextLine();
                    banco.registrarCuenta(c, new Cuenta(idCuenta, true));
                }
                case 5 -> {
                    System.out.print("ID cliente: "); String idC = sc.nextLine();
                    System.out.print("ID cuenta: "); String idCuenta = sc.nextLine();
                    System.out.print("Monto: "); double m = parseDoubleSafe(sc.nextLine(), -1);
                    if (m <= 0) { System.err.println("Monto inválido."); break; }
                    // Aquí pasamos el objeto Empleado de la sesión
                    Empleado empleadoSesion = uemp.getEmpleado();
                    if (empleadoSesion == null) {
                        System.err.println("No hay empleado asociado al usuario en sesión.");
                        break;
                    }
                    banco.procesarDeposito(idC, idCuenta, m, empleadoSesion);
                }
                case 6 -> {
                    System.out.print("ID cliente: "); String idC = sc.nextLine();
                    System.out.print("ID cuenta: "); String idCuenta = sc.nextLine();
                    System.out.print("Monto: "); double m = parseDoubleSafe(sc.nextLine(), -1);
                    if (m <= 0) { System.err.println("Monto inválido."); break; }

                    Empleado empleadoSesion = uemp.getEmpleado();
                    if (empleadoSesion == null) {
                    System.err.println("No hay empleado asociado al usuario en sesión.");
                    break;
                }

                banco.procesarRetiro(idC, idCuenta, m, empleadoSesion);
                }

                case 7 -> {
                    System.out.print("ID cuenta: "); String idCuenta = sc.nextLine();
                    banco.mostrarHistorialCuenta(idCuenta);
                }
                case 8 -> {
                    // mostrar acciones desde el objeto Empleado (si está disponible)
                    Empleado emp = uemp.getEmpleado();
                    if (emp != null) emp.mostrarAcciones();
                    else System.out.println("No hay empleado asociado al usuario (falta asignación).");
                }
                case 9 -> banco.mostrarClientes();
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // Menú CLIENTE

    private static void menuCliente(Scanner sc, Banco banco, UsuarioCliente ucli, String idClienteSesion) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Consultar mis datos");
            System.out.println("2. Consultar mi cuenta y saldo");
            System.out.println("3. Ver historial de mi cuenta");
            System.out.println("0. Cerrar sesión");
            System.out.print("Opción: ");
            op = parseIntSafe(sc.nextLine(), -1);

            switch (op) {
                case 1 -> {
                    Cliente c = banco.buscarCliente(idClienteSesion);
                    if (c == null) System.err.println("Cliente no encontrado.");
                    else c.mostrarInformacion();
                }
                case 2 -> {
                    Cliente c = banco.buscarCliente(idClienteSesion);
                    if (c == null || !c.tieneCuenta()) System.err.println("Cuenta no encontrada.");
                    else System.out.println("Saldo: " + c.getCuenta().getSaldo());
                }
                case 3 -> {
                    Cliente c = banco.buscarCliente(idClienteSesion);
                    if (c == null || !c.tieneCuenta()) System.err.println("Cuenta no encontrada.");
                    else banco.mostrarHistorialCuenta(c.getCuenta().getIdCuenta());
                }
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // Utilidades

    private static int parseIntSafe(String s, int defaultVal) {
        try { return Integer.parseInt(s.trim()); }
        catch (Exception e) { return defaultVal; }
    }

    private static double parseDoubleSafe(String s, double defaultVal) {
        try { return Double.parseDouble(s.trim()); }
        catch (Exception e) { return defaultVal; }
    }
}

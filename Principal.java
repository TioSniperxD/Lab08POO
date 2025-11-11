import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        GestorUsuarios gestor = new GestorUsuarios();

        //  INICIALIZAMOS DATOS

        Empleado emp1 = new Empleado("Carlos Ruiz", "E-001", "Oficina Principal");
        banco.registrarEmpleado(emp1);

        Cliente cli1 = new Cliente("Ana Gomez", "C-101", "Av. Siempre Viva 123");
        Cliente cli2 = new Cliente("Luis Paz", "C-102", "Calle Falsa 456");
        banco.registrarCliente(cli1);
        banco.registrarCliente(cli2);

        banco.registrarCuenta(cli1, new Cuenta("A-1001", true));
        banco.registrarCuenta(cli2, new Cuenta("S-2002", true));

        banco.procesarDeposito("C-101", "A-1001", 1000.0, "E-001");
        banco.procesarDeposito("C-102", "S-2002", 500.0, "E-001");

        gestor.registrarUsuario(new UsuarioAdministrador("admin", "1234", true));
        gestor.registrarUsuario(new UsuarioEmpleado("empleado", "2222", true));
        gestor.registrarUsuario(new UsuarioCliente("cliente", "1111", true));

        //  SISTEMA

        boolean salirSistema = false;

        while (!salirSistema) {

            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir del sistema");
            System.out.print("Opción: ");
            String opcion = sc.nextLine();

            if (opcion.equals("2")) {
                salirSistema = true;
                continue;
            }

            //  LOGIN

            Usuario usuarioActivo = null;
            while (usuarioActivo == null) {
                System.out.print("\nUsuario: ");
                String user = sc.nextLine();
                System.out.print("Clave: ");
                String pass = sc.nextLine();

                usuarioActivo = gestor.acceder(user, pass);

                if (usuarioActivo == null)
                    System.err.println("Acceso denegado.\n");
            }

            System.out.println("\n Bienvenido, " + usuarioActivo.getNombreUsuario());

            String tipo = usuarioActivo.getTipoUsuario().toLowerCase();

            // MENÚ POR TIPO DE USUARIO
            if (tipo.equals("administrador"))
                menuAdmin(sc, banco, gestor);

            if (tipo.equals("empleado"))
                menuEmpleado(sc, banco);

            if (tipo.equals("cliente"))
                menuCliente(sc, banco);

            // AL SALIR DEL MENÚ → volver al login
            System.out.println("\nSesión cerrada. Regresando al inicio...\n");
        }

        System.out.println("\nGracias por usar el sistema.");
        sc.close();
    }

    //               MENU ADMINISTRADOR

    private static void menuAdmin(Scanner sc, Banco banco, GestorUsuarios gestor) {

        int op = 0;
        while (op != 7) {

            System.out.println("\n MENU ADMINISTRADOR ");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Cambiar estado");
            System.out.println("5. Mostrar usuarios");
            System.out.println("6. Datos del banco");
            System.out.println("7. Cerrar sesión");
            System.out.print("Opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1:
                    System.out.print("Usuario nuevo: ");
                    String u = sc.nextLine();
                    System.out.print("Clave: ");
                    String c = sc.nextLine();
                    System.out.print("Tipo (cliente/empleado/admin): ");
                    String t = sc.nextLine();

                    if (t.equalsIgnoreCase("cliente"))
                        gestor.registrarUsuario(new UsuarioCliente(u, c, true));
                    else if (t.equalsIgnoreCase("empleado"))
                        gestor.registrarUsuario(new UsuarioEmpleado(u, c, true));
                    else if (t.equalsIgnoreCase("admin"))
                        gestor.registrarUsuario(new UsuarioAdministrador(u, c, true));
                    else
                        System.err.println("Tipo inválido.");
                    break;

                case 2:
                    System.out.print("Usuario a eliminar: ");
                    gestor.eliminarUsuario(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Usuario: ");
                    String us = sc.nextLine();
                    System.out.print("Nueva clave: ");
                    gestor.cambiarContraseña(us, sc.nextLine());
                    break;

                case 4:
                    System.out.print("Usuario: ");
                    String us2 = sc.nextLine();
                    System.out.print("Nuevo estado (true/false): ");
                    boolean estado = Boolean.parseBoolean(sc.nextLine());
                    gestor.cambiarEstado(us2, estado);
                    break;

                case 5:
                    gestor.mostrarUsuarios();
                    break;

                case 6:
                    banco.mostrarClientes();
                    banco.mostrarEmpleados();
                    banco.mostrarTransacciones();
                    break;
            }
        }
    }

    //                   MENU EMPLEADO

    private static void menuEmpleado(Scanner sc, Banco banco) {

        int op = 0;
        while (op != 6) {

            System.out.println("\n   MENU EMPLEADO  ");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Depósito");
            System.out.println("4. Retiro");
            System.out.println("5. Mostrar clientes");
            System.out.println("6. Cerrar sesión");
            System.out.print("Opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1:
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Dirección: ");
                    String d = sc.nextLine();
                    banco.registrarCliente(new Cliente(n, id, d));
                    break;

                case 2:
                    System.out.print("ID cliente: ");
                    String idc = sc.nextLine();
                    Cliente cl = banco.buscarCliente(idc);

                    if (cl == null) {
                        System.err.println("Cliente no encontrado.");
                        break;
                    }

                    System.out.print("ID cuenta nueva: ");
                    String idcu = sc.nextLine();
                    banco.registrarCuenta(cl, new Cuenta(idcu, true));
                    break;

                case 3:
                    System.out.print("ID cliente: ");
                    String c1 = sc.nextLine();
                    System.out.print("ID cuenta: ");
                    String c2 = sc.nextLine();
                    System.out.print("Monto: ");
                    double m = Double.parseDouble(sc.nextLine());
                    banco.procesarDeposito(c1, c2, m, "E-001");
                    break;

                case 4:
                    System.out.print("ID cliente: ");
                    String r1 = sc.nextLine();
                    System.out.print("ID cuenta: ");
                    String r2 = sc.nextLine();
                    System.out.print("Monto: ");
                    double mr = Double.parseDouble(sc.nextLine());
                    banco.procesarRetiro(r1, r2, mr, "E-001");
                    break;

                case 5:
                    banco.mostrarClientes();
                    break;
            }
        }
    }

    //  MENU CLIENTE

    private static void menuCliente(Scanner sc, Banco banco) {

        int op = 0;
        while (op != 4) {

            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Ver historial");
            System.out.println("3. Depósito");
            System.out.println("4. Cerrar sesión");
            System.out.print("Opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1:
                    System.out.print("ID cliente: ");
                    String c1 = sc.nextLine();
                    System.out.print("ID cuenta: ");
                    String c2 = sc.nextLine();
                    Cuenta cuenta = banco.buscarCuentaDeCliente(c1, c2);

                    if (cuenta == null)
                        System.err.println("Cuenta no encontrada.");
                    else
                        System.out.println("Saldo: " + cuenta.getSaldo());
                    break;

                case 2:
                    System.out.print("ID cuenta: ");
                    banco.mostrarHistorialCuenta(sc.nextLine());
                    break;

                case 3:
                    System.out.print("ID cliente: ");
                    String d1 = sc.nextLine();
                    System.out.print("ID cuenta: ");
                    String d2 = sc.nextLine();
                    System.out.print("Monto: ");
                    double m = Double.parseDouble(sc.nextLine());
                    banco.procesarDeposito(d1, d2, m, "E-001");
                    break;
            }
        }
    }
}
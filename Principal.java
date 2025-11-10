import java.util.Scanner;
public class Principal {
    public static void main(String[] args) {
        
        // Inicializamos el banco y el scanner
        Banco miBanco = new Banco();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        
        //PRE-CARGAMOS DATOS PARA QUE EL SISTEMA SEA FUNCIONAL
        miBanco.registrarEmpleado("Carlos Ruiz", "E-001", "Oficina Principal");
        miBanco.registrarCliente("Ana Gómez", "C-101", "Av. Siempre Viva 123");
        miBanco.registrarCliente("Luis Paz", "C-102", "Calle Falsa 456");
        miBanco.registrarCuenta("C-101", "A-1001", true); // Cuenta para Ana
        miBanco.registrarCuenta("C-102", "S-2002", false); // Cuenta para Luis
        miBanco.generarDeposito("C-101", "A-1001", 1000.0, "E-001"); // Saldo inicial Ana
        miBanco.generarDeposito("C-102", "S-2002", 500.0, "E-001"); // Saldo inicial Luis
        System.out.println();
        System.out.println("Sistema de Banco - DATOS PRE-CARGADOS");
        System.out.println();

        // Bucle principal del menú
        while (!salir) {
            System.out.println("\nMENÚ PRINCIPAL DEL BANCO");
            System.out.println("1. Realizar Depósito");
            System.out.println("2. Realizar Retiro");
            System.out.println("3. Registrar Nuevo Cliente");
            System.out.println("4. Registrar Nueva Cuenta a Cliente");
            System.out.println("5. Ver Historial de Transacciones de Cuenta");
            System.out.println("6. Ver Acciones de Empleado");
            System.out.println("7. Ver Todos los Clientes y Cuentas");
            System.out.println("8. Ver Todos los Empleados");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    // REALIZAR DEPÓSITO
                    System.out.println("\nDEPÓSITO");
                    System.out.print("Ingrese ID del Cliente (ej: C-101): ");
                    String idClienteDep = sc.nextLine();
                    System.out.print("Ingrese ID de la Cuenta (ej: A-1001): ");
                    String idCuentaDep = sc.nextLine();
                    System.out.print("Ingrese Monto a depositar: ");
                    double montoDep = sc.nextDouble();
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese ID del Empleado (ej: E-001): ");
                    String idEmpDep = sc.nextLine();
                    
                    miBanco.generarDeposito(idClienteDep, idCuentaDep, montoDep, idEmpDep);
                    break;

                case 2:
                    //REALIZAR RETIRO
                    System.out.println("\nRETIRO");
                    System.out.print("Ingrese ID del Cliente (ej: C-101): ");
                    String idClienteRet = sc.nextLine();
                    System.out.print("Ingrese ID de la Cuenta (ej: A-1001): ");
                    String idCuentaRet = sc.nextLine();
                    System.out.print("Ingrese Monto a retirar: ");
                    double montoRet = sc.nextDouble();
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese ID del Empleado (ej: E-001): ");
                    String idEmpRet = sc.nextLine();
                    
                    miBanco.generarRetiro(idClienteRet, idCuentaRet, montoRet, idEmpRet);
                    break;

                case 3:
                    //REGISTRAR CLIENTE
                    System.out.println("\nREGISTRAR NUEVO CLIENTE");
                    System.out.print("Ingrese Nombre completo: ");
                    String nombreCliente = sc.nextLine();
                    System.out.print("Ingrese ID (Cédula/RUC): ");
                    String idCliente = sc.nextLine();
                    System.out.print("Ingrese Dirección: ");
                    String dirCliente = sc.nextLine();
                    
                    miBanco.registrarCliente(nombreCliente, idCliente, dirCliente);
                    break;

                case 4:
                    //REGISTRAR CUENTA
                    System.out.println("\nREGISTRAR NUEVA CUENTA");
                    System.out.print("Ingrese ID del Cliente existente: ");
                    String idClienteCuenta = sc.nextLine();
                    System.out.print("Ingrese el nuevo ID de Cuenta: ");
                    String idCuentaNueva = sc.nextLine();
                    
                    miBanco.registrarCuenta(idClienteCuenta, idCuentaNueva, true); // true = tipo (puedes mejorarlo)
                    break;

                case 5:
                    //VER HISTORIAL DE CUENTA
                    System.out.println("\nHISTORIAL DE TRANSACCIONES DE CUENTA");
                    System.out.print("Ingrese el ID de la Cuenta a consultar: ");
                    String idCuentaHist = sc.nextLine();
                    
                    miBanco.historialTransacciones(idCuentaHist);
                    break;

                case 6:
                    //VER ACCIONES DE EMPLEADO
                    System.out.println("\nACCIONES POR EMPLEADO");
                    System.out.print("Ingrese el ID del Empleado a consultar: ");
                    String idEmpHist = sc.nextLine();
                    
                    miBanco.historialTransaccionesEmpleado(idEmpHist);
                    break;

                case 7:
                    //VER CLIENTES Y CUENTAS
                    System.out.println("\nLISTADO DE CLIENTES Y CUENTAS");
                    miBanco.mostrarClienteCuentas();
                    break;
                
                case 8:
                    //VER EMPLEADOS
                    System.out.println("\nLISTADO DE EMPLEADOS");
                    miBanco.mostrarEmpleados();
                    break;

                case 9:
                    // SALIR
                    salir = true;
                    System.out.println("Gracias por usar el sistema bancario. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
        
        sc.close(); 
    }
}

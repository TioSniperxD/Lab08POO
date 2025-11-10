import java.util.ArrayList;

public class Banco {
    // Atributos (agregación con otras clases)
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<ClienteCuenta> listaClienteCuenta; // Nombre corregido
    private ArrayList<Transaccion> listaTransacciones;

    
    // Constructor 
    public Banco() {
        listaClientes = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        listaClienteCuenta = new ArrayList<>(); // Nombre corregido
        listaTransacciones = new ArrayList<>();
    }

    //Registra un nuevo cliente en el banco
    public void registrarCliente(String nombre, String id, String direccion) {
        Cliente nuevo = new Cliente(nombre, id, direccion);
        listaClientes.add(nuevo);
        System.out.println("Cliente registrado: " + nombre);
    }

    /** Registra un nuevo empleado */
    public void registrarEmpleado(String nombre, String id, String direccion) {
        Empleado nuevo = new Empleado(nombre, id, direccion);
        listaEmpleados.add(nuevo);
        System.out.println("Empleado registrado: " + nombre);
    }

    //Asocia una nueva cuenta a un cliente existente
    public void registrarCuenta(String idCliente, String idCuenta, boolean tipoCuenta) {
        Cliente clienteEncontrado = buscarCliente(idCliente);
        
        if (clienteEncontrado != null) {
            Cuenta nuevaCuenta = new Cuenta(idCuenta, tipoCuenta); 
            ClienteCuenta nuevaRelacion = new ClienteCuenta(clienteEncontrado, nuevaCuenta);
            
            listaClienteCuenta.add(nuevaRelacion); // Error de tipeo corregido
            
            System.out.println("Cuenta registrada para cliente: " + clienteEncontrado.getNombre());
        } else {
            System.out.println("Error: cliente no encontrado");
        }
    }
    // Genera un depósito en una cuenta específica
    public void generarDeposito(String idCliente, String idCuenta, double monto, String idEmpleado) {
        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente != null && empleado != null && cuenta != null) {
            Deposito dep = new Deposito(); 
            dep.movimiento(monto, cuenta);

            Transaccion transaccion = new Transaccion(idCliente, idCuenta, monto, "Depósito");
            listaTransacciones.add(transaccion);

            empleado.agregarAccion("Depósito de " + monto + " en cuenta " + idCuenta); 
            
            System.out.println("Depósito realizado correctamente. Nuevo saldo: " + cuenta.getSaldo());
        } else
            System.out.println("Error en los datos del depósito");
    }

    // Genera un retiro en una cuenta si hay saldo suficiente 
    public void generarRetiro(String idCliente, String idCuenta, double monto, String idEmpleado) {
        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente != null && empleado != null && cuenta != null) {
            if (cuenta.getSaldo() >= monto) {
                Retiro ret = new Retiro();
                ret.movimiento(monto, cuenta); // movimiento ahora resta el saldo

                Transaccion transaccion = new Transaccion(idCliente, idCuenta, -monto, "Retiro");
                listaTransacciones.add(transaccion);

                empleado.agregarAccion("Retiro de " + monto + " en cuenta " + idCuenta);
                System.out.println("Retiro realizado correctamente. Nuevo saldo: " + cuenta.getSaldo());
            } else 
                System.out.println("Fondos insuficientes para el retiro. Saldo: " + cuenta.getSaldo());
        } else 
            System.out.println("Error en los datos del retiro");
    }

    // Muestra todas las transacciones realizadas por una cuenta
    public void historialTransacciones(String idCuenta) {
        System.out.println("\nHistorial de transacciones de la cuenta " + idCuenta + ":");
        boolean encontradas = false;
        for (int i = 0; i < listaTransacciones.size(); i++) {
            Transaccion t = listaTransacciones.get(i);
            if (t.getIdCuenta().equals(idCuenta)) {
                System.out.println(t);
                encontradas = true;
            }
        }
         if (!encontradas) {
            System.out.println("No hay transacciones para esta cuenta.");
        }
    }

    // Muestra las acciones registradas por un empleado
    public void historialTransaccionesEmpleado(String idEmpleado) {
        Empleado emp = buscarEmpleado(idEmpleado);
        if (emp != null) {
            System.out.println("\nAcciones del empleado " + emp.getNombre() + ":");
            emp.mostrarAcciones();
        } else 
            System.out.println("Empleado no encontrado");
    }

    // MÉTODOS DE BÚSQUEDA
    private Cliente buscarCliente(String id) {
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private Empleado buscarEmpleado(String id) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado e = listaEmpleados.get(i);
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    private Cuenta buscarCuentaDeCliente(String idCliente, String idCuenta) {
        for (int i = 0; i < listaClienteCuenta.size(); i++) {
            ClienteCuenta cc = listaClienteCuenta.get(i);
            if (cc.getCliente().getId().equals(idCliente) &&
                cc.getCuenta().getIdCuenta().equals(idCuenta)) {
                return cc.getCuenta();
            }
        }
        return null;
    }

    // MÉTODOS PARA MOSTRAR LISTAR
    public void mostrarClientes() {
        System.out.println("\nLos clientes registrados son:");
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            System.out.println("- " + c.getNombre() + " (ID: " + c.getId() + ")");
        }
    }

    public void mostrarEmpleados() {
        System.out.println("\nLos Empleados registrados son:");
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado e = listaEmpleados.get(i);
            System.out.println("- " + e.getNombre() + " (ID: " + e.getId() + ")");
        }
    }

    public void mostrarClienteCuentas() {
        System.out.println("\nLos Clientes y sus cuentas correspondientes son:");
        for (int i = 0; i < listaClienteCuenta.size(); i++) {
            ClienteCuenta cc = listaClienteCuenta.get(i);
            System.out.println(cc);
        }
    }
    
    public void mostrarTransacciones() {
        System.out.println("\nLas Transacciones registradas son:");
        for (int i = 0; i < listaTransacciones.size(); i++) {
            System.out.println(listaTransacciones.get(i));
        }
    }
}

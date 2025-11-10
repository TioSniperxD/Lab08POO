import java.util.ArrayList;

public class Banco {

    // Listas del sistema
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<ClienteCuenta> listaClienteCuenta;
    private ArrayList<Transaccion> listaTransacciones;

    //Constructor
    public Banco() {
        listaClientes = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        listaClienteCuenta = new ArrayList<>();
        listaTransacciones = new ArrayList<>();
    }

    // REGISTROS

    public void registrarCliente(Cliente nuevo) {
        if (!nuevo.validarDatos()) {
            System.err.println("Cliente no válido.");
            return;
        }
        if (buscarCliente(nuevo.getId()) != null) {
            System.err.println("ID de cliente repetido.");
            return;
        }
        listaClientes.add(nuevo);
        System.out.println("Cliente registrado: " + nuevo.getNombre());
    }

    public void registrarEmpleado(Empleado nuevo) {
        if (!nuevo.validarDatos()) {
            System.err.println("Empleado no válido.");
            return;
        }
        if (buscarEmpleado(nuevo.getId()) != null) {
            System.err.println("ID de empleado repetido.");
            return;
        }
        listaEmpleados.add(nuevo);
        System.out.println("Empleado registrado: " + nuevo.getNombre());
    }

    public void registrarCuenta(Cliente cliente, Cuenta cuenta) {
        ClienteCuenta relacion = new ClienteCuenta(cliente, cuenta);

        if (!relacion.validarRelacion()) {
            System.err.println("Relación cliente-cuenta inválida.");
            return;
        }

        listaClienteCuenta.add(relacion);
        cliente.setCuenta(cuenta);

        System.out.println("Cuenta registrada para: " + cliente.getNombre());
    }

    // TRANSACCIONES

    public void procesarDeposito(String idCliente, String idCuenta, double monto, String idEmpleado) {

        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente == null || empleado == null || cuenta == null) {
            System.err.println("Datos no válidos para depósito.");
            return;
        }

        Deposito dep = new Deposito(idCliente, idCuenta, monto);

        if (!dep.procesar(cuenta)) {
            System.err.println("Depósito no realizado.");
            return;
        }

        listaTransacciones.add(dep);
        empleado.agregarAccion("Depósito de " + monto + " en cuenta " + idCuenta);

        System.out.println("Depósito exitoso. Nuevo saldo: " + cuenta.getSaldo());
    }

    public void procesarRetiro(String idCliente, String idCuenta, double monto, String idEmpleado) {

        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente == null || empleado == null || cuenta == null) {
            System.err.println("Datos no válidos para retiro.");
            return;
        }

        Retiro ret = new Retiro(idCliente, idCuenta, monto);

        if (!ret.procesar(cuenta)) {
            System.err.println("Retiro no realizado.");
            return;
        }

        listaTransacciones.add(ret);
        empleado.agregarAccion("Retiro de " + monto + " en cuenta " + idCuenta);

        System.out.println("Retiro exitoso. Nuevo saldo: " + cuenta.getSaldo());
    }

    // BÚSQUEDAS

    public Cliente buscarCliente(String id) {
        for (Cliente c : listaClientes) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    public Empleado buscarEmpleado(String id) {
        for (Empleado e : listaEmpleados) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public Cuenta buscarCuentaDeCliente(String idCliente, String idCuenta) {
        for (ClienteCuenta cc : listaClienteCuenta) {
            if (cc.getCliente().getId().equals(idCliente) &&
                cc.getCuenta().getIdCuenta().equals(idCuenta)) {
                return cc.getCuenta();
            }
        }
        return null;
    }

    // HISTORIALES

    public void mostrarHistorialCuenta(String idCuenta) {
        boolean hay = false;

        for (Transaccion t : listaTransacciones) {
            if (t.getIdCuenta().equals(idCuenta)) {
                System.out.println(t);
                hay = true;
            }
        }

        if (!hay) System.out.println("Sin transacciones.");
    }

    public void mostrarAccionesEmpleado(String idEmpleado) {
        Empleado emp = buscarEmpleado(idEmpleado);
        if (emp == null) {
            System.err.println("Empleado no encontrado.");
            return;
        }
        emp.mostrarAcciones();
    }

    // MOSTRAR LISTAS

    public void mostrarClientes() {
        for (Cliente c : listaClientes) System.out.println(c);
    }

    public void mostrarEmpleados() {
        for (Empleado e : listaEmpleados) System.out.println(e);
    }

    public void mostrarClienteCuentas() {
        for (ClienteCuenta cc : listaClienteCuenta) System.out.println(cc);
    }

    public void mostrarTransacciones() {
        for (Transaccion t : listaTransacciones) System.out.println(t);
    }
}

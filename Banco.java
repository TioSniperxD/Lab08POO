import java.util.ArrayList;

public class Banco {

    // Listas del sistema
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<ClienteCuenta> listaClienteCuenta;
    private ArrayList<Transaccion> listaTransacciones;

    // Constructor
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

    public void procesarDeposito(String idCliente, String idCuenta, double monto, Empleado empleadoSesion) {

        Cliente cliente = buscarCliente(idCliente);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente == null || empleadoSesion == null || cuenta == null) {
            System.err.println("Datos no válidos para depósito.");
            return;
        }

        Deposito dep = new Deposito(idCliente, idCuenta, monto, empleadoSesion.getId());

        if (!dep.procesar(cuenta)) {
            System.err.println("Depósito no realizado.");
            return;
        }

        listaTransacciones.add(dep);
        empleadoSesion.agregarAccion("Depósito de " + monto + " en cuenta " + idCuenta);
        System.out.println("Depósito exitoso. Nuevo saldo: " + cuenta.getSaldo());
    }

    public void procesarRetiro(String idCliente, String idCuenta, double monto, Empleado empleadoSesion) {

        Cliente cliente = buscarCliente(idCliente);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente == null || empleadoSesion == null || cuenta == null) {
            System.err.println("Datos no válidos para retiro.");
            return;
        }

        Retiro ret = new Retiro(idCliente, idCuenta, monto, empleadoSesion.getId());

        if (!ret.procesar(cuenta)) {
            System.err.println("Retiro no realizado.");
            return;
        }

        listaTransacciones.add(ret);
        empleadoSesion.agregarAccion("Retiro de " + monto + " en cuenta " + idCuenta);
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
        if (listaClientes == null || listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE CLIENTES ===");
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
    }

    public void mostrarEmpleados() {
        if (listaEmpleados == null || listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE EMPLEADOS ===");
        for (Empleado e : listaEmpleados) {
            System.out.println(e);
        }
    }

    public void mostrarTransacciones() {
        if (listaTransacciones == null || listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
            return;
        }

        System.out.println("\n=== HISTORIAL DE TRANSACCIONES ===");
        for (Transaccion t : listaTransacciones) {
            System.out.println(t);
        }
    }

    // ELIMINAR

    public boolean eliminarCliente(String idCliente) {
        Cliente c = buscarCliente(idCliente);
        if (c == null) {
            System.err.println("Cliente no encontrado.");
            return false;
        }
        listaClientes.remove(c);
        System.out.println("Cliente eliminado: " + idCliente);
        return true;
    }

    public boolean eliminarEmpleado(String idEmpleado) {
        Empleado e = buscarEmpleado(idEmpleado);
        if (e == null) {
            System.err.println("Empleado no encontrado.");
            return false;
        }
        listaEmpleados.remove(e);
        System.out.println("Empleado eliminado: " + idEmpleado);
        return true;
    }

    // MODIFICAR

    public boolean modificarCliente(String idCliente, String nuevoNombre, String nuevaDireccion) {
        Cliente c = buscarCliente(idCliente);
        if (c == null) {
            System.err.println("Cliente no encontrado.");
            return false;
        }

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            c.setNombre(nuevoNombre);
        }
        if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
            c.setDireccion(nuevaDireccion);
        }

        System.out.println("Cliente modificado correctamente: " + c.getId());
        return true;
    }

    public boolean modificarEmpleado(String idEmpleado, String nuevoNombre, String nuevaDireccion) {
        Empleado e = buscarEmpleado(idEmpleado);
        if (e == null) {
            System.err.println("Empleado no encontrado.");
            return false;
        }

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            e.setNombre(nuevoNombre);
        }
        if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
            e.setDireccion(nuevaDireccion);
        }

        System.out.println("Empleado modificado correctamente: " + e.getId());
        return true;
    }
}
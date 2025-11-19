import java.util.*;

public class Banco {

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<ClienteCuenta> listaClienteCuenta;
    private ArrayList<Transaccion> listaTransacciones;
    //CONSTRUCTOR
    public Banco() {
        listaClientes = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        listaClienteCuenta = new ArrayList<>();
        listaTransacciones = new ArrayList<>();
    }

    //REGISTROS
    //CLIENTE
    public void registrarCliente(Cliente nuevo) {
        if (esClienteValido(nuevo)) {
            if (existeCliente(nuevo.getId())) {
                System.out.println("Ya existe un cliente con ese ID");
            } else {
                listaClientes.add(nuevo);
                System.out.println("Cliente registrado: " + nuevo.getNombre());
            }
        } else {
            System.out.println("Cliente no válido");
        }
    }
    //EMPLEADO
    public void registrarEmpleado(Empleado nuevo) {
        if (esEmpleadoValido(nuevo)) {
            if (existeEmpleado(nuevo.getId())) {
                System.out.println("Ya existe un empleado con ese ID");
            } else {
                listaEmpleados.add(nuevo);
                System.out.println("Empleado registrado: " + nuevo.getNombre());
            }
        } else {
            System.out.println("Empleado no válido");
        }
    }
    //CUENTA
    public void registrarCuenta(Cliente cliente, Cuenta cuenta) {
        if (esRegistroCuentaValido(cliente, cuenta)) {
            ClienteCuenta relacion = new ClienteCuenta(cliente, cuenta);
            listaClienteCuenta.add(relacion);
            cliente.setCuenta(cuenta);
            System.out.println("Cuenta registrada para: " + cliente.getNombre());
        } else {
            System.out.println("No se puede registrar la cuenta");
        }
    }

    //TRANSACCION
    //DEPOSITO
    public void procesarDeposito(String idCliente, String idCuenta, double monto, Empleado empleadoSesion) {
        if (esOperacionValida(idCliente, idCuenta, empleadoSesion)) {
            Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);
            if (cuenta != null) {
                Deposito deposito = new Deposito(idCliente, idCuenta, monto, empleadoSesion.getId());
                if (deposito.procesar(cuenta)) {
                    registrarTransaccion(deposito, empleadoSesion, "Depósito de " + monto);
                    System.out.println("Depósito exitoso. Nuevo saldo: " + cuenta.getSaldo());
                } else {
                    System.out.println("No se pudo realizar el depósito");
                }
            } else {
                System.out.println("Cuenta no encontrada");
            }
        } else {
            System.out.println("No se puede procesar el depósito");
        }
    }
    //RETIRO
    public void procesarRetiro(String idCliente, String idCuenta, double monto, Empleado empleadoSesion) {
        if (esOperacionValida(idCliente, idCuenta, empleadoSesion)) {
            Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);
            if (cuenta != null) {
                Retiro retiro = new Retiro(idCliente, idCuenta, monto, empleadoSesion.getId());
                if (retiro.procesar(cuenta)) {
                    registrarTransaccion(retiro, empleadoSesion, "Retiro de " + monto);
                    System.out.println("Retiro exitoso. Nuevo saldo: " + cuenta.getSaldo());
                } else {
                    System.out.println("No se pudo realizar el retiro");
                }
            } else {
                System.out.println("Cuenta no encontrada");
            }
        } else {
            System.out.println("No se puede procesar el retiro");
        }
    }

    //BUSQUEDAS
    //BUSCAR CLIENTE
    public Cliente buscarCliente(String id) {
        Cliente clienteEncontrado = null;
        for (Cliente c : listaClientes) {
            if (c.getId().equals(id)) {
                clienteEncontrado = c;
                break;
            }
        }
        return clienteEncontrado;
    }
    //BUSCAR EMPLEADO
    public Empleado buscarEmpleado(String id) {
        Empleado empleadoEncontrado = null;
        for (Empleado e : listaEmpleados) {
            if (e.getId().equals(id)) {
                empleadoEncontrado = e;
                break;
            }
        }
        return empleadoEncontrado;
    }
    //BUSCAR CUENTA
    public Cuenta buscarCuentaDeCliente(String idCliente, String idCuenta) {
        Cuenta cuentaEncontrada = null;
        for (ClienteCuenta cc : listaClienteCuenta) {
            if (cc.getCliente().getId().equals(idCliente) && 
                cc.getCuenta().getIdCuenta().equals(idCuenta)) {
                cuentaEncontrada = cc.getCuenta();
                break;
            }
        }
        return cuentaEncontrada;
    }

    //HISTORIAL
    //MOSTRAR EL HISTORIAL DE LAS CUENTAS
    public void mostrarHistorialCuenta(String idCuenta) {
        boolean encontrado = false;
        for (Transaccion t : listaTransacciones) {
            if (t.getIdCuenta().equals(idCuenta)) {
                System.out.println(t);
                encontrado = true;
            }
        }
        if(!encontrado) System.out.println("No hay transacciones para esta cuenta");
        
    }
    //MOSTRAR EL HISTORIAL DE ACCIONES DEL EMPLEADO
    public void mostrarAccionesEmpleado(String idEmpleado) {
        Empleado empleado = buscarEmpleado(idEmpleado);
        if (empleado != null) {
            empleado.mostrarAcciones();
        } else {
            System.out.println("Empleado no encontrado");
        }
    }

    //MOSTRAR LOS CLIENTES
    public void mostrarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            System.out.println("CLIENTES:");
            for (Cliente c : listaClientes) {
                System.out.println(c);
            }
        }
    }
    //MOSTRAR LOS EMPLEADOS
    public void mostrarEmpleados() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println("EMPLEADOS:");
            for (Empleado e : listaEmpleados) {
                System.out.println(e);
            }
        }
    }
    //MOSTRAR LAS TRANSACCIONES
    public void mostrarTransacciones() {
        if (listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas");
        } else {
            System.out.println("TRANSACCIONES");
            for (Transaccion t : listaTransacciones) {
                System.out.println(t);
            }
        }
    }

    //ELIMINAR CLIENTE
    public void eliminarCliente(String idCliente) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente != null) {
            listaClientes.remove(cliente);
            System.out.println("Cliente eliminado: " + idCliente);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }
    //ELIMINAR EMPLEADO
    public void eliminarEmpleado(String idEmpleado) {
        Empleado empleado = buscarEmpleado(idEmpleado);
        if (empleado != null) {
            listaEmpleados.remove(empleado);
            System.out.println("Empleado eliminado: " + idEmpleado);
        } else {
            System.out.println("Empleado no encontrado");
        }
    }

    //MODIFICAR CLIENTE
    public void modificarCliente(String idCliente, String nuevoNombre, String nuevaDireccion) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente != null) {
            if (nuevoNombre != null) {
                cliente.setNombre(nuevoNombre);
            }
            if (nuevaDireccion != null) {
                cliente.setDireccion(nuevaDireccion);
            }
            System.out.println("Cliente modificado: " + idCliente);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }
    //MODIFICAR EMPLEADO
    public void modificarEmpleado(String idEmpleado, String nuevoNombre, String nuevaDireccion) {
        Empleado empleado = buscarEmpleado(idEmpleado);
        if (empleado != null) {
            if (nuevoNombre != null) {
                empleado.setNombre(nuevoNombre);
            }
            if (nuevaDireccion != null) {
                empleado.setDireccion(nuevaDireccion);
            }
            System.out.println("Empleado modificado: " + idEmpleado);
        } else {
            System.out.println("Empleado no encontrado");
        }
    }

    //VALIDACIONES
    //VALIDAR EL CLIENTE
    private boolean esClienteValido(Cliente cliente) {
        if (cliente != null && cliente.validarDatos()) {
            return true;
        } else {
            return false;
        }
    }
    //VALIDAR EL EMPLEADO
    private boolean esEmpleadoValido(Empleado empleado) {
        if (empleado != null && empleado.validarDatos()) {
            return true;
        } else {
            return false;
        }
    }
    //VALIDAR SI YA EXISTE EL CLIENTE
    private boolean existeCliente(String idCliente) {
        if (buscarCliente(idCliente) != null) {
            return true;
        } else {
            return false;
        }
    }
    //VALIDAR SI YA EXISTE EL EMPLEADO
    private boolean existeEmpleado(String idEmpleado) {
        if (buscarEmpleado(idEmpleado) != null) {
            return true;
        } else {
            return false;
        }
    }
    //VALIDAR LA CUENTA
    private boolean esRegistroCuentaValido(Cliente cliente, Cuenta cuenta) {
        if (cliente != null && cuenta != null) {
            return true;
        } else {
            return false;
        }
    }
    //VALIDAR DATOS DE LA TRANSACCION
    private boolean esOperacionValida(String idCliente, String idCuenta, Empleado empleado) {
        if (idCliente != null && idCuenta != null && empleado != null) {
            return true;
        } else {
            return false;
        }
    }
    //REGISTRAR LA TRANSACCION
    private void registrarTransaccion(Transaccion transaccion, Empleado empleado, String accion) {
        listaTransacciones.add(transaccion);
        empleado.agregarAccion(accion);
    }

}

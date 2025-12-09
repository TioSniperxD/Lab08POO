package LAB10;

import java.util.*;

public class Banco {
    // ATRIBUTOS DE LISTAS
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<ClienteCuenta> listaClienteCuenta;
    private ArrayList<Transaccion> listaTransacciones;
    //CONSTRUCTOR
    public Banco() {
        // inicializa todas las listas vacías
        listaClientes = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        listaClienteCuenta = new ArrayList<>();
        listaTransacciones = new ArrayList<>();
    }

    //REGISTROS CON VALIDACIÓN
    //CLIENTE
    // Registra un cliente si cumple validaciones básicas
    public void registrarCliente(Cliente nuevo) {
        if (esClienteValido(nuevo)) {
            if (existeCliente(nuevo.getId())) { // Verifica si ya existe un cliente con el mismo ID
                System.out.println("Ya existe un cliente con ese ID");
            } else {
                listaClientes.add(nuevo); // Agrega al cliente a listaClientes
                System.out.println("Cliente registrado: " + nuevo.getNombre());
            }
        } else {
            System.out.println("Cliente no válido");
        }
    }
    //EMPLEADO
    // Registra un empleado si sus datos son válidos
    public void registrarEmpleado(Empleado nuevo) {
        if (esEmpleadoValido(nuevo)) {
            if (existeEmpleado(nuevo.getId())) { // Verifica si ya existe un empleado con el mismo ID
                System.out.println("Ya existe un empleado con ese ID");
            } else {
                listaEmpleados.add(nuevo); // Agrega al empleado a listaEmpleados
                System.out.println("Empleado registrado: " + nuevo.getNombre());
            }
        } else {
            System.out.println("Empleado no válido");
        }
    }
    //CUENTA
    // Asocia una cuenta a un cliente (relación ClienteCuenta)
    public void registrarCuenta(Cliente cliente, Cuenta cuenta) {
        if (esRegistroCuentaValido(cliente, cuenta)) {
            ClienteCuenta relacion = new ClienteCuenta(cliente, cuenta); //Crea un nuevo objeto de la clase ClienteCuenta
            listaClienteCuenta.add(relacion); // Agrega la nueva relacion a la listaClienteCuenta
            cliente.setCuenta(cuenta); // Se enlaza la cuenta al cliente
            System.out.println("Cuenta registrada para: " + cliente.getNombre());
        } else {
            System.out.println("No se puede registrar la cuenta");
        }
    }

    //TRANSACCION
    //PROCESAR DEPÓSITO
    public void procesarDeposito(String idCliente, String idCuenta, double monto, Empleado empleadoSesion) {
        // Valida datos de la operación y permisos básicos
        if (esOperacionValida(idCliente, idCuenta, empleadoSesion)) {
            Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta); //Busca la cuenta del cliente con los ID
            if (cuenta != null) {
                Deposito deposito = new Deposito(idCliente, idCuenta, monto, empleadoSesion.getId()); // Crea un nuevo objeto de la clase Deposito
                if (deposito.procesar(cuenta)) { // Procesa el deposito
                    registrarTransaccion(deposito, empleadoSesion, "Depósito de " + monto);  // Guarda el movimiento en el historial del empleado
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
    //PROCESAR RETIRO
    public void procesarRetiro(String idCliente, String idCuenta, double monto, Empleado empleadoSesion) {
        // Valida datos de la operación y permisos básicos
        if (esOperacionValida(idCliente, idCuenta, empleadoSesion)) {
            Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta); //Busca la cuenta del cliente con los ID
            if (cuenta != null) {
                Retiro retiro = new Retiro(idCliente, idCuenta, monto, empleadoSesion.getId()); // Crea un nuevo objeto de la clase Retiro
                if (retiro.procesar(cuenta)) {
                    registrarTransaccion(retiro, empleadoSesion, "Retiro de " + monto); // Guarda el movimiento en el historial del empleado
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
    public Cliente buscarCliente(String id) { // Busca un cliente por ID
        Cliente clienteEncontrado = null;
        for (Cliente c : listaClientes) { // Busca un cliente recorriendo la listaClientes
            if (c.getId().equals(id)) { // Compara ID del cliente
                clienteEncontrado = c;
                break; // Se detiene al encontrarlo
            }
        }
        return clienteEncontrado;
    }
    //BUSCAR EMPLEADO
    public Empleado buscarEmpleado(String id) { // Busca un empleado por ID
        Empleado empleadoEncontrado = null;
        for (Empleado e : listaEmpleados) {  // Busca un empleado recorriendo la listaEmpleados
            if (e.getId().equals(id)) { // Compara ID del empelado
                empleadoEncontrado = e;
                break; // Se detiene al encontrarlo
            }
        }
        return empleadoEncontrado;
    }
    //BUSCAR CUENTA
    public Cuenta buscarCuentaDeCliente(String idCliente, String idCuenta) { // Busca una cuenta asociada a un cliente
        Cuenta cuentaEncontrada = null;
        for (ClienteCuenta cc : listaClienteCuenta) {
            if (cc.getCliente().getId().equals(idCliente) && 
                cc.getCuenta().getIdCuenta().equals(idCuenta)) {// Verifica si los datos ingresados corresponden a la cuenta y cliente
                cuentaEncontrada = cc.getCuenta(); // Obtiene la cuenta
                break; // Se detiene al encontrarlo
            }
        }
        return cuentaEncontrada;
    }

    //HISTORIAL
    //MOSTRAR EL HISTORIAL DE LAS CUENTAS
    public void mostrarHistorialCuenta(String idCuenta) { // Muestra todas las transacciones de una cuenta específica
        boolean encontrado = false;
        for (Transaccion t : listaTransacciones) {
            if (t.getIdCuenta().equals(idCuenta)) { // Verifica si la cuenta 
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
            empleado.mostrarAcciones(); // Llama método del empleado
        } else {
            System.out.println("Empleado no encontrado");
        }
    }

    //MOSTRAR LOS CLIENTES
    public void mostrarClientes() { // Muestra todos los clientes registrados
        if (listaClientes.isEmpty()) { // Valida si hay registros
            System.out.println("No hay clientes registrados");
        } else {
            System.out.println("CLIENTES:");
            for (Cliente c : listaClientes) {
                System.out.println(c); // Imprime cada cliente
            }
        }
    }
    //MOSTRAR LOS EMPLEADOS
    public void mostrarEmpleados() { // Muestra todos los empleados
        if (listaEmpleados.isEmpty()) { // Valida si hay registros
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println("EMPLEADOS:");
            for (Empleado e : listaEmpleados) {
                System.out.println(e); // Imprime cada empleado
            }
        }
    }
    //MOSTRAR LAS TRANSACCIONES
    public void mostrarTransacciones() { // Muestra todas las transacciones realizadas
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
        Cliente cliente = buscarCliente(idCliente); // Busca cliente
        if (cliente != null) {
            listaClientes.remove(cliente); // Lo elimina de la listaClientes
            System.out.println("Cliente eliminado: " + idCliente);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }
    //ELIMINAR EMPLEADO
    public void eliminarEmpleado(String idEmpleado) {
        Empleado empleado = buscarEmpleado(idEmpleado); // Busca empleado
        if (empleado != null) {
            listaEmpleados.remove(empleado); // Lo elimina de la listaEmpleados
            System.out.println("Empleado eliminado: " + idEmpleado);
        } else {
            System.out.println("Empleado no encontrado");
        }
    }

    //MODIFICAR CLIENTE
    // Edita nombre o dirección de un cliente
    public void modificarCliente(String idCliente, String nuevoNombre, String nuevaDireccion) {
        Cliente cliente = buscarCliente(idCliente); // Busca cliente
        if (cliente != null) {
            if (nuevoNombre != null) {
                cliente.setNombre(nuevoNombre); // Cambia nombre

            }
            if (nuevaDireccion != null) {
                cliente.setDireccion(nuevaDireccion); // Cambia dirección
            }
            System.out.println("Cliente modificado: " + idCliente);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }
    //MODIFICAR EMPLEADO
    // Edita datos de un empleado
    public void modificarEmpleado(String idEmpleado, String nuevoNombre, String nuevaDireccion) {
        Empleado empleado = buscarEmpleado(idEmpleado); 
        if (empleado != null) {
            if (nuevoNombre != null) {
                empleado.setNombre(nuevoNombre); // Cambia nombre
            }
            if (nuevaDireccion != null) {
                empleado.setDireccion(nuevaDireccion); // Cambia dirección
            }
            System.out.println("Empleado modificado: " + idEmpleado);
        } else {
            System.out.println("Empleado no encontrado");
        }
    }

    //VALIDACIONES
    //VALIDAR EL CLIENTE
    private boolean esClienteValido(Cliente cliente) {
        if (cliente != null && cliente.validarDatos()) { // Valida que el cliente no sea null y tenga datos correctos
            return true;
        } else {
            return false;
        }
    }
    //VALIDAR EL EMPLEADO
    private boolean esEmpleadoValido(Empleado empleado) {
        if (empleado != null && empleado.validarDatos()) { // Valida que el empleado tenga datos correctos
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
        if (cliente != null && cuenta != null) { // Verifica que no sea nulo
            return true;
        } else {
            return false;
        }
    }
    //VALIDAR DATOS DE LA TRANSACCION
    private boolean esOperacionValida(String idCliente, String idCuenta, Empleado empleado) { // Guarda la transacción y registra la acción del empleado
        if (idCliente != null && idCuenta != null && empleado != null) {
            return true;
        } else {
            return false;
        }
    }
    //REGISTRAR LA TRANSACCION
    private void registrarTransaccion(Transaccion transaccion, Empleado empleado, String accion) {
        listaTransacciones.add(transaccion); // Agrega al historial 
        empleado.agregarAccion(accion); // Registra en el empleado
    }

}

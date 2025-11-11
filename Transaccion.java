import java.util.Date;

public class Transaccion {
    // Atributos
    protected String idCliente;
    protected String idCuenta;
    protected double monto;
    protected String tipo;
    protected String fecha;
    protected Cuenta cuenta;
    protected String idEmpleado; // 🔹 Nuevo atributo opcional

    // Constructor
    public Transaccion(String idCliente, String idCuenta, double monto, String tipo, String idEmpleado) {
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = generarFecha();
        this.cuenta = null;
        this.idEmpleado = idEmpleado; // 🔹 Se registra quién realizó la transacción
    }

    public Transaccion(String idCliente, String idCuenta, double monto, String tipo) {
    this(idCliente, idCuenta, monto, tipo, "N/A");
    }

    // MÉTODOS
    // Genera la fecha de la transacción
    private String generarFecha() {
        return new Date().toString().substring(4, 19);
    }

    // Valida los datos básicos
    public boolean datosValidos() {
        if (monto <= 0) {
            System.err.println("Monto inválido.");
            return false;
        }
        if (idCliente == null || idCliente.trim().isEmpty()) {
            System.err.println("ID del cliente vacío.");
            return false;
        }
        if (idCuenta == null || idCuenta.trim().isEmpty()) {
            System.err.println("ID de la cuenta vacío.");
            return false;
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            System.err.println("Tipo inválido.");
            return false;
        }
        return true;
    }

    // Getters
    public String getIdCuenta() {
        return idCuenta;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    // toString
    @Override
    public String toString() {
        return tipo + " | Cliente: " + idCliente +
               " | Cuenta: " + idCuenta +
               " | Monto: " + monto +
               " | Empleado: " + (idEmpleado.isEmpty() ? "No registrado" : idEmpleado) +
               " | Fecha: " + fecha;
    }
}
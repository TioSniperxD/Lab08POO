package LAB10;

import java.util.Date;

public class Transaccion {
    protected String idCliente;
    protected String idCuenta;
    protected String idEmpleado;
    protected double monto;
    protected String tipo;
    protected String fecha;
    protected Cuenta cuenta;

    //CONSTRUCTOR CON EMPLEADO
    public Transaccion(String idCliente, String idCuenta, double monto, String tipo, String idEmpleado) {
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.idEmpleado = idEmpleado;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = generarFecha();
        this.cuenta = null;
    }

    //CONSTRUCTOR SIN EMPLEADO
    public Transaccion(String idCliente, String idCuenta, double monto, String tipo) {
        this(idCliente, idCuenta, monto, tipo, "N/A");
    }

    //GENERA LA FECHA (Apr 17 12:34:56)
    private String generarFecha() {
        return new Date().toString().substring(4, 19);
    }

    //VALIDAR DATOS
    public boolean datosValidos() {
        if (monto <= 0) {
            System.out.println("Monto inválido.");
            return false;
        }
        if (idCliente == null || idCliente.trim().isEmpty()) {
            System.out.println("ID del cliente vacío.");
            return false;
        }
        if (idCuenta == null || idCuenta.trim().isEmpty()) {
            System.out.println("ID de la cuenta vacío.");
            return false;
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            System.out.println("Tipo inválido.");
            return false;
        }
        return true;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    @Override
    public String toString() {
        return tipo + " | Cliente: " + idCliente +
               " | Cuenta: " + idCuenta +
               " | Monto: " + monto +
               " | Empleado: " + (idEmpleado.isEmpty() ? "No registrado" : idEmpleado) +
               " | Fecha: " + fecha;
    }
}
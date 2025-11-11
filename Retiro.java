public class Retiro extends Transaccion {

    // Constructor 
    public Retiro(String idCliente, String idCuenta, double monto) {
        super(idCliente, idCuenta, monto, "Retiro");
    }

    // Constructor sobrecargadp
    public Retiro(String idCliente, String idCuenta, double monto, String idEmpleado) {
        super(idCliente, idCuenta, monto, "Retiro", idEmpleado);
    }
    //MÉTODOS
    // Valifación de cuenta
    private boolean cuentaValida(Cuenta cuenta) {
        if (cuenta == null) {
            System.err.println("Cuenta no válida.");
            return false;
        }
        return true;
    }
    // Valida los fondos de una cuenta
    private boolean fondosSuficientes(Cuenta cuenta) {
        if (cuenta.getSaldo() < monto) {
            System.err.println("Saldo insuficiente.");
            return false;
        }
        return true;
    }
    // Acción aplicar retiro
    private void aplicarRetiro(Cuenta cuenta) {
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        this.cuenta = cuenta;
    }
    // Procesa el retiro
    public boolean procesar(Cuenta cuenta) {
        if (!datosValidos()) return false;
        if (!cuentaValida(cuenta)) return false;
        if (!fondosSuficientes(cuenta)) return false;
        aplicarRetiro(cuenta);
        return true;
    }
    //toString
    @Override
    public String toString() {
        return "Retiro -> " + super.toString();
    }
}
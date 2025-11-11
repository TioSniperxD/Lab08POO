public class Deposito extends Transaccion {

    // Constructor
    public Deposito(String idCliente, String idCuenta, double monto) {
        super(idCliente, idCuenta, monto, "Depósito");
    }

    // Constructor sobrecargad
    public Deposito(String idCliente, String idCuenta, double monto, String idEmpleado) {
        super(idCliente, idCuenta, monto, "Depósito", idEmpleado);
    }
    //MÉTODOS
    // Valida la cuenta
    private boolean cuentaValida(Cuenta cuenta) {
        if (cuenta == null) {
            System.err.println("Cuenta no válida.");
            return false;
        }
        return true;
    }

    // Aplica el cambio en el saldo
    private void aplicarDeposito(Cuenta cuenta) {
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        this.cuenta = cuenta;
    }

    // Procesa el depósito
    public boolean procesar(Cuenta cuenta) {
        if (!datosValidos()) return false;
        if (!cuentaValida(cuenta)) return false;
        aplicarDeposito(cuenta);
        return true;
    }
    //toString
    @Override
    public String toString() {
        return "Depósito -> " + super.toString();
    }
}
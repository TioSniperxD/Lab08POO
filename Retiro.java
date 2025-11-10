public class Retiro extends Transaccion {

    public Retiro(String idCliente, String idCuenta, double monto) {
        super(idCliente, idCuenta, monto, "Retiro");
    }

    private boolean cuentaValida(Cuenta cuenta) {
        if (cuenta == null) {
            System.err.println("Cuenta no válida.");
            return false;
        }
        return true;
    }

    private boolean fondosSuficientes(Cuenta cuenta) {
        if (cuenta.getSaldo() < monto) {
            System.err.println("Saldo insuficiente.");
            return false;
        }
        return true;
    }

    private void aplicarRetiro(Cuenta cuenta) {
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        this.cuenta = cuenta;
    }

    public boolean procesar(Cuenta cuenta) {
        if (!datosValidos()) return false;
        if (!cuentaValida(cuenta)) return false;
        if (!fondosSuficientes(cuenta)) return false;
        aplicarRetiro(cuenta);
        return true;
    }

    @Override
    public String toString() {
        return "Retiro -> " + super.toString();
    }
}
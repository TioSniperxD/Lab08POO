public class Deposito extends Transaccion {

    //CONSTRUCTOR SIN EMPLEADO
    public Deposito(String idCliente, String idCuenta, double monto) {
        super(idCliente, idCuenta, monto, "Depósito");
    }

    //CONSTRUCTOR CON EMPLEADO
    public Deposito(String idCliente, String idCuenta, double monto, String idEmpleado) {
        super(idCliente, idCuenta, monto, "Depósito", idEmpleado);
    }

    //VERIFICA LA CUENTA
    private boolean cuentaValida(Cuenta cuenta) {
        if (cuenta == null) {
            System.out.println("Cuenta no válida.");
            return false;
        }
        return true;
    }

    //APLICA EL DEPOSITO
    private void aplicarDeposito(Cuenta cuenta) {
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        this.cuenta = cuenta;
    }

    //APLICA EL DEPOSITO SI TODO ES CORRECTO
    public boolean procesar(Cuenta cuenta) {
        if (!datosValidos()) return false;
        if (!cuentaValida(cuenta)) return false;

        aplicarDeposito(cuenta);
        return true;
    }

    @Override
    public String toString() {
        return "Depósito -> " + super.toString();
    }
}
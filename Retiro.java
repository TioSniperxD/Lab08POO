public class Retiro extends Transaccion {

    //CONSTRUCTOR SIN EMPLEADO 
    public Retiro(String idCliente, String idCuenta, double monto) {
        super(idCliente, idCuenta, monto, "Retiro");
    }

    //CONSTRUCTOR CON EMPLEADO
    public Retiro(String idCliente, String idCuenta, double monto, String idEmpleado) {
        super(idCliente, idCuenta, monto, "Retiro", idEmpleado);
    }

    //VERIFICAR LA CUENTA
    private boolean cuentaValida(Cuenta cuenta) {
        if (cuenta == null) {
            System.out.println("Cuenta no válida.");
            return false;
        }
        return true;
    }

    //VALIDAR FONDOS
    private boolean fondosSuficientes(Cuenta cuenta) {
        if (cuenta.getSaldo() < monto) {
            System.out.println("Saldo insuficiente.");
            return false;
        }
        return true;
    }

    //APLICA EL RETIRO
    private void aplicarRetiro(Cuenta cuenta) {
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        this.cuenta = cuenta;
    }

    //PROCESA EL RETIRO SI TODO ES VALIDO
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
import java.util.ArrayList;

public class Cuenta {

    private String idCuenta;
    private double saldo;
    private ArrayList<Transaccion> transacciones;

    // Constructor principal
    public Cuenta(String idCuenta, double saldo) {
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.transacciones = new ArrayList<>();
    }

    // Constructor sobrecargado
    public Cuenta(String idCuenta, boolean tipoCuenta) {
        this.idCuenta = idCuenta;
        this.saldo = 0.0;
        this.transacciones = new ArrayList<>();
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método agregarTransaccion 
    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public void mostrarHistorial() {
        if (transacciones.isEmpty()) {
            System.out.println("Sin transacciones registradas.");
        } else {
            for (Transaccion t : transacciones) {
                System.out.println(t);
            }
        }
    }
    //toString
    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta='" + idCuenta + '\'' +
                ", saldo=" + saldo +
                ", transacciones=" + transacciones.size() +
                '}';
    }
}

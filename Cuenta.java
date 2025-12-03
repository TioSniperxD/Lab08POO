
import java.util.ArrayList;

public class Cuenta {

    private String idCuenta;
    private double saldo;
    private ArrayList<Transaccion> transacciones;

    //CONSTRUCTOR 
    public Cuenta(String idCuenta, double saldo) {
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.transacciones = new ArrayList<>();
    }

    //CONSTRUCTOR SOBRECARGADO
    public Cuenta(String idCuenta, boolean tipoCuenta) {
        this.idCuenta = idCuenta;
        this.saldo = 0.0;
        this.transacciones = new ArrayList<>();
    }

    // Getters y Setters
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

    //AGREGA LA TRANSSACCION
    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    //MUESTRA EL HISTORIAL DE LAS TRANSACCIONES
    public void mostrarHistorial() {
        if (transacciones.isEmpty()) {
            System.out.println("Sin transacciones registradas.");
        } else {
            for (Transaccion t : transacciones) {
                System.out.println(t);
            }
        }
    }

    public String mostrarHistorialString() {
        if (transacciones.isEmpty()) {
            return "Sin transacciones registradas.";
        } else {
            StringBuilder sb = new StringBuilder();

            for (Transaccion t : transacciones) {
                sb.append(t.toString()).append("\n");
            }

            return sb.toString();
        }
    }

    @Override
    public String toString() {
        return "Cuenta{"
                + "idCuenta='" + idCuenta + '\''
                + ", saldo=" + saldo
                + ", transacciones=" + transacciones.size()
                + '}';
    }
}

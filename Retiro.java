//Hereda de Transaccion
public class Retiro extends Transaccion{

    //Constructor vacío que necesita Banco.java
    public Retiro() {
        // Llama al constructor de Transaccion con valores nulos/cero
        super(null, null, null, 0, null, null, null);
    }

    //Constructor con empleado
    public Retiro(String idCliente, String idEmpleado, Cuenta cuenta, double monto, String idTransaccion, String fecha, String hora) {
        super(idCliente, idEmpleado, cuenta, monto, idTransaccion, fecha, hora);
    }
    //Constructor sin empleado
    public Retiro(String idCliente, String idTransaccion, Cuenta cuenta, double monto, String fecha, String hora) {
        super(idCliente, idTransaccion, cuenta,monto, fecha, hora);
    }
    
    //Movimiento de retiro
    @Override
    public void movimiento(double monto, Cuenta cuenta) {
        super.movimiento(monto, cuenta);
        // System.out.println("Retiro realizado con éxito en la cuenta.");
    }
    
    @Override
    public String toString() {
        return "Retiro -> " + super.toString();
    }
}

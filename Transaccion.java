public class Transaccion {
    //Atributos protegidos
    protected String idCliente;
    protected String idEmpleado;
    protected Cuenta cuenta;
    protected double monto;
    protected String idTransaccion;
    protected String fecha;
    protected String hora;


    // Atributos para el constructor simple que usa Banco
    protected String idCuenta; 
    protected String tipo;     

    //Transaccion que actua el empleado
    public Transaccion(String idCliente, String idEmpleado, Cuenta cuenta, double monto, String idTransaccion, String fecha, String hora){
        this.idCliente=idCliente;
        this.idEmpleado=idEmpleado;
        this.cuenta=cuenta;
        this.monto=monto;
        this.idTransaccion=idTransaccion;
        this.fecha=fecha;
        this.hora=hora;
    }
    //Transaccion que no actua el empleado
    public Transaccion(String idCliente, String idTransaccion, Cuenta cuenta, double monto, String fecha, String hora){
        this.idCliente=idCliente;
        this.cuenta=cuenta;
        this.monto=monto;
        this.idTransaccion=idTransaccion;
        this.fecha=fecha;
        this.hora=hora;
    }
    
    //Constructor que necesita Banco.java para el historial
    public Transaccion(String idCliente, String idCuenta, double monto, String tipo) {
        this.idCliente = idCliente;
        this.idCuenta = idCuenta; // Guardamos el ID
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = new java.util.Date().toString().substring(4, 19); // Fecha simple
        this.cuenta = null; 
    }

    // Getter para que Banco.historialTransacciones funcione
    public String getIdCuenta() {
        if (cuenta != null) {
            return cuenta.getIdCuenta();
        }
        return this.idCuenta; // Devuelve el ID guardado
    }

    //Movimiento que heredaran las clases Deposito y Retiro
    public void movimiento(double monto, Cuenta cuenta){
        if(monto <= 0){
            System.out.println("Movimiento no realizado, introducir un monto correcto");
            return;
        }
        if(cuenta == null){
            System.out.println("Cuenta no valida, ingresar una cuenta valida");
            return;
        }
        
        // Aplica el saldo en la cuenta
        if (this instanceof Deposito) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
        } else if (this instanceof Retiro) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
        }
        
        this.monto = monto;
        this.cuenta = cuenta;
        // System.out.println("Movimiento Realizado"); // Se comenta para evitar duplicidad
    }
    
    //ToString
    @Override
    public String toString() {
        // Para el constructor simple de Banco
        if (tipo != null) {
            return "Transaccion [Tipo: " + tipo + 
                   ", idCliente=" + idCliente + 
                   ", idCuenta=" + idCuenta + 
                   ", monto=" + monto + 
                   ", fecha=" + fecha + "]";
        }
        
        // Para los constructores completos
        return "Transaccion{" +
                "\nidCliente='" + idCliente + 
                "\nidEmpleado='" + idEmpleado +  
                "\nCuenta=" + (cuenta != null ? cuenta.getIdCuenta() : "null") +
                "\nMonto=" + monto +
                '}';
    }
}

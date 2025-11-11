public class ClienteCuenta {
    // Atributos
    private Cliente cliente;
    private Cuenta cuenta;
    // Constructor
    public ClienteCuenta(Cliente cliente, Cuenta cuenta) {
        this.cliente = cliente;
        this.cuenta = cuenta;
    }
    //Setter y Getter
    public Cliente getCliente() {
        return cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta nuevaCuenta) {
        if (nuevaCuenta == null) {
            System.err.println("Cuenta inválida.");
            return;
        }
        this.cuenta = nuevaCuenta;
    }
    //MÉTODOS
    // Valida relación cliente-cuenta
    public boolean validarRelacion() {
        if (cliente == null) {
            System.err.println("Cliente nulo en ClienteCuenta.");
            return false;
        }
        if (cuenta == null) {
            System.err.println("Cuenta nula en ClienteCuenta.");
            return false;
        }
        return true;
    }
    // toString 
    @Override
    public String toString() {
        return "Cliente: " + cliente.getNombre() +
               " | Cuenta: " + cuenta.getIdCuenta() +
               " | Saldo: " + cuenta.getSaldo();
    }
}

package LAB10;

public class ClienteCuenta {
    // Atributos
    private final Cliente cliente;
    private final Cuenta cuenta;

    // Constructor
    public ClienteCuenta(Cliente cliente, Cuenta cuenta) {
        if (cliente == null) {
            throw new IllegalArgumentException("El Cliente no puede ser nulo en una relación ClienteCuenta.");
        }
        if (cuenta == null) {
            throw new IllegalArgumentException("La Cuenta no puede ser nula en una relación ClienteCuenta.");
        }
        
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    // --- GETTERS ---
    public Cliente getCliente() {
        return cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNombre() +
               " | Cuenta: " + cuenta.getIdCuenta() +
               " | Saldo: " + cuenta.getSaldo();
    }
}
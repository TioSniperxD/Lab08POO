// Esta clase sirve como "puente" para registrar qué cliente es dueño de qué cuenta
public class ClienteCuenta {
    private Cliente cliente;
    private Cuenta cuenta;

    //Constructor que usa la clase Banco
    public ClienteCuenta(Cliente cliente, Cuenta cuenta) {
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    // Getters que Banco.java necesita para buscar
    public Cliente getCliente() {
        return cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }
    
    @Override
    public String toString() {
        return "Relacion -> Cliente: " + cliente.getNombre() + 
               " --- Cuenta: " + cuenta.getIdCuenta() +
               " (Saldo: " + cuenta.getSaldo() + ")";
    }
}


public class UsuarioCliente extends Usuario {
    //ATRIBUTOS
    private Cliente cliente;
    private String idCliente;

    // Constructor con objeto Cliente
    public UsuarioCliente(String nombreUsuario, String contraseña, Cliente cliente) {
        super(nombreUsuario, contraseña, "cliente");
        this.cliente = cliente;
        this.idCliente = cliente != null ? cliente.getId() : null;
    }

    // Constructor sobrecargado con ID
    public UsuarioCliente(String nombreUsuario, String contraseña, String idCliente) {
        super(nombreUsuario, contraseña, "cliente");
        this.idCliente = idCliente;
        this.cliente = null;
    }
    //Getter y Setter
    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente != null) this.idCliente = cliente.getId();
    }

    public String getIdCliente() {
        return idCliente != null ? idCliente :
               (cliente != null ? cliente.getId() : "N/A");
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("1. Consultar mis datos");
        System.out.println("2. Consultar mi cuenta y saldo");
        System.out.println("3. Ver historial de mi cuenta");
    }
    // toString
    @Override
    public String toString() {
        return "UsuarioCliente -> Usuario: " + nombreUsuario +
               " | Cliente: " + (cliente != null ? cliente.getNombre() : "Sin asignar") +
               " | ID Cliente: " + getIdCliente() +
               " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
public class UsuarioCliente extends Usuario {

    // Atributos
    private String idCliente;

    // Constructor
    public UsuarioCliente(String nombreUsuario, String contraseña, String idCliente) {
        super(nombreUsuario, contraseña, "cliente");
        this.idCliente = idCliente;
    }

    // Getter y Setter
    public String getIdCliente() { 
        return idCliente; 
    }
    public void setIdCliente(String idCliente) { 
        this.idCliente = idCliente; 
    }

    // MÉTODOS
    // Mostrar los permisos para el usuario Cliente
    @Override
    public void mostrarPermisos() {
        System.out.println("- Consultar saldo");
        System.out.println("- Ver historial de movimientos");
    }

    //toString
    @Override
    public String toString() {
        return "UsuarioCliente: " + nombreUsuario + 
        " | ID Cliente: " + idCliente + 
        " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
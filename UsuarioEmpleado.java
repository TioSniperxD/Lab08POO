public class UsuarioEmpleado extends Usuario {
    // Atributo
    private String idEmpleado;
    // Constructor
    public UsuarioEmpleado(String nombreUsuario, String contraseña, String idEmpleado) {
        super(nombreUsuario, contraseña, "empleado");
        this.idEmpleado = idEmpleado;
    }
    public UsuarioEmpleado(String nombreUsuario, String contraseña, boolean activo) {
        super(nombreUsuario, contraseña, "Empleado");
        this.activo = activo;
}
    // Getter y Setter
    public String getIdEmpleado() { 
        return idEmpleado; 
    }
    public void setIdEmpleado(String idEmpleado) { 
        this.idEmpleado = idEmpleado; 
    }
    // MÉTODOS
    // Mostrar los permisos para Usuario Empleado
    @Override
    public void mostrarPermisos() {
        System.out.println("- Registrar clientes");
        System.out.println("- Registrar cuentas");
        System.out.println("- Realizar depósitos");
        System.out.println("- Realizar retiros");
        System.out.println("- Ver transacciones");
    }

    // toString
    @Override
    public String toString() {
        return "UsuarioEmpleado: " + nombreUsuario + 
        " | ID Empleado: " + idEmpleado + 
        " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
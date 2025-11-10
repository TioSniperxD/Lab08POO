public class UsuarioAdministrador extends Usuario {
    // Constructor
    public UsuarioAdministrador(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña, "administrador");
    }

    // MÉTODOS
    // Mostrar los permisos para Usuario Administrador
    @Override
    public void mostrarPermisos() {
        System.out.println("- Registrar clientes");
        System.out.println("- Registrar empleados");
        System.out.println("- Registrar cuentas");
        System.out.println("- Eliminar empleados");
        System.out.println("- Eliminar clientes");
        System.out.println("- Ver toda la información del sistema");
    }
    // toString
    @Override
    public String toString() {
        return "UsuarioAdministrador: " + nombreUsuario + 
        " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
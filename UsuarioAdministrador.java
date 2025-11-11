public class UsuarioAdministrador extends Usuario {

    public UsuarioAdministrador(String nombreUsuario, String contraseña, String tipoUsuario) {
        super(nombreUsuario, contraseña, tipoUsuario);
    }

    // Constructor simplificado que asigna el tipo automáticamente
    public UsuarioAdministrador(String nombreUsuario, String contraseña, boolean activo) {
        super(nombreUsuario, contraseña, "Administrador");
        this.activo = activo;
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
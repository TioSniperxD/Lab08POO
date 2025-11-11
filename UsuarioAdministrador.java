public class UsuarioAdministrador extends Usuario {

    // Constructor estándar 
    public UsuarioAdministrador(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña, "administrador");
    }

    // Constructor sobrecargado
    public UsuarioAdministrador(String nombreUsuario, String contraseña, boolean activo) {
        super(nombreUsuario, contraseña, "administrador");
        this.activo = activo;
    }

    // Mostrar permisos del administrador
    @Override
    public void mostrarPermisos() {
        System.out.println("- Registrar clientes");
        System.out.println("- Registrar empleados");
        System.out.println("- Registrar cuentas");
        System.out.println("- Eliminar empleados");
        System.out.println("- Eliminar clientes");
        System.out.println("- Modificar datos");
        System.out.println("- Ver toda la información del sistema");
    }

    @Override
    public String toString() {
        return "UsuarioAdministrador -> Usuario: " + nombreUsuario +
               " | Rol: Administrador" +
               " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
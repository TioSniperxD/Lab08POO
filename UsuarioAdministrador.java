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
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar empleado");
            System.out.println("3. Registrar cuenta a cliente");
            System.out.println("4. Modificar cliente (por ID)");
            System.out.println("5. Modificar empleado (por ID)");
            System.out.println("6. Eliminar cliente (por ID)");
            System.out.println("7. Eliminar empleado (por ID)");
            System.out.println("8. Mostrar usuarios");
            System.out.println("9. Mostrar clientes");
            System.out.println("10. Mostrar empleados");
            System.out.println("11. Mostrar transacciones");
            System.out.println("12. Registrar usuario");
            System.out.println("13. Eliminar usuario");
            System.out.println("14. Cambiar contraseña de usuario");
            System.out.println("15. Cambiar estado de usuario");
            System.out.println("16. Buscar usuario");
    }

    @Override
    public String toString() {
        return "UsuarioAdministrador -> Usuario: " + nombreUsuario +
               " | Rol: Administrador" +
               " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
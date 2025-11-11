public class UsuarioEmpleado extends Usuario {
    // Atributos
    private String idEmpleado;
    private Empleado empleado;

    // Constructor con objeto Empleado 
    public UsuarioEmpleado(String nombreUsuario, String contraseña, Empleado empleado) {
        super(nombreUsuario, contraseña, "empleado");
        this.empleado = empleado;
        this.idEmpleado = empleado != null ? empleado.getId() : "N/A";
    }

    // Constructor sobrecargado con iD
    public UsuarioEmpleado(String nombreUsuario, String contraseña, String idEmpleado) {
        super(nombreUsuario, contraseña, "empleado");
        this.idEmpleado = idEmpleado;
    }

    // Getters y setters
    public String getIdEmpleado() {
        return idEmpleado != null ? idEmpleado :
               (empleado != null ? empleado.getId() : "N/A");
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        if (empleado != null)
            this.idEmpleado = empleado.getId();
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("- Registrar clientes");
        System.out.println("- Registrar cuentas");
        System.out.println("- Realizar depósitos");
        System.out.println("- Realizar retiros");
        System.out.println("- Ver transacciones");
    }
    //toString
    @Override
    public String toString() {
        return "UsuarioEmpleado -> Usuario: " + nombreUsuario +
               " | Empleado: " + (empleado != null ? empleado.getNombre() : "Sin asignar") +
               " | ID Empleado: " + getIdEmpleado() +
               " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
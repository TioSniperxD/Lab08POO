public class Usuario {
    // Atributos
    protected String nombreUsuario;
    protected String contraseña;
    protected boolean activo;
    protected String tipoUsuario;
    // Constructor
    public Usuario(String nombreUsuario, String contraseña, String tipoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.activo = true;
    }

    // Setters y Getters
    public String getNombreUsuario() {
        return nombreUsuario; 
    }
    public void setNombreUsuario(String nombreUsuario) { 
        this.nombreUsuario = nombreUsuario; 
    }

    public String getContraseña() { 
        return contraseña; 
    }
    public void setContraseña(String contraseña) { 
        this.contraseña = contraseña; 
    }

    public boolean getActivo() { 
        return activo; 
    }
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }

    public String getTipoUsuario() { 
        return tipoUsuario; 
    }
    public void setTipoUsuario(String tipoUsuario) { 
        this.tipoUsuario = tipoUsuario;
    }

    //MÉTODOS
    // Verificar acceso según el tipo de usuario
    public boolean validarAcceso(String usuarioIngresado, String claveIngresada) {
        if (!activo) return false;
        if (!nombreUsuario.equals(usuarioIngresado)) return false;
        if (!contraseña.equals(claveIngresada)) return false;
        return true;
    }

    // Muestra los datos de Usuario
    public void mostrarDatos() {
        System.out.println(toString());
    }

    public void mostrarPermisos() {
        System.out.println("Sin permisos específicos.");
    }

    //toString
    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + 
        " | Tipo: " + tipoUsuario + 
        " | Estado: " + (activo ? "Activo" : "Inactivo");
    }
}
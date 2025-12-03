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

    // Valida nombreUsuario (solo letras)
    public boolean validarNombreUsuario() {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            System.err.println("Usuario vacío.");
            return false;
        }

        if (nombreUsuario.length() < 3) {
            System.err.println("El usuario debe tener mínimo 3 caracteres.");
            return false;
        }

        for (int i = 0; i < nombreUsuario.length(); i++) {
            char c = nombreUsuario.charAt(i);
            boolean esLetra = (c >= 'A' && c <= 'Z') ||
                              (c >= 'a' && c <= 'z');

            if (!esLetra ) {
                System.err.println("El usuario solo puede contener letras");
                return false;
            }
        }

        return true;
    }

    // Validar contraseña (mínimo 4 caracteres)
    public boolean validarContraseña() {
        if (contraseña == null || contraseña.trim().isEmpty()) {
            System.err.println("Contraseña vacía.");
            return false;
        }
        if (contraseña.length() < 4) {
            System.err.println("La contraseña debe tener mínimo 4 caracteres.");
            return false;
        }
        return true;
    }

    // Validar tipo de usuario
    public boolean validarTipo() {
        if (tipoUsuario == null) {
            System.err.println("Tipo de usuario nulo.");
            return false;
        }
        if (!tipoUsuario.equalsIgnoreCase("Administrador") &&
            !tipoUsuario.equalsIgnoreCase("Empleado") &&
            !tipoUsuario.equalsIgnoreCase("Cliente")) {

            System.err.println("Tipo de usuario inválido.");
            return false;
        }
        return true;
    }

    // Validar todos los datos
    public boolean validarDatos() {
        if(validarNombreUsuario() && validarContraseña() && validarTipo());
        return true;
    }

    // Muestra los datos de Usuario
    public void mostrarDatos() {
        System.out.println(toString());
    }
    // Muestra los permisos de Usuario
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
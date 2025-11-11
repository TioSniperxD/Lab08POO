public class Cliente extends Persona {
    //Atributo
    private Cuenta cuenta;
    // Constructor
    public Cliente(String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        this.cuenta = null;
    }
    //Constructor Sobrecargado
    public Cliente(String nombre, String id, String direccion, Cuenta cuenta) {
        super(nombre, id, direccion);
        this.cuenta = cuenta;
    }

    //Getter y setter
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    // Setter seguro para nombre
    public boolean setNombreSeguro(String nuevoNombre) {
        this.nombre = nuevoNombre;
        if (!validarNombre()) {
            System.err.println("Nombre no válido.");
            return false;
        }
        return true;
    }

    // Setter seguro para ID
    public boolean setIdSeguro(String nuevoId) {
        this.id = nuevoId;
        if (!validarId()) {
            System.err.println("ID no válido.");
            return false;
        }
        return true;
    }

    // Setter seguro para dirección
    public boolean setDireccionSeguro(String nuevaDir) {
        this.direccion = nuevaDir;
        if (!validarDireccion()) {
            System.err.println("Dirección no válida.");
            return false;
        }
        return true;
    }

    // Verifica si tiene cuenta asignada
    public boolean tieneCuenta() {
        return cuenta != null;
    }

    public boolean validarDatos() {
        return validarNombre() && validarId() && validarDireccion();
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
    // toString
    @Override
    public String toString() {
        if (cuenta == null) {
            return super.toString() + " | Sin cuenta";
        } else {
            return super.toString() + " | Cuenta: " + cuenta.getIdCuenta();
        }
    }
}
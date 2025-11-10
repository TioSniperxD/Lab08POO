public class Cliente extends Persona {

    // ATRIBUTOS
    private Cuenta cuenta; 
    private boolean datosValidos;

    // Constructor sobrecargado
    public Cliente(String nombre, String id, String direccion, Cuenta cuenta){
        super(nombre, id, direccion);
        this.cuenta = cuenta;
        datosValidos = validarDatos();
    }

    // Constructor que necesita Banco.java
    public Cliente(String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        this.cuenta = null;
        datosValidos = validarDatos();
    }

    // MÉTODOS (getters y setters)
    public Cuenta getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public boolean getDatosValidos() {
        return datosValidos;
    }

    // Mostrar información
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        if (cuenta != null) System.out.println("Cuenta del cliente: " + cuenta);
        else System.out.println("El cliente no tiene cuenta asignada.");
    }

    // VALIDACIONES INTERNAS
    private boolean validarDatos() {
        if (!validarNombre(this.nombre)) 
            return false;
        if (!validarId(this.id)) 
            return false;
        if (!validarDireccion(this.direccion)) 
            return false;
        return true;
    }

    private boolean validarNombre(String n) {
        if (n == null) {
            System.err.println("Error: el nombre no puede ser nulo.");
            return false;
        }
        n = n.trim();
        if (n.equals("")) {
            System.err.println("Error: el nombre no puede estar vacío.");
            return false;
        }

        int letras = 0;

        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            // letra mayúscula o minúscula
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) 
                letras++;
            else if (c == ' ') {
            } // espacio permitido
            else {
                System.err.println("Error: solo se permiten letras");
                return false;
            }
        }

        if (letras == 0) {
            System.err.println("Error: el nombre no puede ser solo espacios.");
            return false;
        }
        return true;
    }

    private boolean validarId(String id) {
        if (id == null) {
            System.err.println("Error: el ID no puede ser nulo.");
            return false;
        }
        id = id.trim();
        if (id.equals("")) {
            System.err.println("Error: el ID no puede estar vacío.");
            return false;
        }
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == ' ') {
                System.err.println("Error: el ID no puede contener espacios.");
                return false;
            }
        }
        return true;
    }

    private boolean validarDireccion(String d) {
        if (d == null) {
            System.err.println("Error: la dirección no puede ser nula.");
            return false;
        }
        d = d.trim();
        if (d.equals("")) {
            System.err.println("Error: la dirección no puede estar vacía.");
            return false;
        }
        return true;
    }

    // toString SIEMPRE AL FINAL
    @Override
    public String toString() {
        return "Cliente -> Nombre: " + nombre +
               ", ID: " + id +
               ", Dirección: " + direccion +
               ", Cuenta: " + (cuenta != null ? cuenta.getIdCuenta() : "Sin cuenta");
    }
}
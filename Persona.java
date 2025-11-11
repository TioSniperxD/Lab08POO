public class Persona {
    // ATRIBUTOS
    protected String nombre;
    protected String id;
    protected String direccion;
    // Constructor
    public Persona(String nombre, String id, String direccion) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
    }

    // Setter y Getter
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // MÉTODOS
    // Valida nombre (solo letras, tildes, ñ, espacios)
    public boolean validarNombre() {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Nombre vacío.");
            return false;
        }

        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            boolean esLetra = (c >= 'A' && c <= 'Z') ||
                               (c >= 'a' && c <= 'z') ||
                               (c == 'ñ' || c == 'Ñ') ||
                               (c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' ||
                                c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú');

            if (!esLetra) {
                System.err.println("Nombre con caracteres inválidos.");
                return false;
            }
        }
        return true;
    }

    public boolean validarId() {
        if (id == null || id.length() < 3) {
            System.err.println("ID inválido.");
            return false;
        }

        char letra = id.charAt(0);
        if (letra < 'A' || letra > 'Z') {
            System.err.println("El ID debe iniciar con letra mayúscula.");
            return false;
        }

        if (id.charAt(1) != '-') {
            System.err.println("El ID debe tener un guion en la segunda posición.");
            return false;
        }

        for (int i = 2; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                System.err.println("La parte numérica del ID solo acepta números.");
                return false;
            }
        }

        return true;
    }

    public boolean validarDireccion() {
        if (direccion == null || direccion.trim().length() < 3) {
            System.err.println("Dirección inválida.");
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", ID: " + id + ", Dirección: " + direccion;
    }

    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
}
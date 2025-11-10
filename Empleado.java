import java.util.*;

public class Empleado extends Persona {

    // ATRIBUTOS
    private List<String> accionesRealizadas;
    private boolean datosValidos;

    // Constructor
    public Empleado(String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        this.accionesRealizadas = new ArrayList<>();
        datosValidos = validarDatos();
    }

    // MÉTODOS (getters y setters)
    public boolean getDatosValidos() {
        return datosValidos;
    }

    public void agregarAccion(String accionRealizada) {
        accionesRealizadas.add(accionRealizada);
    }

    public void mostrarAcciones() {
        if (accionesRealizadas.isEmpty()) System.out.println("No se registraron acciones.");
        else {
            System.out.println("Acciones realizadas:");
            for (int i = 0; i < accionesRealizadas.size(); i++) {
                System.out.println((i + 1) + ". " + accionesRealizadas.get(i));
            }
        }
    }

    // Mostrar información
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        mostrarAcciones();
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
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) letras++;
            else if (c == ' ') {}
            else {
                System.err.println("Error: el nombre solo puede contener letras y espacios.");
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
            char c = id.charAt(i);
            if (c == ' ') {
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
        return "Empleado -> Nombre: " + nombre +
               ", ID: " + id +
               ", Dirección: " + direccion +
               ", Acciones registradas: " + accionesRealizadas.size();
    }
}
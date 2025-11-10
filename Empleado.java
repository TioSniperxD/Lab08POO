import java.util.ArrayList;

public class Empleado extends Persona {
    // Atributo
    private ArrayList<String> acciones;
    // Constructor
    public Empleado(String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        acciones = new ArrayList<>();
    }
    // GETTERS Y SETTERS
     // Getter de acciones (cantidad)
    public int getCantidadAcciones() {
        return acciones.size();
    }

    // Getter del arreglo completo
    public ArrayList<String> getAcciones() {
        return acciones;
    }

    // Setter nombre (aplica validación de Persona)
    public boolean setNombreSeguro(String nuevoNombre) {
        this.nombre = nuevoNombre;
        if (!validarNombre()) {
            System.err.println("Nombre no válido.");
            return false;
        }
        return true;
    }

    // Setter ID (aplica validación de Persona)
    public boolean setIdSeguro(String nuevoId) {
        this.id = nuevoId;
        if (!validarId()) {
            System.err.println("ID no válido.");
            return false;
        }
        return true;
    }

    // Setter dirección (aplica validación de Persona)
    public boolean setDireccionSeguro(String nuevaDir) {
        this.direccion = nuevaDir;
        if (!validarDireccion()) {
            System.err.println("Dirección no válida.");
            return false;
        }
        return true;
    }


    // MÉTODOS


    // Guarda acción realizada
    public void agregarAccion(String accion) {
        if (accion == null || accion.trim().isEmpty())
            System.err.println("Acción inválida.");
        acciones.add(accion);
    }

    // Muestra acciones registradas
    public void mostrarAcciones() {
        if (acciones.isEmpty()) 
            System.out.println("No tiene acciones registradas.");

        for (int i = 0; i < acciones.size(); i++) {
            System.out.println((i + 1) + ". " + acciones.get(i));
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
    // toString
    @Override
    public String toString() {
        return super.toString() + " | Acciones: " + acciones.size();
    }
}
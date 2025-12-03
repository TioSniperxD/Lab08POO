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
    public int getCantidadAcciones() {
        return acciones.size();
    }

    public ArrayList<String> getAcciones() {
        return new ArrayList<>(acciones);
    }

    // MÉTODOS
    //Guardar acción realizada
    public void agregarAccion(String accion) {
        if (accion == null || accion.trim().isEmpty()) {
            System.err.println("Acción inválida.");
            return; // Sale del método y NO AÑADE NADA
        }
        acciones.add(accion); 
    }

    // Muestra acciones registradas
    public void mostrarAcciones() {
        if (acciones.isEmpty()) { // Verifica Si no hay acciones registradas
            System.out.println("No tiene acciones registradas.");
            return; 
        }

        System.out.println("Acciones de " + getNombre() );
        for (int i = 0; i < acciones.size(); i++) {
            System.out.println((i + 1) + ". " + acciones.get(i));
        }
    }

    public boolean validarDatos() {
        return validarNombre() && validarId() && validarDireccion(); // Usa validaciones heredadas de Persona
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
    // toString
    @Override
    public String toString() {
        return super.toString() + " | Acciones: " + acciones.size(); // Agrega el número de acciones al texto base
    }
}
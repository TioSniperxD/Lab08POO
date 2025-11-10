import java.util.*;

//Hereda Empleado de la clase Persona
public class Empleado extends Persona{
    private List<String> accionesRealizadas; 

    //Constructor sobrecargado
    public Empleado (String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        this.accionesRealizadas = new ArrayList<>();
    }
    //Método para agregar
    public void agregarAccion(String accionRealizada) {
        accionesRealizadas.add(accionRealizada);
    }

    public void mostrarAcciones() {
        if (accionesRealizadas.isEmpty()) {
            System.out.println("No se registraron acciones.");
        }
        else {
            System.out.println("Acciones realizadas:");
            for (int i=0; i < this.accionesRealizadas.size(); i++) {
            System.out.println((i+1)+ ". "+ accionesRealizadas.get(i));
            }
        }
    }
    
    @Override
    public void mostrarInformacion () {
        super.mostrarInformacion();
        mostrarAcciones();
    }
}

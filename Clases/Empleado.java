package Clases;
import java.util.*;

//Hereda de la clase Persona
public class Empleado extends Persona{
    private List<String> accionesRealizadas; 

    //Constructor sobrecargado
    public Empleado (String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        this.accionesRealizadas = new ArrayList<>();
    }

    //Método para verificar si una acción es válida
     public boolean verificarAccion(String accionRealizada) {
        if (accionRealizada == null) return false;

        accionRealizada = accionRealizada.trim();
        if (accionRealizada.isEmpty()) return false;

        // Verifica que contenga: letras, espacios, guiones y tildes
        return accionRealizada.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ\\- ]+");
    }

    //Método para agregar acción (usa verificarAccion)
    public void agregarAccion(String accionRealizada) {
        if (verificarAccion(accionRealizada)) {
            accionRealizada = accionRealizada.trim();

            if (!accionesRealizadas.contains(accionRealizada)) {
                accionesRealizadas.add(accionRealizada);
                System.out.println("Acción agregada: " + accionRealizada);
            } else {
                System.out.println("Esta acción ya fue registrada: " + accionRealizada);
            }
        } else {
            System.out.println("Acción no válida: " + accionRealizada);
        }
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

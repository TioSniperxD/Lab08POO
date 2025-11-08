

public class Persona {
    //Atributos
    protected String nombre;
    protected String id;
    protected String direccion;

    //Constructor sobrecargado 
    public Persona (String nombre, String id, String direccion) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
    }

    //MÉTODOS (getters y setters) 
    public void setNombre (String nombre) {
        this.nombre = nombre;
    } 

    public void setId (String id) {
        this.id = id;
    }
    public void setDireccion (String direccion) {
        this.direccion = direccion;
    }

    public String getNombre () {
        return this.nombre;
    }
    public String getId () {
        return this.id;
    }
    public String getDireccion () {
        return this.direccion;
    }

    //Mostrar información
    public void mostrarInformacion () {
        System.out.println("\nNombre: "+ this.nombre +
        "\nID: "+ this.id +
        "\nDirección: "+ this.direccion);
    }
}

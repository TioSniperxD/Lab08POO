package Clases;

public class Persona {
    protected String nombre;
    protected String id;
    protected String direccion;

    //Constructor sobrecargado 
    public Persona (String nombre, String id, String direccion) {
        setNombre(nombre);
        setId(id);
        setDireccion(direccion);
    }

    //MÉTODOS (getters y setters) 
    public void setNombre (String nombre) {
        if(nombre != null && nombre.matches("[A-Za-zÁÉÍÓÚáéíóúnÑ]+") && !nombre.matches("\\d+")){
            this.nombre = nombre;
        }
        else {
            System.out.println("El nombre no debe estar vacío, debe tener solo letras y no debe estar compuesto solo por números.");
        }
    } 

    public void setId (String id) {
        if (id != null && id.matches("^[A-Za-z]-\\d{3}$")) {
            this.id = id;
        }
        else {
            System.out.println("No es válido el id, vuelve a ingresarlo. La forma es: letra-digitos(3).");
        }
    }
    public void setDireccion (String direccion) {
        if (direccion != null && direccion.matches(".*[A-Za-zÁÉÍÓÚáéíóúñÑ].*") && direccion.matches(".*\\d.*")) {
            this.direccion = direccion;
        } else {
            System.out.println("La dirección debe contener al menos una letra y un número (Ej: Calle 5).");
        }
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

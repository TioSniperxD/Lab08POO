public class Cliente extends Persona {
    //ATRIBUTOS
    private Cuenta cuenta; 
    
    //Constructor sobrecargado
    public Cliente (String nombre, String id, String direccion, Cuenta cuenta){
        super(nombre, id, direccion);
        this.cuenta = cuenta;
    }

    //Constructor que necesita Banco.java para registrar un cliente nuevo
    public Cliente (String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        this.cuenta = null; // Un cliente puede empezar sin cuenta
    }

    //MÉTODOS (getters y setters)
    public Cuenta getCuenta() {
        return this.cuenta;
    }
    public void setCuenta (Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    //Mostrar información
    @Override
    public void mostrarInformacion () {
        super.mostrarInformacion();
        if (cuenta != null) {
            System.out.println("Cuenta del cliente: " + cuenta);
        } else {
            System.out.println("El cliente no tiene cuenta asignada.");
        }
    }
}

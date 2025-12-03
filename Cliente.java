public class Cliente extends Persona {
    //Atributo
    private Cuenta cuenta;
    // Constructor (cliente sin cuenta)
    public Cliente(String nombre, String id, String direccion) {
        super(nombre, id, direccion);
        this.cuenta = null;
    }
    //Constructor (cliente con cuenta)
    public Cliente(String nombre, String id, String direccion, Cuenta cuenta) {
        super(nombre, id, direccion);
        setCuenta(cuenta);
    }

    //Getter y setter
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            System.err.println("Advertencia: Se está asignando una cuenta nula al cliente " + getNombre());
        }
        this.cuenta = cuenta;
    }

    // Verifica si tiene cuenta asignada
    public boolean tieneCuenta() {
        return cuenta != null;
    }

    public boolean validarDatos() {
        return validarNombre() && validarId() && validarDireccion();
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        // Llama al toString() de Persona (que da Nombre, ID, Direccion)
        String infoPersona = super.toString(); 
        
        if (cuenta == null) {
            return infoPersona + " | Sin cuenta asignada";
        } else {
            return infoPersona + " | Cuenta: " + cuenta.getIdCuenta();
        }
    }
}
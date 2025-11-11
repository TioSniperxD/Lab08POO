import java.util.ArrayList;

public class GestorUsuarios {

    private ArrayList<Usuario> listaUsuarios;

    public GestorUsuarios() {
        listaUsuarios = new ArrayList<>();
    }

    // Registrar usuario
    public void registrarUsuario(Usuario u) {

        if (!u.validarDatos()) 
            System.err.println("Usuario no válido.");

        if (buscarUsuario(u.getNombreUsuario()) != null)
            System.err.println("Nombre de usuario ya existe.");
 
        listaUsuarios.add(u);
        System.out.println("Usuario registrado: " + u.getNombreUsuario());
    }

    // Buscar usuario por nombre
    public Usuario buscarUsuario(String nombreUsuario) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }

    // Eliminar usuario
    public boolean eliminarUsuario(String nombreUsuario) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) {
            System.err.println("Usuario no encontrado.");
            return false;
        }
        listaUsuarios.remove(u);
        System.out.println("Usuario eliminado: " + nombreUsuario);
        return true;
    }

    // Cambiar contraseña
    public boolean cambiarContraseña(String nombreUsuario, String nuevaClave) {
        Usuario u = buscarUsuario(nombreUsuario);

        if (u == null) {
            System.err.println("Usuario no encontrado.");
            return false;
        }

        if (nuevaClave == null || nuevaClave.trim().length() < 4) {
            System.err.println("Contraseña inválida (mínimo 4 caracteres).");
            return false;
        }

        u.setContraseña(nuevaClave);
        System.out.println("Contraseña actualizada para " + nombreUsuario);
        return true;
    }

    // Cambiar estado (activar / desactivar)
    public boolean cambiarEstado(String nombreUsuario, boolean nuevoEstado) {
        Usuario u = buscarUsuario(nombreUsuario);

        if (u == null) {
            System.err.println("Usuario no encontrado.");
            return false;
        }

        u.setActivo(nuevoEstado);
        System.out.println("Estado de " + nombreUsuario + " actualizado.");
        return true;
    }

    // Mostrar todos los usuarios registrados
    public void mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        for (Usuario u : listaUsuarios) {
            System.out.println(u);
        }
    }

    // Validar acceso
    public Usuario acceder(String usuario, String clave) {
        Usuario u = buscarUsuario(usuario);

        if (u == null) {
            System.err.println("Usuario no existe.");
            return null;
        }

        if (!u.getActivo()) {
            System.err.println("Usuario inactivo.");
            return null;
        }

        // Aquí usamos el validarAcceso del usuario (manual)
        if (u.validarAcceso(usuario, clave)) {
            return u;
        }

        System.err.println("Contraseña incorrecta.");
        return null;
    }
}
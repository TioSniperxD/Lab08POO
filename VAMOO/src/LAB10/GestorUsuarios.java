package LAB10;

import java.util.ArrayList;

public class GestorUsuarios {
    // Lista usuarios
    private ArrayList<Usuario> listaUsuarios;
    //Constructor
    public GestorUsuarios() {
        listaUsuarios = new ArrayList<>();
    }

    //MÉTODOS
    // Registrar usuario
    public void registrarUsuario(Usuario u) {
        if (u == null) { // Verifica que no sea un objeto nulo
            System.err.println("No se puede registrar un usuario nulo.");
            return;
        }
        // Validación de datos
        if (!u.validarDatos()) {
            System.err.println("Usuario no válido.");
            return;
        }
        // Evitar duplicados
        if (buscarUsuario(u.getNombreUsuario()) != null) {
            System.err.println("El nombre de usuario ya existe.");
            return;
        }
        listaUsuarios.add(u); // Agrega el usuario a la lista
        System.out.println("Usuario registrado: " + u.getNombreUsuario()); // Confirma registro
    }

    // Buscar usuario por nombre
    public Usuario buscarUsuario(String nombreUsuario) { // Busca un usuario por su nombre único
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) return null;

        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) { // Compara nombres ignorando mayúsculas
                return u;
            }
        }
        return null;
    }
    // Eliminar usuario
    public boolean eliminarUsuario(String nombreUsuario) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) { // Si no existe, no elimina
            System.err.println("Usuario no encontrado.");
            return false;
        }
        listaUsuarios.remove(u); // Lo quita de la lista
        System.out.println("Usuario eliminado: " + nombreUsuario);
        return true;
    }
    // Cambiar contraseña
    public boolean cambiarContraseña(String nombreUsuario, String nuevaClave) { // Actualiza la clave del usuario
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) {
            System.err.println("Usuario no encontrado.");
            return false;
        }

        if (nuevaClave == null || nuevaClave.trim().length() < 4) { // Valida longitud mínima
            System.err.println("Contraseña inválida (mínimo 4 caracteres).");
            return false;
        }

        u.setContraseña(nuevaClave);
        System.out.println("Contraseña actualizada para " + nombreUsuario);
        return true;
    }

    // Cambiar estado (activar / desactivar)
    public boolean cambiarEstado(String nombreUsuario, boolean nuevoEstado) { // Activa o desactiva un usuario
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) {
            System.err.println("Usuario no encontrado.");
            return false;
        }
        u.setActivo(nuevoEstado); // Cambia su estado
        System.out.println("Estado de " + nombreUsuario + " actualizado a " + (nuevoEstado ? "Activo" : "Inactivo"));
        return true;
    }

    // Mostrar todos los usuarios registrados
    public void mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) { // Verifica si la lista está vacía
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("=== LISTA DE USUARIOS REGISTRADOS ===");
        for (Usuario u : listaUsuarios) {
            System.out.println(u); // Imprime usando el toString de Usuario
        } 
    }

    // Validar acceso
    public Usuario acceder(String usuario, String clave) {
        Usuario u = buscarUsuario(usuario);

        if (u == null) { // No existe
            System.err.println("Usuario no existe.");
            return null;
        }
        if (!u.getActivo()) { // Si está inactivo, no puede acceder
            System.err.println("Usuario inactivo.");
            return null;
        }
        if (u.validarAcceso(usuario, clave)) { // Verifica la clave
            System.out.println("Acceso permitido: " + usuario);// Acceso correcto
            return u;
        }
        System.err.println("Contraseña incorrecta."); 
        return null;
    }
}
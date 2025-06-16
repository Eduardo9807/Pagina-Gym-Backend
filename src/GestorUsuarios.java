import java.util.*;
//Administra todos los usuarios registrados
public class GestorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();//almacena los objetos USUARIO registrados en ele gimnasio

    public void agregarUsuario(Usuario usuario) {
        // Agrega un nuevo usuario a la lista si no está previamente registrado
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        } else {
            System.out.println("⚠️ El usuario ya está registrado.");
        }
    }

    public Usuario buscarUsuario(int id) {
        // Busca un usuario en la lista comparando el atributo id
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u; // Devuelve el usuario si se encuentra
            }
        }
        return null; // Si no se encuentra, devuelve null
    }

    public Usuario buscarUsuario(String nombre) {
        // Sobrecarga del método buscarUsuario, esta vez por nombre
        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        return null;
    }

    public boolean estaRegistrado(Usuario usuario) {
        // Retorna true si el usuario ya está en la lista de registrados
        return usuarios.contains(usuario);
    }


}

//Esta clase reprenseta una clase del gimnasio, aqui se gestionan las inscripciones
import java.util.*;// importa metodos de la lista
public class Clase {
    private String nombre;
    public String horario;
    public int cupoMaximo;
    private List<Usuario> usuariosInscritos = new ArrayList<>();//lista tipo DINÁMICA de usuarios inscritos
    //CONSTRUCTOR para inicializar
    public Clase(String nombre, String horario, int cupoMaximo) {
        this.nombre = nombre;
        this.horario = horario;
        this.cupoMaximo = cupoMaximo;
    }
    //retorna si el numero de inscritos es menor al cupo máximo
    public boolean hayCupo() {
        return usuariosInscritos.size() < cupoMaximo;
    }
    //Inscribe un usuario a la clase validando en IF si hay cupo , lanza excepcion personalizada si no existe cupo
    public boolean inscribirUsuario(Usuario usuario) throws ExcepcionClaseLlena {
        if (hayCupo()) {
            usuariosInscritos.add(usuario);
            System.out.println(usuario.getNombre() + " se ha inscrito a " + nombre);
            return true;
        }
        throw new ExcepcionClaseLlena("La clase '" + nombre + "' ya alcanzó el cupo máximo de " + cupoMaximo +
                ". Actualmente hay " + usuariosInscritos.size() + " inscritos.");// uso del método SIZE de lista y obtiene el numero actual de inscritos
    }
    //devuelve el nombre de la clase
    public String getNombre() {
        return nombre;
    }
    //devuelve la lista de usuarios inscritos , esto se usa en los resumenes de entrenador
    public List<Usuario> getUsuariosInscritos() {
        return usuariosInscritos;
    }
    public void removerUsuario(Usuario usuario) {
        // Quita al usuario de la lista de inscritos si está presente
        usuariosInscritos.remove(usuario);
    }
}

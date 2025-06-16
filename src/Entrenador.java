import java.util.*;//importa metodos de Lista

public class Entrenador extends Persona {
    private String especialidad;
    private int aniosExperiencia;
    private List<Clase> clasesAsignadas = new ArrayList<>();//lista de clases que un entrenador imparte
    //constructor que inicializa el objeto entrenador para poder crearlo en main
    public Entrenador(String nombre, String correo,String especialidad, int aniosExperiencia) {
        super(nombre, correo);//inicializa los datos heredados de persona
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;

    }
    //Método que agrega una clase a la lista de clases que imparte el entrenador
    public void asignarClase(Clase clase) {
        clasesAsignadas.add(clase);//.ADD metodo de la lista para agregar un elemento
    }
   //Get devuelve los datos que seran usuados en el resumendel entrenador
    public List<Clase> getClases() {
        return clasesAsignadas;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public List<Clase> getClasesAsignadas() {
        return clasesAsignadas;
    }
}
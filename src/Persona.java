//Esta clase presenta una persona que no es usuario aun del gimnasio
//Es una superclase de usuario y entrenador
public class Persona {
    //atributos
    protected String nombre;//uso del modificador de acceso protedte para que las clases hijas puedan acceder
    protected String correo;
    //constructor para inicialiazar los datos
    public Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }
    //get devulve el nombre de la persona
    public String getNombre() {
        return nombre;
    }
}

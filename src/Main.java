//primera version ejecuta fija , No interaccion .Muestra de excepciones
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Creando objeto  entrenadores
            Entrenador nancy = new Entrenador("Nancy", "nancy@mail.com","yoga",3);
            Entrenador uriel = new Entrenador("Uriel", "uriel@mail.com","pesas",4);

            // Crear clases y asignarlas
            Clase yoga = new Clase("Yoga", "10:00 AM", 2);
            Clase spinning = new Clase("Spinning", "11:00 AM", 1);
            nancy.asignarClase(yoga);
            uriel.asignarClase(spinning);

            // Crear productos
            Producto agua = new Producto("Agua", 15.0, 2);
            Producto barra = new Producto("Barra energética", 25.0, 1);

            // Gestor y usuarios
            GestorUsuarios gestor = new GestorUsuarios();
            Usuario ana = new Usuario(1, "Ana", "ana@mail.com");
            Usuario pedro = new Usuario(2, "Pedro", "pedro@mail.com");
            gestor.agregarUsuario(ana);
            gestor.agregarUsuario(pedro);

            // Validar usuarios y realizar acciones
            if (!gestor.estaRegistrado(ana)) throw new ExcepcionUsuarioNoRegistrado("Ana no está registrada");

            ana.comprarProducto(barra); // OK
            pedro.comprarProducto(barra); //  stock insuficiente

            ana.inscribirseEnClase(yoga); // OK
            pedro.inscribirseEnClase(yoga); // OK
            pedro.inscribirseEnClase(spinning); //  clase llena

            // Mostrar resumen
            System.out.println("\n Resumen:");
            List<Entrenador> entrenadores = Arrays.asList(nancy, uriel);
            for (Entrenador e : entrenadores) {
                System.out.println(e.getNombre() + " imparte:");
                for (Clase c : e.getClases()) {
                    System.out.println(" - " + c.getNombre());
                }
            }

        } catch (ExcepcionUsuarioNoRegistrado | ExcepcionClaseLlena | ExcepcionStockInsuficiente e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
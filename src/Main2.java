//segunda version , muestra resumenes de entrenador y usuario
import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        try {
            // Crear entrenadores y clases
            Entrenador nancy = new Entrenador("Nancy", "nancy@gym.com","yoga",3);
            Entrenador uriel = new Entrenador("Uriel", "uriel@gym.com","pesas",4);

            Clase yoga = new Clase("Yoga", "08:00 AM", 5);
            Clase zumba = new Clase("Zumba", "09:00 AM", 3);
            Clase spinning = new Clase("Spinning", "10:00 AM", 2);

            nancy.asignarClase(yoga);
            nancy.asignarClase(zumba);
            uriel.asignarClase(spinning);

            //  Mostrar info del entrenador Nancy
            System.out.println(" Información del Entrenador:");
            System.out.println("Nombre: " + nancy.getNombre());
            System.out.println("Correo: " + nancy.correo);
            System.out.println("Clases asignadas:");
            for (Clase c : nancy.getClases()) {
                System.out.println(" - " + c.getNombre() + " (" + c.horario + ")");
            }

            //  Registrar un nuevo usuario con ID
            GestorUsuarios gestor = new GestorUsuarios();
            Usuario luisa = new Usuario(100, "Luisa", "luisa@mail.com");
            gestor.agregarUsuario(luisa);
            System.out.println("\n Usuario registrado: " + luisa.getNombre() + " (ID: " + luisa.getId() + ")");

            //  Mostrar productos disponibles
            Producto agua = new Producto("Agua", 15.0, 5);
            Producto barra = new Producto("Barra energética", 25.0, 2);
            Producto toalla = new Producto("Toalla deportiva", 35.0, 1);
            List<Producto> catalogo = Arrays.asList(agua, barra, toalla);

            System.out.println("\n Productos disponibles:");
            for (int i = 0; i < catalogo.size(); i++) {
                Producto p = catalogo.get(i);
                System.out.println((i + 1) + ". " + p.getNombre() + " - $" + p.precio + " (Stock: " + (p.hayStock() ? "Disponible" : "Agotado") + ")");
            }

            // Simular que Laura compra la barra energética
            Producto elegido = catalogo.get(1); // Barra
            if (!gestor.estaRegistrado(luisa)) {
                throw new ExcepcionUsuarioNoRegistrado("Laura no está registrada.");
            }

            luisa.comprarProducto(elegido);

            //  Mostrar resumen de compra
            System.out.println("\n Resumen de compra:");
            System.out.println("Usuario: " + luisa.getNombre());
            System.out.println("Producto comprado: " + elegido.getNombre());

            //  Inscribir a laura en varias clases
            Clase[] clasesSeleccionadas = { yoga, zumba };
            luisa.inscribirseEnClase(clasesSeleccionadas);

            //  Mostrar entrenadores, clases y alumnos inscritos
            System.out.println("\n Resumen de Entrenadores y Clases:");
            List<Entrenador> entrenadores = Arrays.asList(nancy, uriel);
            for (Entrenador e : entrenadores) {
                System.out.println(" Entrenador: " + e.getNombre());
                for (Clase c : e.getClases()) {
                    System.out.println("   Clase: " + c.getNombre() + " (" + c.getUsuariosInscritos().size() + " inscritos)");
                    if (c.getUsuariosInscritos().isEmpty()) {
                        System.out.println("    No hay alumnos inscritos.");
                    } else {
                        for (Usuario u : c.getUsuariosInscritos()) {
                            System.out.println("    - " + u.getNombre() + " (ID: " + u.getId() + ")");
                        }
                    }
                }
            }

        } catch (ExcepcionUsuarioNoRegistrado | ExcepcionClaseLlena | ExcepcionStockInsuficiente e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
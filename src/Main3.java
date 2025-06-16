//Tercera version , menú interactivo
import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicialización de estructuras
        GestorUsuarios gestor = new GestorUsuarios();
        List<Entrenador> entrenadores = new ArrayList<>();
        List<Clase> clasesDisponibles = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();

        // Carga inicial de productos
        productos.add(new Producto("Agua", 10, 10));
        productos.add(new Producto("Barra energética", 25, 3));
        productos.add(new Producto("Toalla", 20, 2));

        // Menú principal
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n MENÚ PRINCIPAL:");
            System.out.println("1. Registrar entrenador");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Crear clase y asignar a entrenador");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Comprar producto");
            System.out.println("6. Inscribir usuario en clase");
            System.out.println("7. Ver resumen de entrenadores y clases");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1: { // Registrar entrenador
                    System.out.print("Nombre del entrenador: ");
                    String nombre = sc.nextLine();
                    System.out.print("Correo del entrenador: ");
                    String correo = sc.nextLine();
                    System.out.print("Especialidad: ");
                    String especialidad = sc.nextLine();
                    System.out.print("Años de experiencia: ");
                    int experiencia = sc.nextInt(); sc.nextLine();
                    Entrenador e = new Entrenador(nombre, correo, especialidad, experiencia);
                    entrenadores.add(e);
                    System.out.println(" Entrenador registrado.");
                    break;
                }

                case 2: { // Registrar usuario
                    System.out.print("ID del usuario: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre del usuario: ");
                    String nombre = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    Usuario u = new Usuario(id, nombre, correo);
                    gestor.agregarUsuario(u);
                    System.out.println(" Usuario registrado.");
                    break;
                }

                case 3: { // Crear clase y asignar
                    if (entrenadores.isEmpty()) {
                        System.out.println(" No hay entrenadores registrados.");
                        break;
                    }
                    System.out.print("Nombre de la clase: ");
                    String nombre = sc.nextLine();
                    System.out.print("Horario: ");
                    String horario = sc.nextLine();
                    System.out.print("Cupo máximo: ");
                    int cupo = sc.nextInt(); sc.nextLine();
                    Clase nueva = new Clase(nombre, horario, cupo);
                    clasesDisponibles.add(nueva);

                    // Asignar a entrenador
                    System.out.println("Entrenadores disponibles:");
                    for (int i = 0; i < entrenadores.size(); i++) {
                        System.out.println((i+1) + ". " + entrenadores.get(i).getNombre());
                    }
                    System.out.print("Selecciona uno: ");
                    int seleccion = sc.nextInt(); sc.nextLine();
                    entrenadores.get(seleccion - 1).asignarClase(nueva);
                    System.out.println(" Clase creada y asignada.");
                    break;
                }

                case 4: { // Mostrar productos
                    System.out.println("\n Productos disponibles:");
                    for (int i = 0; i < productos.size(); i++) {
                        Producto p = productos.get(i);
                        System.out.println((i+1) + ". " + p.getNombre() + " - $" + p.precio +
                                " (Stock: " + (p.hayStock() ? "Disponible" : "Agotado") + ")");
                    }
                    break;
                }

                case 5: { // Comprar producto
                    System.out.print("ID del usuario: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Usuario u = gestor.buscarUsuario(id);
                    if (u == null) {
                        System.out.println(" Usuario no registrado.");
                        break;
                    }

                    System.out.println("Productos:");
                    for (int i = 0; i < productos.size(); i++) {
                        Producto p = productos.get(i);
                        System.out.println((i+1) + ". " + p.getNombre() + " - $" + p.precio);
                    }
                    System.out.print("Selecciona un producto: ");
                    int prodIdx = sc.nextInt(); sc.nextLine();
                    Producto elegido = productos.get(prodIdx - 1);

                    System.out.print("¿Cuántas unidades desea comprar? ");
                    int cantidad = sc.nextInt(); sc.nextLine();
                    System.out.print("Método de pago (efectivo/tarjeta): ");
                    String metodo = sc.nextLine();

                    try {
                        u.comprarProducto(elegido, cantidad, metodo);
                    } catch (ExcepcionStockInsuficiente e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                    break;
                }

                case 6: { // Inscribir en clase
                    System.out.print("ID del usuario: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Usuario u = gestor.buscarUsuario(id);
                    if (u == null) {
                        System.out.println(" Usuario no registrado.");
                        break;
                    }

                    System.out.println("Clases disponibles:");
                    for (int i = 0; i < clasesDisponibles.size(); i++) {
                        Clase c = clasesDisponibles.get(i);
                        System.out.println((i+1) + ". " + c.getNombre() + " - " + c.horario);
                    }
                    System.out.print("Selecciona clase: ");
                    int idx = sc.nextInt(); sc.nextLine();
                    Clase clase = clasesDisponibles.get(idx - 1);

                    try {
                        u.inscribirseEnClase(clase);
                    } catch (ExcepcionClaseLlena e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                    break;
                }

                case 7: { // Mostrar resumen
                    System.out.println("\n Entrenadores y clases:");
                    for (Entrenador ent : entrenadores) {
                        System.out.println("Nombre: " + ent.getNombre());
                        System.out.println("   Especialidad: " + ent.getEspecialidad());
                        System.out.println("   Experiencia: " + ent.getAniosExperiencia() + " años");

                        if (ent.getClases().isEmpty()) {
                            System.out.println("    No tiene clases asignadas.");
                        } else {
                            for (Clase c : ent.getClases()) {
                                System.out.println("   Clase: " + c.getNombre() + " | Horario: " + c.horario +
                                        " | Cupo: " + c.getUsuariosInscritos().size() + "/" + c.cupoMaximo);
                                if (c.getUsuariosInscritos().isEmpty()) {
                                    System.out.println("       Sin alumnos inscritos.");
                                } else {
                                    for (Usuario u : c.getUsuariosInscritos()) {
                                        System.out.println("      - " + u.getNombre() + " (ID: " + u.getId() + ")");
                                    }
                                }
                            }
                        }
                        System.out.println(); // Línea en blanco entre entrenadores
                    }
                    break;
                }

                case 8: {// Cancelar inscripción a clase
                    System.out.println("8. Cancelar inscripción a clase");
                    System.out.print("ID del usuario: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Usuario u = gestor.buscarUsuario(id);
                    if (u == null) {
                        System.out.println(" Usuario no registrado.");
                        break;
                    }

                    System.out.println("Clases en las que está inscrito:");
                    List<Clase> inscritas = new ArrayList<>();
                    for (Clase c : clasesDisponibles) {
                        if (c.getUsuariosInscritos().contains(u)) {
                            inscritas.add(c);
                        }
                    }

                    if (inscritas.isEmpty()) {
                        System.out.println("️ El usuario no está inscrito en ninguna clase.");
                        break;
                    }

                    for (int i = 0; i < inscritas.size(); i++) {
                        System.out.println((i + 1) + ". " + inscritas.get(i).getNombre());
                    }

                    System.out.print("Selecciona la clase a cancelar: ");
                    int seleccion = sc.nextInt(); sc.nextLine();
                    Clase clase = inscritas.get(seleccion - 1);

                    boolean cancelado = u.cancelarClase(clase);
                    if (cancelado) {
                        System.out.println(" Cancelación completada.");
                    }
                    break;
                }

                case 0:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println(" Opción inválida.");
            }
        }

        sc.close();
    }
}

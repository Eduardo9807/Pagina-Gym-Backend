import java.util.*;//importa métodos de la Lista DINÁMICA
//Esta clase hereda de persona su nombre y correo
public class Usuario extends Persona {
    //Atributos
    private int id;//identificador de la persona como usuario del gimnasio
    private List<Clase> clasesInscritas = new ArrayList<>();//lista de clases que inscribio el usuario
    private List<Producto> productosComprados = new ArrayList<>();//lista de productos que el usuario ha comprado en el gimnasio
    //constructor que inicializa los atributos heredados y propios
    public Usuario(int id, String nombre, String correo) {
        super(nombre, correo);//llama el constructor de la clase Persona
        this.id = id;
    }
    //Devuelve el id del usuario , permite acceder a este dato privado desde fuera de la clase
    public int getId() {
        return id;
    }

    //inscribe el usuario en UNA clase y lanza la excepcion personalizada si la clase esta llena
    //BASE para la sobre carga
    public boolean inscribirseEnClase(Clase clase) throws ExcepcionClaseLlena {
        if (clase.inscribirUsuario(this)) {
            clasesInscritas.add(clase);//usa el método base para cada clase
            return true;
        }
        return false;
    }

    // Sobrecarga: inscripción en varias clases  RECIBE (arreglo) PARA CLASES PRE CRAGADSS EN ARREGLO
    public void inscribirseEnClase(Clase[] clases) throws ExcepcionClaseLlena {
        for (Clase clase : clases) {
            inscribirseEnClase(clase);//reutiliza el método original
        }
    }

    // Sobrecarga: inscripción en varias clases RECIBE (lista) DINÁMICA
    public void inscribirseEnClase(List<Clase> clases) throws ExcepcionClaseLlena {
        for (Clase clase : clases) {
            inscribirseEnClase(clase);
        }
    }
    public boolean cancelarClase(Clase clase) {
        // Verifica si el usuario está inscrito en la clase
        if (clasesInscritas.contains(clase)) {
            clasesInscritas.remove(clase); // Lo elimina de su historial
            clase.removerUsuario(this);   // También se quita de la clase
            System.out.println(nombre + " canceló su inscripción en " + clase.getNombre());
            return true;
        } else {
            System.out.println(nombre + " no está inscrito en la clase " + clase.getNombre());
            return false;
        }
    }
    //compra solo UNA UNIDAD de producto
    public void comprarProducto(Producto producto) throws ExcepcionStockInsuficiente {
        if (producto.comprar()) {
            productosComprados.add(producto);
            System.out.println(nombre + " compró: " + producto.getNombre());
        }
    }
    //Compra de mas de un producto y regitra el método de pago
    public void comprarProducto(Producto producto, int cantidad, String metodoPago) throws ExcepcionStockInsuficiente {
        if (producto.comprar(cantidad)) {
            for (int i = 0; i < cantidad; i++) {
                productosComprados.add(producto);
            }
            System.out.println(nombre + " compró " + cantidad + " unidad(es) de " + producto.getNombre()
                    + " usando " + metodoPago);
        }
    }
}
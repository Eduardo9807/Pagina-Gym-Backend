//Productos en venta en el gimnasio
public class Producto {
    private String nombre;
    public double precio;
    private int stock;
    //Constuctor  que inicializa
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Método base comprar UNA UNIDAD , si hay stock lo reduce en una unidad , de loc ontrario llama excepcion personalizada
    public boolean comprar() throws ExcepcionStockInsuficiente {
        return comprar(1);
    }

    // Sobrecarga: múltiples unidades con uso de excepcion personalizada
    public boolean comprar(int cantidad) throws ExcepcionStockInsuficiente {
        if (cantidad <= stock) {
            stock -= cantidad;
            return true;
        }
        throw new ExcepcionStockInsuficiente("Stock insuficiente para: " + nombre);
    }

    // Sobrecarga: con nombre de usuario , para controlar quien realizp la compra y mostrar resumen de compra
    public boolean comprar(int cantidad, String nombreUsuario) throws ExcepcionStockInsuficiente {
        if (comprar(cantidad)) {
            System.out.println(nombreUsuario + " compró " + cantidad + " unidad(es) de " + nombre);
            return true;
        }
        return false;
    }
     //devuelve true si hay al menos un producto dsiponible
    public boolean hayStock() {
        return stock > 0;
    }
    //devuelve el nombre del producto , usado para mostrar en el menú interactivo y resumen de compra
    public String getNombre() {
        return nombre;
    }
}
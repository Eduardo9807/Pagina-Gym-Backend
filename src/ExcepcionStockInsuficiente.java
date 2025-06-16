public class ExcepcionStockInsuficiente extends Exception {
    public ExcepcionStockInsuficiente(String mensaje) {
        super(mensaje);//invoca al constructor de la clase base ECEPTION que acepta un string y se recupera con un get
    }
}
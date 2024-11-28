package dominio.productos;

public class ProductoNoEncontrado extends Exception {
    private Producto producto;
    private String id;


    //Constructor del objeto de excepción:
    public ProductoNoEncontrado(Producto producto) {this.producto = producto;}
    public ProductoNoEncontrado(String id) {this.id = id;}


    //Métodos de la expeción:
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        if (producto != null) {
            sb.append(producto).append(" ");
        }
        if (id != null) {
            sb.append(id).append(" ");
        }
        sb.append("no existe.");
        return sb.toString().trim();
    }
}
package dominio.productos;

public class ProductoDuplicado extends Exception {
    private final Producto producto;


    //Constructor del objeto de expeción:
    public ProductoDuplicado(Producto producto) {this.producto = producto;}


    //Métodos de la excepción:
    public Producto getProducto() {return producto;}
    public String idProducto() {return producto.getId();}
    public String getMessage() {return producto.getId()+" already exists.";}
}
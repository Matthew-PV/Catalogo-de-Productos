package dominio;

import dominio.productos.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Catalogo implements Serializable {
    private String nombre;
    private final Map<String, Categoria> categorias;
    private final ArrayList<Producto> productos = new ArrayList<>();
    //Para que cada catálogo tenga categorías preseleccionadas, fijas e independientes entre catálogos,
    //utilizamos un contenedor HashMap para cada uno.


    //Constructores:
    public Catalogo (String nombre) {
        this.nombre = nombre;
        categorias = new HashMap<>();
        for (Categoria categoria : ManejadorCategorias.getInstancia().getCategoriasPredefinidas()) {
            categorias.put(categoria.getNombre(), new Categoria(categoria.getNombre(), categoria.getAbreviatura()));
        } //Adición de las categorías
    }


    //Getters y Setters de Catálogo:
    public String getNombre() {return nombre;}
    public Catalogo setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public ArrayList<Producto> getProductos() {return productos;}


    //Métodos de Categoria:
    public Categoria getCategoria(String nombreCategoria) {return categorias.get(nombreCategoria);}
    public Catalogo addProductoACategoria(Producto producto, String nombreCategoria) throws ProductoDuplicado {
        getCategoria(nombreCategoria).addProducto(producto);
        productos.add(producto);
        return this;
    }


    //Métodos de Producto:
    public Producto getProducto(Producto producto, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).getProducto(producto);
    }
    public Producto getProducto(String id, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).getProducto(id);
    }

    public boolean eliminarProducto(Producto producto, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).eliminarProducto(producto);
    }
    public boolean eliminarProducto(String id, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).eliminarProducto(id);
    }


    //Métodos de Catalogo:
    public String toString() {
        StringBuilder sb = new StringBuilder(nombre);
        for (Categoria categoria : categorias.values()) {
            sb.append("\n\t").append(categoria).append('\n');
        }
        return sb.toString();
    }
}
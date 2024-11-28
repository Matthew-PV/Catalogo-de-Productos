package dominio;

import dominio.productos.Producto;
import dominio.productos.ProductoDuplicado;

import java.util.HashMap;
import java.util.Map;

public class Catalogo {
    private String nombre;
    private final Map<String, Categoria> categorias; //Utilizamos un mapa para permitir categorías predefinidas.


    //Constructores:
    public Catalogo () {
        categorias = new HashMap<>();
        for (Categoria categoria : ManejadorCategorias.getInstancia().getCategoriasPredefinidas()) {
            categorias.put(categoria.getNombre(), new Categoria(categoria.getNombre(), categoria.getAbreviatura()));
        }
    }
    public Catalogo (String nombre) {
        this.nombre = nombre;
        categorias = new HashMap<>();
        for (Categoria categoria : ManejadorCategorias.getInstancia().getCategoriasPredefinidas()) {
            categorias.put(categoria.getNombre(), new Categoria(categoria.getNombre(), categoria.getAbreviatura()));
        }
    }


    //Getters y Setters:
    public String getNombre() {return nombre;}
    public Catalogo setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }


    //Métodos de Categoria
    public Categoria getCategoria(String categoria) {return categorias.get(categoria);}
    public Catalogo addProductoACategoria(Producto producto, String categoria) throws ProductoDuplicado {
        categorias.get(categoria).addProducto(producto);
        return this;
    }


    //Métodos de Catalogo
    public String toString() {
        StringBuilder sb = new StringBuilder(nombre);
        for (Categoria categoria : categorias.values()) {
            sb.append("\n\t").append(categoria).append('\n');
        }
        return sb.toString().trim();
    }
}
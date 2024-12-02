package dominio;

import dominio.productos.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categoria implements Serializable {
    private String nombre, abreviatura;
    private ArrayList<Producto> productos = new ArrayList<>();


    //Constructores:
    public Categoria(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }


    //Getters y Setters de Categoría:
    public String getNombre() {return nombre;}
    public Categoria setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public String getAbreviatura() {return abreviatura;}
    public Categoria setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
        return this;
    }
    public List<Producto> getProductos() {return productos;}
    public Categoria setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
        return this;
    }


    //Métodos de Producto:
    public boolean contains(Producto producto) {return productos.contains(producto);}
    public boolean contains(String id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) return true;
        }
        return false;
    }

    public Categoria addProducto(Producto producto) throws ProductoDuplicado {
        String idTemp = producto.generarID(producto.generarID(abreviatura));
        if (contains(idTemp)) throw new ProductoDuplicado(producto);
        producto.setID(producto.generarID(abreviatura));
        productos.add(producto);
        return this;
    }
    public boolean modificarProducto(Producto producto, String[] modificacion) throws ProductoNoEncontrado, ArrayIndexOutOfBoundsException {
        if (!contains(producto)) throw new ProductoNoEncontrado(producto);
        else return producto.modificar(modificacion);
    }

    public Producto getProducto(Producto producto) throws ProductoNoEncontrado {
        if (!contains(producto)) throw new ProductoNoEncontrado(producto);
        else return productos.get(productos.indexOf(producto));
    }
    public Producto getProducto(String id) throws ProductoNoEncontrado {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) return producto;
        }
        throw new ProductoNoEncontrado(id);
    }

    public boolean eliminarProducto(Producto producto) throws ProductoNoEncontrado {
        if (!contains(producto)) throw new ProductoNoEncontrado(producto);
        else {
            productos.remove(producto);
            return true;
        }
    }
    public boolean eliminarProducto(String id) throws ProductoNoEncontrado {
        if(!contains(id)) throw new ProductoNoEncontrado(id);
        else {
            productos.remove(getProducto(id));
            return true;
        }
    }


    //Métodos de Categoria:
    public String toString() {
        StringBuilder sb = new StringBuilder(Ansi.Cyan(nombre.toUpperCase()));
        if (productos.isEmpty()) return "La categoría de "+nombre+" está vacía.";
        else {
            int index = 1;
            for (Producto producto : productos) {
                sb.append("\n\t").append(index).append(' ').append(producto);
                index++;
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
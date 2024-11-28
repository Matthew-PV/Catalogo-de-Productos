package dominio;

import dominio.productos.*;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String nombre, abreviatura;
    private List<Producto> productos = new ArrayList<>();


    //Constructores:
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    public Categoria(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }


    //Getters y Setters:
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
    public Categoria setProductos(List<Producto> productos) {
        this.productos = productos;
        return this;
    }


    //Métodos de Producto:
    public Categoria addProducto(Producto producto) throws ProductoDuplicado {
        if (productos.contains(producto)) throw new ProductoDuplicado(producto);
        producto.setID(producto.generarID(abreviatura));
        productos.add(producto);
        return this;
    }
    //Método contains() con ID y Producto.
    public Producto buscarPorID(String id) throws ProductoNoEncontrado {
        for (Producto producto : productos) {
            if (producto.getId() == id) return producto;
        }
        throw new ProductoNoEncontrado(id);
    }
    public boolean eliminarProducto(Producto producto) throws ProductoNoEncontrado {
        if (!productos.contains(producto)) throw new ProductoNoEncontrado(producto);
        else {
            productos.remove(producto);
            return true;
        }
    }
    //Método eliminar con Producto.


    //Métodos de Categoria:
    public String toString() {
        StringBuilder sb = new StringBuilder(nombre.toUpperCase());
        if (!productos.isEmpty()) {
            for (Producto producto : productos) {
                sb.append("\n\t").append(producto);
            }
            sb.append('\n');
        }
        else return nombre+" está vacía";
        return sb.toString().trim();
    }
}